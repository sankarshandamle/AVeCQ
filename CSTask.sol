pragma solidity ^0.8.1;

// updated CSTask with worker inputs

contract crowdsourcingTask {

    // structure
    
    // structures which hold submit response tuple
    struct workerresponse_encryptions {
        bytes32[2] answer_encryption;
        bytes32[2] address_encryption;
        bytes32[2] randomness_encryption;
	    bool exists;        // this flag ensures that a worker submits only one response
    }
    
    struct workerresponse_data {
    	bytes32[2] quality_commitment;
        bytes32 hash_tag;
        bytes32[4] proofs;
    }

   // create a mapping between requestor address and its existence
   mapping(address => bool) addressExists;

   // mapping which maps a worker address to the response tuple
    mapping(address => workerresponse_encryptions) responses_encryptions;
    mapping(address => workerresponse_data) responses_data;
    

    
    // structure which holds the submit quality response tuple
    struct submitquality_ComTuple {
        bytes32 hashIdentifier;
        bytes32 taskRandomness;
        bytes32 attackRandomness;
        bytes32[2] qualityCommitment;
        uint256 leafPosition;
    }
    
    // structure which holds the submit quality response tuple
    struct submitquality_ProofTuple {
        bytes32[4] AuthQual_proof_points;
        bytes32[4] AuthValue_proof_points;
    }
    
     bytes32[4] authcalc_proof_points;


    // mapping which maps H(r_i^t) to each submit quality tuple   
    mapping(bytes32=>submitquality_ComTuple) workerTuples_Com;
    
    // mapping which maps H(r_i^t) to each submit quality tuple   
    mapping(bytes32=>submitquality_ProofTuple) workerTuples_Proof;
     


    // other variables

    address private requestor;   // address of the requestor
    uint256 workerCount = 0;    // counter for the number of workers
    uint256[10] responseCount; // count array that keeps count of the number of times a response has been submitted
    uint256 budget; // amount sent by the requestor
    uint256 maxFrequency = 0;   // variable to decide the maximum responded response
    uint256 winner; // variable that holds the final winning response
    uint256 noOfWorkers = 0;    // variable that states how many workers are required
    uint256 noOfChoices = 0;    // variable that states the number of choices available for a task
    enum contractState {SETUP, RESPONDING, SUBMITFILES, SUBMITAUTHCALC, SUBMITQUALITY, PAYMENTS, DONE}     // enums to handle the contract state
    uint256 budgetConsumed = 0;     // interger to keep a tab on the budget used
    uint256 amountToBePaid = 0;
    uint256 epochLength = 0;    // integer that specifies how much time each state to last
    uint256 blockNumber = 0;    // integer that storest the previous state blockNumber
    uint256 protestLength=0;
    contractState currState;
    string reqPubKey;
    uint256[2] thresholds;
    bytes32 reqHash;
    address private tempReqAddress;

    // modifiers

    modifier isRequestor() {
        require(msg.sender == requestor, "access only to the requestor");
        _;
    }

    modifier enoughBudget(uint256 _noOfWorkers, uint256 _amountToPay) {
        require(msg.value >= _noOfWorkers*_amountToPay, "insufficient money deposited");
        _;
    }

    modifier checkCurrState(contractState state) {
        require(currState == state, "function not available at this state");
        _;
    }

    modifier allowedChoices(uint256 x) {
        require(x < 30, "more than 30 choices not allowed");
        _;
    }

    modifier allowedWorkers(uint256 y) {
        require(y < 150, "more than 150 workers not allowed");
        _;
    }
    
    modifier protestOver() {
        require(blockNumber + protestLength < block.number, "Protest is not over yet!");
        _;
    }
    

    // events

    event taskEmit (string task);
    event qualitySubmitted (string announce);

                /* Main Source */
    /* ON-CHAIN
    */

    // a requestor will invoke the contract
    constructor () {
        requestor = msg.sender;
        currState = contractState.SETUP;    // state of the contract is initialized to the setup state
    }

    // only the requestor can broadcast the task
    function broadcastTask (string memory task, uint256 number_1, uint256 number_2, uint256 _amount, uint256 _epoch, uint256 _protest, string memory _pubkey, uint256 _alpha, uint256 _beta) allowedWorkers(number_1) allowedChoices(number_2) checkCurrState(contractState.SETUP) isRequestor() enoughBudget(number_1, _amount) public payable {
        budget = msg.value;
        noOfWorkers = number_1;
        noOfChoices = number_2;
        amountToBePaid = _amount;
        epochLength = _epoch;
        reqPubKey = _pubkey;
        thresholds[0] = _alpha;
        thresholds[1] = _beta;
        protestLength = _protest;
        blockNumber = block.number; // starte the clock!
        emit taskEmit(task);
        currState = contractState.RESPONDING;   // state of the contract is changed to allow the workers to submit their responses
    }

    
    // the submitResponse tuple: workers submit the encryptions
    function submitresponse_method_encryptions(bytes32 _e1, bytes32 _e2, bytes32 _e3, bytes32 _e4,
                    bytes32 _r1, bytes32 _r2) checkCurrState(contractState.RESPONDING) public {
                
        //require(responses_encryptions[msg.sender].exists==false, "A worker can only submit once!");   // check to see if the worker is not submitting again
        responses_encryptions[msg.sender] = workerresponse_encryptions([_e1, _e2], [_e3, _e4], [_r1, _r2], true);
    }
    
    // the submitResponse tuple: workers submits the quality commitment and the proof
    function submitresponse_method_commitment(bytes32 _alpha, bytes32 _beta, bytes32 _h1,
                    bytes32 _proof1, bytes32 _proof2, bytes32 _proof3, bytes32 _proof4) checkCurrState(contractState.RESPONDING) public {
	responses_data[msg.sender] = workerresponse_data([_alpha, _beta], _h1, [_proof1,_proof2,_proof3,_proof4]);
    	workerCount++;
        // condition check to move to the next state
        if (workerCount >= noOfWorkers) {
            currState = contractState.SUBMITAUTHCALC;  // state of the contract is changed to allow the requestor to submit the SNARK files
            workerCount = 0;        // re-initialize
        }
        
        // give the requester back its money if the noOfWorkers do not meet the threshold till the deadline
        if (workerCount < noOfWorkers && block.number > blockNumber + epochLength) {
            currState = contractState.DONE;
            workerCount=0;  // re-initialize
        }
    
    }

    /* OFF-CHAIN:
    (1) The requestor now checks all proofs
    (2) It then calculates the final answer; constructs its proof
    (3) Updates qualities zk-SNARK proof
    */

    /* ON-CHAIN:
    (1) For the SNARKs to work, the requestor first gives the IPFS hashes for all the files
    (2) It then gives the SUBMITQUALITY tuple which holds the new qualities and the 3 proofs
    (3) Then we pay the workers
    */
    
    
    // // the requestor adds how many workers were correct => the noOfWorkers that needs to be paid
    // function correctWorkers(uint256 _no) checkCurrState(contractState.PAYMENTS) isRequestor() public returns(bool) {
    //     noOfCorrectWorkers = _no;
    //     return true;
    // }
    
    // the requestor now submits the authcalc proofs
    function submitAuthCalc(bytes32 _p1, bytes32 _p2, bytes32 _p3, bytes32 _p4) checkCurrState(contractState.SUBMITAUTHCALC) isRequestor() public {
    
    	authcalc_proof_points[0]=_p1;
	authcalc_proof_points[1]=_p2;
	authcalc_proof_points[2]=_p3;
	authcalc_proof_points[3]=_p4;
    	
    	emit qualitySubmitted("Please verify final answer!");    // let the workers know you have submitted the quality
    	currState = contractState.SUBMITQUALITY;
    
    }

    // the requestor now submits the new qualities, i.e., SUBMITQUALITY
    function submitQuality_Com(bytes32[] memory _hash, bytes32[] memory _randomness, bytes32[] memory attack_rand, bytes32[]  memory _alpha, 
                bytes32[] memory _beta, uint256[] memory _pos) checkCurrState(contractState.SUBMITQUALITY) isRequestor() public {

        for (uint256 i = 0; i<noOfWorkers; i++) {
            workerTuples_Com[_hash[i]] = submitquality_ComTuple(_hash[i], _randomness[i], attack_rand[i], [_alpha[i], _beta[i]], _pos[i]);
        }

        emit qualitySubmitted("Please check you qualities!!");    // let the workers know you have submitted the quality
    }

    

    // the requestor now submits the new qualities, i.e., SUBMITQUALITY
    function submitQuality_Proofs(bytes32[] memory _hash, bytes32[] memory _p1, bytes32[] memory _p2, bytes32[] memory _p3, bytes32[] memory _p4, 
        bytes32[] memory _p5, bytes32[] memory _p6, bytes32[] memory _p7, bytes32[] memory _p8) checkCurrState(contractState.SUBMITQUALITY) isRequestor() public {

            for (uint256 i = 0; i<noOfWorkers; i++) {
                addProofs(_hash[i], _p1[i], _p2[i], _p3[i], _p4[i], _p5[i], _p6[i], _p7[i], _p8[i]);   
            }
            
            currState = contractState.PAYMENTS;
            workerCount = 0;    // re-initialize
            
            emit qualitySubmitted("Please check you qualities");    // let the workers know you have submitted the quality

    }
    
    function addProofs(bytes32 _hash, bytes32 _p1, bytes32 _p2, bytes32 _p3, bytes32 _p4, bytes32 _p5, 
        bytes32 _p6, bytes32 _p7, bytes32 _p8) private {
        workerTuples_Proof[_hash] = submitquality_ProofTuple([_p1,_p2,_p3, _p4], [_p5,_p6,_p7, _p8]);
    }
    
    // then we pay the workers
    function payWorkers(address[] memory _a, uint256[] memory _amt) checkCurrState(contractState.PAYMENTS) isRequestor() public returns(bool) {
        
        for (uint256 i = 0; i<noOfWorkers; i++) {
            if (_amt[i] != 0 ) {
                payable(_a[i]).transfer(amountToBePaid);   // pay
                budgetConsumed = budgetConsumed + amountToBePaid; // update the budget consumed
            }
        }
        
        currState = contractState.DONE;
        blockNumber = block.number;     // restart the clock for the protesting!
        
        return true;
    }
    
    // function to re-initialize all values for a new task
    function reinitValues() checkCurrState(contractState.DONE) protestOver() isRequestor() public {
        uint256 remValue = budget - budgetConsumed;
        payable(requestor).transfer(remValue); // first return the requestor the reamaining budget
        budget = 0;
        winner = 0;
        budgetConsumed = 0;
        maxFrequency = 0;
        workerCount = 0;
        currState = contractState.SETUP;    // changing the state of the contract to the setup phase for re-use
    }
    
} // contract ends
