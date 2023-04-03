 package com.Pythia.core;

import jnr.a64asm.SerializerIntrinsics_a64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;

import java.io.*;
import java.math.BigInteger ;
import java.util.*;

import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticEIP1559GasProvider;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import sun.jvm.hotspot.oops.ExceptionTableElement;

import static com.Pythia.core.CSTask_sol_crowdsourcingTask.*;


 public class requestorApp {
  private static final Logger log = LoggerFactory.getLogger(requestorApp.class);
     public static List<Long> workerTimes = new ArrayList<>();;
     // ---- Private keys for input
  // ---- Private keys for input



  static String[] worker_proofs = new String[10];

  // global datatypes
  static CSTask_sol_crowdsourcingTask CTcontract;
  static Credentials credentials_requestor;
  static Web3j web3j;
  static Web3ClientVersion web3ClientVersion;
  static String contractAddress;

  static String authQual_link;
  static String authCalc_link;
  static String authValue_link;
  static String _hash;
  static BigInteger _randomness;
  static List<String> _com;
  static List<String> _proofs;
  static CSTask_sol_crowdsourcingTask CTcontract_requestor;
  static String proveQualLink;
  static String finAnsEnc0="";
  static String finAnsEnc1="";
  static String finAnsEnc="";
  static ArrayList<String> workerAnsEnc = new ArrayList<String>();
  static ArrayList<String> workerQualCom = new ArrayList<String>();

  static long phase2StartTime;
  static long phase2EndTime;
  static long phase3StartTime;
  static long phase3EndTime;
  static String AnsRandomness;
  static Long authQualSetupTime0;
  static Long authQualSetupTime1;
  static Long authCalcSetupTime0;
  static Long authCalcSetupTime1;

  public static List<BigInteger> paymentTimes = new ArrayList<>();
  public static List<BigInteger> qualityTimes = new ArrayList<>();

  public static String reqAuthCalc0="";
  public static String reqAuthCalc1="";

  public static Long LPS0;
  public static Long LPS1;

 public static  List<Integer> correctWorker;

  // function to register the requestor and deploy the contract
  public static CSTask_sol_crowdsourcingTask deployContract(String requestor_key, Web3j web3j_temp) throws Exception {
    web3j = web3j_temp;
    credentials_requestor = Credentials.create(requestor_key);
      BigInteger maxPriorFees = BigInteger.valueOf(1_500_000_000L);
      BigInteger gasPrice = BigInteger.valueOf(1_500_000_000L);

      // chainID for Goerli : 5
      // for RINKEBY: 4
      StaticEIP1559GasProvider staticEIP1559GasProvider = new StaticEIP1559GasProvider(5, gasPrice, maxPriorFees, DefaultGasProvider.GAS_LIMIT);
      log.info(staticEIP1559GasProvider.getGasPrice() + " " + staticEIP1559GasProvider.getGasLimit());
      // load the smart contract
      //CTcontract = CSTask_sol_crowdsourcingTask.deploy(web3j, credentials_requestor,  BigInteger.valueOf(8_000_000),.send();
      CTcontract = CSTask_sol_crowdsourcingTask.deploy(web3j, credentials_requestor, staticEIP1559GasProvider).send();
      //CTcontract = CSTask_sol_crowdsourcingTask.deploy(web3j, credentials_requestor, gasPrice, DefaultGasProvider.GAS_LIMIT).send();

      contractAddress = CTcontract.getContractAddress();
      log.info("Smart contract deployed to address " + contractAddress);
      return CTcontract;

  } // function block

  // function to broadcast the task
  public static void taskBroadcast(String task_data, BigInteger noOfWorkers, BigInteger noOfChoices, BigInteger amountToBePaid,
                                   BigInteger epoch, BigInteger protest, String _pubkey, BigInteger _alpha, BigInteger _beta, BigInteger budget) throws Exception {



    // broadcast the task and send the budget
    TransactionReceipt receipt_task;
    receipt_task = CTcontract.broadcastTask(task_data, noOfWorkers, noOfChoices, amountToBePaid, epoch, protest, _pubkey, _alpha, _beta, budget).send();
    log.info("Broadcasting the task... " +  receipt_task.getTransactionHash());
    log.info("Gas Used to broadcast the task.. " + receipt_task.getGasUsed());



//     set up provequal
/*
    log.info("Compiling ProveQual...");
    Process proc = Runtime.getRuntime().exec("zokrates compile -i newProveQual.zok");
    proc.waitFor();
    proc = Runtime.getRuntime().exec("zokrates setup");
    proc.waitFor();
*/

    /* ignore */
//    // sending proving.key
//    proc = Runtime.getRuntime().exec("ipfs add proving.key");
//    Scanner scanner = new Scanner(proc.getInputStream());
//    scanner.useDelimiter("\r\n");
//    proveQualLink = scanner.next().split(" ")[1];
//    log.info("Proving Key available at: "  + proveQualLink);
//    scanner.close();
//    proc.waitFor();
    //proveQualLink = "QmZ7eyUaGmbR78g58uHQw25WfgvumMuH59Widy3Cr9Pfit";
  } // function block

/*
  // function to pay workers
  public static void payWorkers() throws Exception {
   System.out.println("I am in payments..");
   for (int i=0; i <32; i++) {
     payWorkers curr = new payWorkers(web3j, i, contractAddress, "0xCB30E8a186BaFC5e96738326c403e481bB03bd6D",0);
     curr.start();
     //curr.runMe();
   }
   //System.out.println("Worker Payment Times .. " + paymentTimes);
   //System.exit(0);
  }
  
// function to pay workers
  public static void payWorkers2() throws Exception {
   System.out.println("I am in payments..");
   for (int i=32; i <64; i++) {
     payWorkers curr = new payWorkers(web3j, i, contractAddress, "0xCB30E8a186BaFC5e96738326c403e481bB03bd6D",1);
     curr.start();
     //curr.runMe();
   }
   //System.out.println("Worker Payment Times .. " + paymentTimes);
   //System.exit(0)
}
  public static void payWorkers3() throws Exception {
   System.out.println("I am in payments..");
   for (int i=64; i <96; i++) {
     payWorkers curr = new payWorkers(web3j, i, contractAddress, "0xCB30E8a186BaFC5e96738326c403e481bB03bd6D",2);
     curr.start();
     //curr.runMe();
   }
   //System.out.println("Worker Payment Times .. " + paymentTimes);
   //System.exit(0)
}
 
  public static void payWorkers4() throws Exception {
   System.out.println("I am in payments..");
   for (int i=64; i <128; i++) {
     payWorkers curr = new payWorkers(web3j, i, contractAddress, "0xCB30E8a186BaFC5e96738326c403e481bB03bd6D",3);
     curr.start();
     //curr.runMe();
   }
   //System.out.println("Worker Payment Times .. " + paymentTimes);
   //System.exit(0)
}

*/

  // function to for provequal verification
  public static void verifyProveQual() throws Exception {
    phase2StartTime = System.nanoTime();
    log.info("Starting verification....");
    // verify the proofs for each worker
    for (int i = 0; i<App.workers_required; i++) {
      Process proc = Runtime.getRuntime().exec("zokrates verify ");
      Scanner scanner = new Scanner(proc.getInputStream());
      scanner.useDelimiter("\r\n");
      while (scanner.hasNext()) {
        System.out.println(scanner.next());
      }
      scanner.close();
      proc.waitFor();
    }
    log.info("Verification completed! Moving to aggregation...");
    localCalc();
  }

  // function that performs AuthCalc
  public static void requesterAuthCalc() throws Exception {
    log.info("Performing AuthCalc Setup...");
    authCalcSetupTime0 = System.nanoTime();
    Process proc = Runtime.getRuntime().exec("zokrates compile -i finAvgauthcacl.zok");
    proc.waitFor();
    proc = Runtime.getRuntime().exec("zokrates setup");
    proc.waitFor();
    authCalcSetupTime1 = System.nanoTime();
    log.info("Generating AuthCalc Proof...");

    proc = Runtime.getRuntime().exec("python authcalcRunner.py");
    proc.waitFor();

    // now start quality computations
    localQual();
  }

  public static void localCalc() throws Exception {
    log.info("Computing final answer locally...");
    // out the encrypted final answer
    Process proc = Runtime.getRuntime().exec("python localAuthCalc.py " + App.workers_required);
    Scanner scanner = new Scanner(proc.getInputStream());
    scanner.useDelimiter("\r\n");
    List<String> statement = Arrays.asList(scanner.next().split("\n"));    
  //finAnsEnc0 = statement.get(0) + " " + statement.get(1) + " " + statement.get(4) + " " + statement.get(5) + " " + statement.get(8) + " " + statement.get(9);
    //finAnsEnc1 = statement.get(2) + " " + statement.get(3) + " " + statement.get(6) + " " + statement.get(7) + " " + statement.get(10) + " " + statement.get(11);
    //finAnsEnc = statement.get(0) + " " + statement.get(1) + " " + statement.get(2) + " " + statement.get(3) + " " + statement.get(4) + " " + statement.get(5) + " " + statement.get(6) + " " +
   //             statement.get(7) + " " + statement.get(8) + " " + statement.get(9) + " " + statement.get(10) + " " + statement.get(11);
    finAnsEnc = statement.get(0) + " " + statement.get(1) + " " + statement.get(2) + " " + statement.get(3);
    //String randomness = statement.get(12) + " " + statement.get(13) + " " + statement.get(14);
    String randomness = statement.get(4);
    //String yaxis = statement.get(15) + " " + statement.get(16) + " " + statement.get(17);
    String yaxis = statement.get(5);
    log.info("Final Answer's Encryption... " + finAnsEnc);
    scanner.close();
    proc.waitFor();
  
    // create the authcalc witness
    BufferedWriter writer = new BufferedWriter(new FileWriter("requester.txt"));
    
/*   
 writer.write(" 16540640123574156134436876038791482806971768689494387082833631921987005038935 20819045374670962167435360035096875258406992893633759881276124905556507972311 " +
            "17324563846726889236817837922625232543153115346355010501047597319863650987830 20022170825455209233733649024450576091402881793145646502279487074566492066831 2 " +
            finAnsEnc0 + " " + finAnsEnc1 + " " + requestorApp.reqAuthCalc0  + " " + requestorApp.reqAuthCalc1  + " " + randomness + " " + yaxis);
 */  


  
    writer.write(" 16540640123574156134436876038791482806971768689494387082833631921987005038935 20819045374670962167435360035096875258406992893633759881276124905556507972311 " +
            "17324563846726889236817837922625232543153115346355010501047597319863650987830 20022170825455209233733649024450576091402881793145646502279487074566492066831 2 " +
            finAnsEnc + " " + requestorApp.reqAuthCalc0  + " " + requestorApp.reqAuthCalc1  + " " + randomness + " " + yaxis);
    
    writer.close();

    // call authcalc
    //requesterAuthCalc();
//System.exit(0);
    localQual();

  }

  public static void localQual() throws Exception {
    log.info("Computing new qualities locally...");
    for (int i=0; i<App.workers_required; i++) {
      Process proc = Runtime.getRuntime().exec("python localQual.py " + finAnsEnc + " " + workerAnsEnc.get(i) + " " + workerQualCom.get(i));
      Scanner scanner = new Scanner(proc.getInputStream());
      scanner.useDelimiter("\r\n");
      List<String> quality = Arrays.asList(scanner.next().split("\n"));
      String myQuality = quality.get(0) + " " + quality.get(1) + " " + quality.get(2) + " " + quality.get(3);
      String randomness = quality.get(4) + " " + quality.get(5);
      scanner.close();
      proc.waitFor();
      String filename = "authqualWitness" + String.valueOf(i) + ".txt";

      /* first complete witness file */
      log.info("New Quality for worker " + String.valueOf(i) + " ... " + myQuality);
      log.info("Computing AuthQual witness for worker " + String.valueOf(i));
      BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
  
/*    
      writer.write("16540640123574156134436876038791482806971768689494387082833631921987005038935 20819045374670962167435360035096875258406992893633759881276124905556507972311 " +
              "17324563846726889236817837922625232543153115346355010501047597319863650987830 20022170825455209233733649024450576091402881793145646502279487074566492066831 2 " +
              myQuality + " " +  workerAnsEnc.get(i) + " " + finAnsEnc0 + " " + finAnsEnc1 + " " + workerQualCom.get(i) + " " + randomness
      );
  */    
      
      writer.write("16540640123574156134436876038791482806971768689494387082833631921987005038935 20819045374670962167435360035096875258406992893633759881276124905556507972311 " +
              "17324563846726889236817837922625232543153115346355010501047597319863650987830 20022170825455209233733649024450576091402881793145646502279487074566492066831 2 " +
              myQuality + " " +  workerAnsEnc.get(i) + " " + finAnsEnc + " " + workerQualCom.get(i) + " " + randomness
      );
      
      writer.close();
    }
    requesterAuthQual();
//    System.exit(0);
requesterAuthValue();
  }

  // function that performs AuthQual
  public static void requesterAuthQual() throws Exception {
    log.info("Performing AuthQual Setup...");
    authQualSetupTime0 = System.nanoTime();
    Process proc = Runtime.getRuntime().exec("zokrates compile -i finavgauthqual.zok");
    proc.waitFor();
    proc = Runtime.getRuntime().exec("zokrates setup");
    proc.waitFor();
    authQualSetupTime1 = System.nanoTime();
    for (int i=0; i<App.workers_required; i++) {

      log.info("Generating AuthQual proof for Worker... " + String.valueOf(i));
      String filename = "authqualWitness" + String.valueOf(i) + ".txt";
      proc = Runtime.getRuntime().exec("python authqualRunner.py " + filename);
      proc.waitFor();

      // emitting the new worker's quality
      /* ignore */
//      ArrayList<String> tempFinAns = new ArrayList<String>();
//      File encData = new File("./witness");
//      BufferedReader br = new BufferedReader(new FileReader(encData));
//      String tempQualCom=br.readLine().split(" ")[1] + " ";
//      tempQualCom+=br.readLine().split(" ")[1] + " ";
//      tempQualCom+=br.readLine().split(" ")[1] + " ";
//      tempQualCom+=br.readLine().split(" ")[1] + " ";
//      tempQualCom = tempQualCom.replace("null","");
//      log.info("New Quality Commitment for Worker " + String.valueOf(i) + " ... " + tempQualCom);
//      proc = Runtime.getRuntime().exec("rm witness");
//      proc.waitFor();

      // add this to the merkle tree
    }

    requesterAuthValue();
  }

  // function that performs AuthValue
  public static void requesterAuthValue() throws Exception {
    
  Long authValueSetupTime0 = System.nanoTime();
    log.info("Performing AuthValue Setup...");
    Process proc = Runtime.getRuntime().exec("zokrates compile -i finagvauthvalue.zok");
    proc.waitFor();
    proc = Runtime.getRuntime().exec("zokrates setup");
    proc.waitFor();
    Long authValueSetupTime1 = System.nanoTime();

    for (int i=0; i<App.workers_required; i++) {
      log.info("Computing AuthValue witness for worker " + String.valueOf(i));
      String filename = "authvalueWitness" + String.valueOf(i) + ".txt";

      /* first complete witness file */
      log.info("Completing AuthValue witness files...");
      BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
      
      
writer.write("16540640123574156134436876038791482806971768689494387082833631921987005038935 20819045374670962167435360035096875258406992893633759881276124905556507972311 " +
              "17324563846726889236817837922625232543153115346355010501047597319863650987830 20022170825455209233733649024450576091402881793145646502279487074566492066831 2 " +
              workerAnsEnc.get(i) + " " + finAnsEnc + " "
      );


/*           
      
      writer.write("16540640123574156134436876038791482806971768689494387082833631921987005038935 20819045374670962167435360035096875258406992893633759881276124905556507972311 " +
              "17324563846726889236817837922625232543153115346355010501047597319863650987830 20022170825455209233733649024450576091402881793145646502279487074566492066831 2 " +
              workerAnsEnc.get(i) + " " + finAnsEnc0 + " " + finAnsEnc1 + " "
      );
  */    
      writer.close();
      log.info("Generating AuthValue proof..");
      proc = Runtime.getRuntime().exec("python authvalueRunner.py " + filename);
      proc.waitFor();
    }

    phase2EndTime = System.nanoTime();
    System.out.println("Phase 2 Time: " + (phase2EndTime - phase2StartTime - (authValueSetupTime1 - authValueSetupTime0)
            - (authQualSetupTime1-authQualSetupTime0) - (authCalcSetupTime1-authCalcSetupTime0)
    ));
    System.exit(0);
    //submitQuality();
  }


  /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static void submitQuality() throws Exception {
    LPS0 = System.nanoTime();
    for (int i=0; i<32; i++) {
      submitQuality curr = new submitQuality(web3j, i, contractAddress, 0);
      //curr.runMe();
      curr.start();
    }
    //System.out.println("Worker Quality Times.." + qualityTimes);
    //payWorkers();
  }
  
 public static void submitQuality2() throws Exception {
    //LPS0 = System.nanoTime();
    for (int i=32; i<64; i++) {
      submitQuality curr = new submitQuality(web3j, i, contractAddress, 1);
      //curr.runMe();
      curr.start();
    }
    //System.out.println("Worker Quality Times.." + qualityTimes);
    //payWorkers();
  }


 public static void submitQuality3() throws Exception {
    //LPS0 = System.nanoTime();
    for (int i=64; i<96; i++) {
      submitQuality curr = new submitQuality(web3j, i, contractAddress, 2);
      //curr.runMe();
      curr.start();
    }
    //System.out.println("Worker Quality Times.." + qualityTimes);
    //payWorkers();
  }


 public static void submitQuality4() throws Exception {
    //LPS0 = System.nanoTime();
    for (int i=96; i<App.workers_required; i++) {
      submitQuality curr = new submitQuality(web3j, i, contractAddress, 3);
      //curr.runMe();
      curr.start();
    }
    //System.out.println("Worker Quality Times.." + qualityTimes);
    //payWorkers();
  }
   */

     /*
  public static void parallelSQ() throws Exception {
      LPS0 = System.nanoTime();
      EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(credentials_requestor.getAddress(), DefaultBlockParameterName.PENDING).send();
      BigInteger nonce1 = ethGetTransactionCount.getTransactionCount();
      for (int i=0 ; i < App.workers_required; i++) {
          submitQuality curr = new submitQuality(web3j, i, contractAddress, 3, nonce1);
          //curr.runMe();
          curr.start();
      }

  }
*/

  public static void allinOneSQ() throws Exception {

      log.info("Time taken by workers... ");
      System.out.println(workerTimes);

      LPS0 = System.nanoTime();


      List<Bytes32> _HASH = new ArrayList<>();
      List<Bytes32> _RANDOMNESS = new ArrayList<>();
      List<Bytes32> _ATTACK = new ArrayList<>();
      List<Bytes32> _COM_ALPHA = new ArrayList<>();
      List<Bytes32> _COM_BETA = new ArrayList<>();
      List<Bytes32> _PROOFS_0 = new ArrayList<>();
      List<Bytes32> _PROOFS_1 = new ArrayList<>();
      List<Bytes32> _PROOFS_2 = new ArrayList<>();
      List<Bytes32> _PROOFS_3 = new ArrayList<>();
      List<Bytes32> _PROOFS_4 = new ArrayList<>();
      List<Bytes32> _PROOFS_5 = new ArrayList<>();
      List<Bytes32> _PROOFS_6 = new ArrayList<>();
      List<Bytes32> _PROOFS_7 = new ArrayList<>();
      List<Uint256> _POSITION = new ArrayList<>();
      List<Address> _ADDR = new ArrayList<>();
      List<Uint256> _AMT = new ArrayList<>();
      List<String> _proofs = new ArrayList<>();
      //int[] responses = {8,9,9,8,9,8,5,9,8,8,9,9,9,8,8,2,9,8,9,9,9,9,2,9,9,9,8,2,9,9,9,9,9,2,5,9,9,9,9,9,8,2,8,2,9,8,9,2,2,9,8,5,8,2,9,9,2,9,2,2,8,2,9,9,9,8,9,9,8,9};
      int[] responses = {9,5,9,5,5,5,2,5,2,5,5,5,5,5,2,9,5,2,5,5,5,5,5,5,5,5,2,2,5,5,2,5,9,5,9,5,5,5,5,5,5,5,5,5,5,5,5,9,5,5,9,8,8,5,5,5,5,5,5,5,9,5,5,9,5,5,5,8,5,5,5,5,5,5,5,9,5,8,5,5,2,9,5,9,5,5,9,5,5,9,9,5,5,5,5,9,9,2,5,9,9,5,5,5,5,5,5,5,5,5,5,5,5,5,5,8,5,5,5,9,9,5,5,8,5,9,5,8,9,5,5,5,5,9,5};




      // get the data
      for (int i=0; i<App.workers_required; i++) {
          File workerData = new File("./qualityData/worker" + i + ".txt");
          BufferedReader br = null;
          br = new BufferedReader(new FileReader(workerData));

          BigInteger _hash = new BigInteger(br.readLine());
          _HASH.add(new org.web3j.abi.datatypes.generated.Bytes32(_hash.toByteArray()));

          BigInteger _randomness = new BigInteger(br.readLine());
          _RANDOMNESS.add(new org.web3j.abi.datatypes.generated.Bytes32(_randomness.toByteArray()));


          BigInteger _randomnessAttack = new BigInteger(br.readLine());
          _ATTACK.add(new org.web3j.abi.datatypes.generated.Bytes32(_randomnessAttack.toByteArray()));


          List<String> _com = Arrays.asList(br.readLine().split(","));
          _COM_ALPHA.add(new org.web3j.abi.datatypes.generated.Bytes32(new BigInteger(_com.get(0)).toByteArray()));
          _COM_BETA.add(new org.web3j.abi.datatypes.generated.Bytes32(new BigInteger(_com.get(1)).toByteArray()));

          _proofs = Arrays.asList(br.readLine().split(","));
          _PROOFS_0.add(new org.web3j.abi.datatypes.generated.Bytes32(new BigInteger(_proofs.get(0)).toByteArray()));
          _PROOFS_1.add(new org.web3j.abi.datatypes.generated.Bytes32(new BigInteger(_proofs.get(1)).toByteArray()));
          _PROOFS_2.add(new org.web3j.abi.datatypes.generated.Bytes32(new BigInteger(_proofs.get(2)).toByteArray()));
          _PROOFS_3.add(new org.web3j.abi.datatypes.generated.Bytes32(new BigInteger(_proofs.get(3)).toByteArray()));
          _PROOFS_4.add(new org.web3j.abi.datatypes.generated.Bytes32(new BigInteger(_proofs.get(4)).toByteArray()));
          _PROOFS_5.add(new org.web3j.abi.datatypes.generated.Bytes32(new BigInteger(_proofs.get(5)).toByteArray()));
          _PROOFS_6.add(new org.web3j.abi.datatypes.generated.Bytes32(new BigInteger(_proofs.get(6)).toByteArray()));
          _PROOFS_7.add(new org.web3j.abi.datatypes.generated.Bytes32(new BigInteger(_proofs.get(7)).toByteArray()));


          BigInteger _position = BigInteger.valueOf(Long.parseLong(br.readLine()));
          _POSITION.add(new Uint256(_position));

          Address addr = new org.web3j.abi.datatypes.Address(160, Credentials.create(workerResponse.workersData[i]).getAddress());

          _ADDR.add(addr);
          if (responses[i]==5) _AMT.add(new Uint256(1));
          else _AMT.add(new Uint256(0));



      }

      // getting EIP 1559 ready
      BigInteger maxPriorFees = BigInteger.valueOf(1_000_000_000L);
      BigInteger gasPrice = BigInteger.valueOf(1_000_000_000L);

      // first send the authcalc proof

      StaticEIP1559GasProvider staticEIP1559GasProvider = new StaticEIP1559GasProvider(5, gasPrice ,  maxPriorFees, DefaultGasProvider.GAS_LIMIT);
      CTcontract_requestor = CSTask_sol_crowdsourcingTask.load(contractAddress, web3j, credentials_requestor, staticEIP1559GasProvider);
      TransactionReceipt receipt0 = CTcontract_requestor.submitAuthCalc(new BigInteger(_proofs.get(0)).toByteArray(),new BigInteger(_proofs.get(1)).toByteArray(),
                    new BigInteger(_proofs.get(2)).toByteArray(),new BigInteger(_proofs.get(3)).toByteArray()).send();

      log.info("Gas used for authcalc..... " + receipt0.getGasUsed());

      ////////////////////////////////////

      List<Type> inputParameters = new ArrayList<>();
      inputParameters.add(new DynamicArray<>(Bytes32.class, _HASH));
      inputParameters.add(new DynamicArray<>(Bytes32.class, _RANDOMNESS));
      inputParameters.add(new DynamicArray<>(Bytes32.class, _ATTACK));
      inputParameters.add(new DynamicArray<>(Bytes32.class, _COM_ALPHA));
      inputParameters.add(new DynamicArray<>(Bytes32.class, _COM_BETA));
      inputParameters.add(new DynamicArray<>(Uint256.class, _POSITION));

      Function function1 = new Function(
              FUNC_SUBMITQUALITY_COM,
              inputParameters,
              Collections.emptyList()
      );

      String data = FunctionEncoder.encode(function1);

      EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(credentials_requestor.getAddress(), DefaultBlockParameterName.PENDING).send();

      RawTransaction rawTransaction = RawTransaction.createTransaction(
              5,
              ethGetTransactionCount.getTransactionCount(),
              DefaultGasProvider.GAS_LIMIT,
              contractAddress,
              BigInteger.ZERO, // No Value
              data,
              maxPriorFees,
              gasPrice
      );

      RawTransactionManager transactionManager = new RawTransactionManager(web3j, credentials_requestor);
      EthSendTransaction receipt1 = transactionManager.signAndSend(rawTransaction);

      // preparing input parameters to submit quality proofs
      List<Type> inputParameters2 = new ArrayList<>();
      inputParameters2.add(new DynamicArray<>(Bytes32.class, _HASH));
      inputParameters2.add(new DynamicArray<>(Bytes32.class, _PROOFS_0));
      inputParameters2.add(new DynamicArray<>(Bytes32.class, _PROOFS_1));
      inputParameters2.add(new DynamicArray<>(Bytes32.class, _PROOFS_2));
      inputParameters2.add(new DynamicArray<>(Bytes32.class, _PROOFS_3));
      inputParameters2.add(new DynamicArray<>(Bytes32.class, _PROOFS_4));
      inputParameters2.add(new DynamicArray<>(Bytes32.class, _PROOFS_5));
      inputParameters2.add(new DynamicArray<>(Bytes32.class, _PROOFS_6));
      inputParameters2.add(new DynamicArray<>(Bytes32.class, _PROOFS_7));



      log.info("Submitted Quality Commitments .. " +  receipt1.getTransactionHash());
      log.info("Waiting for confirmation receipt.... ");
      waitForTx(receipt1.getTransactionHash()); // wait for tx to be mined

      Function function2 = new Function(
              FUNC_SUBMITQUALITY_PROOFS,
        inputParameters2,
        Collections.emptyList()
      );

      String data2 = FunctionEncoder.encode(function2);

      ethGetTransactionCount = web3j.ethGetTransactionCount(credentials_requestor.getAddress(), DefaultBlockParameterName.PENDING).send();

      RawTransaction rawTransaction2 = RawTransaction.createTransaction(
              5,
              ethGetTransactionCount.getTransactionCount(),
              DefaultGasProvider.GAS_LIMIT,
              contractAddress,
              BigInteger.ZERO, // No Value
              data2,
              maxPriorFees,
              gasPrice
      );

      transactionManager = new RawTransactionManager(web3j, credentials_requestor);
      EthSendTransaction receipt2 = transactionManager.signAndSend(rawTransaction2);

      log.info("Submitted Quality Proofs .. " +  receipt2.getTransactionHash());

      log.info("Waiting for confirmation receipt.... ");
      waitForTx(receipt2.getTransactionHash()); // wait for tx to be mined

      Optional<TransactionReceipt> R1 =  web3j.ethGetTransactionReceipt(receipt1.getTransactionHash()).send().getTransactionReceipt();
      Optional<TransactionReceipt> R2 =  web3j.ethGetTransactionReceipt(receipt2.getTransactionHash()).send().getTransactionReceipt();


      log.info("Total Gas used for the worker to submit quality ..." + R1.get().getGasUsed().add(R2.get().getGasUsed()));

      // moving to payments
      List<Type> inputParameters3 = new ArrayList<>();
      inputParameters3.add(new DynamicArray<>(Address.class, _ADDR));
      inputParameters3.add(new DynamicArray<>(Uint256.class, _AMT));

      Function function3 = new Function(
              FUNC_PAYWORKERS,
              inputParameters3,
              Collections.emptyList()
      );

      String data3 = FunctionEncoder.encode(function3);

      ethGetTransactionCount = web3j.ethGetTransactionCount(credentials_requestor.getAddress(), DefaultBlockParameterName.PENDING).send();

      RawTransaction rawTransaction3 = RawTransaction.createTransaction(
              5,
              ethGetTransactionCount.getTransactionCount(),
              DefaultGasProvider.GAS_LIMIT,
              contractAddress,
              BigInteger.ZERO, // No Value
              data3,
              maxPriorFees,
              gasPrice
      );

      transactionManager = new RawTransactionManager(web3j, credentials_requestor);
      EthSendTransaction receipt3 = transactionManager.signAndSend(rawTransaction3);

      log.info("Submitted PAYMENTS .. " +  receipt3.getTransactionHash());
      log.info("Waiting for confirmation receipt.... ");
      waitForTx(receipt3.getTransactionHash()); // wait for tx to be mined

      Optional<TransactionReceipt> R3 =  web3j.ethGetTransactionReceipt(receipt3.getTransactionHash()).send().getTransactionReceipt();

      log.info("Total Gas used to pay the worker ..." + R3.get().getGasUsed());

      LPS1 = System.nanoTime();

      log.info("Total time taken for this phase.... ");
      System.out.println(LPS1 - LPS0);


      System.exit(0);

  } // submit quality class

     public static void waitForTx(String txHash) throws Exception {

         while (true) {
             EthGetTransactionReceipt transactionReceipt = web3j
                     .ethGetTransactionReceipt(txHash)
                     .send();
             if (transactionReceipt.getResult() != null) {
                 break;
             }
             Thread.sleep(15000);
         }

     }

/*
    public static void parallelPAY() throws Exception {

        System.out.println("I am in payments..");
        for (int i=0; i <App.workers_required; i++) {
            payWorkers curr = new payWorkers(web3j, i, contractAddress, "0xb0Dd1aaef153e50B680F9dc4F2F3C583579dE762",0);
            curr.start();
            //curr.runMe();
        }

    }
*/
/*
  public static void payWorkerUI() throws Exception {
    JFrame frame = new JFrame("Submit SNARKs");
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());
    JLabel label_0 = new JLabel("Enter Address to Pay");
    JLabel label_1 = new JLabel("Last Worker (Y/N)");
    JButton button = new JButton();
    button.setText("OK");
    JTextField textField_0 = new JTextField();
    textField_0.setPreferredSize(new Dimension(150, 25));
    JTextField textField_1 = new JTextField();
    textField_1.setPreferredSize(new Dimension(150, 25));

    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String address = textField_0.getText();
        String decision = textField_1.getText();


        // load the values to the contract
        CTcontract_requestor = CSTask_sol_crowdsourcingTask.load(contractAddress, web3j, credentials_requestor, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
        try {
          log.info("Paying the worker.. " + address);
          log.info("Transaction hash.. " + CTcontract_requestor.payWorkers(address, "Bro").send().getTransactionHash());
          if (decision == "Y") {
            log.info("Thanks for participating");
            log.info("Re-initializing Values.. " + CTcontract_requestor.reinitValues().send().getTransactionHash());
          }
          else {
            payWorkers(); // recall the function to pay others
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }

      }
    });

    panel.add(label_0);
    panel.add(textField_0);
    panel.add(label_1);
    panel.add(textField_1);
    panel.add(button);
    frame.add(panel);
    frame.setSize(300, 450);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  } // function block

  // SNARK verification prompt
  public void SNARKprompt() {
    JFrame frame = new JFrame("Worker Input");
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());
    JLabel label_0 = new JLabel("Hit OK if verification completed!");
    JButton button = new JButton();
    button.setText("OK");
    button.addActionListener(new ActionListener() {
 va.lang.String,java.lang.String     @Override
      public void actionPerformed(ActionEvent e) {
        try {
          // TODO: add the next step
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });

    frame.add(panel);
    frame.setSize(200, 200);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  } // function block
*/

} // class block
