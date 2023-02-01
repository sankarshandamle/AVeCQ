package com.Pythia.core;


import java.io.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticEIP1559GasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static com.Pythia.core.CSTask_sol_crowdsourcingTask.FUNC_SUBMITQUALITY_COM;
import static com.Pythia.core.CSTask_sol_crowdsourcingTask.FUNC_SUBMITQUALITY_PROOFS;

public class submitQuality extends Thread {

    Web3j web3j;
    private final Logger log = LoggerFactory.getLogger(submitQuality.class);
    int currWorker;
    String contractAddress;
    private CSTask_sol_crowdsourcingTask CTcontract_requestor;
    static Credentials credentials_requestor;
    private int phase;
    BigInteger nonce1;
    BigInteger nonce2;
    BigInteger txCount;


    public submitQuality(Web3j x, int y, String z, int _p, BigInteger _tx) throws Exception {
        web3j = x;
        currWorker = y;
        contractAddress = z;
        phase = _p;
        txCount = _tx;
        log.info("Requester nonce is...... " + _tx);
    }

    public void run() {
        Long t0 = null,t1 = null;
        File workerData = new File("./qualityData/worker" + currWorker + ".txt");
        BufferedReader br = null;
        try {
            
		br = new BufferedReader(new FileReader(workerData));

            String _hash = br.readLine();
            BigInteger _randomness = new BigInteger(br.readLine());
            List<String> _com = Arrays.asList(br.readLine().split(","));
            List<String> _proofs = Arrays.asList(br.readLine().split(","));
            BigInteger _position = BigInteger.valueOf(Long.parseLong(br.readLine()));

        // now send these values to the smart contract
	BigInteger maxPriorFees = BigInteger.valueOf(1_100_000_000L);
    BigInteger gasPrice = BigInteger.valueOf(5_000_000_000L);
            //credentials_requestor = Credentials.create(workerResponse.workersData[currWorker%32]);
            credentials_requestor = Credentials.create("0df4a5343622e882842e6495c599929d4bfd3ee91c07b220b9ff24f9f455d303");
        t0 = System.nanoTime();
            StaticEIP1559GasProvider staticEIP1559GasProvider = new StaticEIP1559GasProvider(5, gasPrice ,  maxPriorFees, DefaultGasProvider.GAS_LIMIT);
        CTcontract_requestor = CSTask_sol_crowdsourcingTask.load(contractAddress, web3j, credentials_requestor, staticEIP1559GasProvider);

        /* fast raw transaction */
           // FastRawTransactionManager fastRawTxMgr =new FastRawTransactionManager(web3j, credentials_requestor, new PollingTransactionReceiptProcessor(web3j, 3000, 40));
        //    CTcontract_requestor = CSTask_sol_crowdsourcingTask.load(contractAddress, web3j, fastRawTxMgr, staticEIP1559GasProvider);


            /* change the transaction nonce */

            List<Bytes32> byteInput = new ArrayList<>();
            byteInput.add(CSTask_sol_crowdsourcingTask.stringToBytes32(new BigInteger(_hash).toByteArray()));
            byteInput.add(CSTask_sol_crowdsourcingTask.stringToBytes32( _randomness.toByteArray()));
            byteInput.add(CSTask_sol_crowdsourcingTask.stringToBytes32( new BigInteger(_com.get(0)).toByteArray()));
            byteInput.add(CSTask_sol_crowdsourcingTask.stringToBytes32( new BigInteger(_com.get(1)).toByteArray()));
            List<Uint256> uintInput = new ArrayList<>();
            uintInput.add(new Uint256(_position));

            List<Type> inputParameters = new ArrayList<>();
            inputParameters.add(new DynamicArray<>(Bytes32.class, byteInput));
            inputParameters.add(new DynamicArray<>(Uint256.class, uintInput));

            Function function1 = new Function(
                    FUNC_SUBMITQUALITY_COM,
                    inputParameters,
                    Collections.emptyList()
            );

	String data = FunctionEncoder.encode(function1);
    //EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(credentials_requestor.getAddress(), DefaultBlockParameterName.PENDING).send();
    nonce1 = txCount.add(BigInteger.valueOf(currWorker+1));

	RawTransaction rawTransaction = RawTransaction.createTransaction(
        	nonce1,
       		gasPrice,
        	DefaultGasProvider.GAS_LIMIT,
        	contractAddress,
        	BigInteger.ZERO, // No Value
        	data
	);

	RawTransactionManager transactionManager = new RawTransactionManager(web3j, credentials_requestor);
	EthSendTransaction receipt1 = transactionManager.signAndSend(rawTransaction);


        log.info("Submitted Transaction .. for .. " + String.valueOf(currWorker) + receipt1.getTransactionHash());

        /*~~~~~~~~~~~~~~~~~~~~~ submitquality_PROOFS  ~~~~~~~~~~~~~~~~~~~~~~~~~*/

            List<Bytes32> byteInput2 = new ArrayList<>();
            byteInput2.add(CSTask_sol_crowdsourcingTask.stringToBytes32(new BigInteger(_hash).toByteArray()));
            byteInput2.add(CSTask_sol_crowdsourcingTask.stringToBytes32(new BigInteger(_proofs.get(0)).toByteArray()));
            byteInput2.add(CSTask_sol_crowdsourcingTask.stringToBytes32(new BigInteger(_proofs.get(1)).toByteArray()));
            byteInput2.add(CSTask_sol_crowdsourcingTask.stringToBytes32(new BigInteger(_proofs.get(2)).toByteArray()));
            byteInput2.add(CSTask_sol_crowdsourcingTask.stringToBytes32(new BigInteger(_proofs.get(3)).toByteArray()));
            byteInput2.add(CSTask_sol_crowdsourcingTask.stringToBytes32(new BigInteger(_proofs.get(4)).toByteArray()));
            byteInput2.add(CSTask_sol_crowdsourcingTask.stringToBytes32(new BigInteger(_proofs.get(5)).toByteArray()));
            byteInput2.add(CSTask_sol_crowdsourcingTask.stringToBytes32(new BigInteger(_proofs.get(6)).toByteArray()));
            byteInput2.add(CSTask_sol_crowdsourcingTask.stringToBytes32(new BigInteger(_proofs.get(7)).toByteArray()));

            List<Type> inputParameters2 = new ArrayList<>();
            inputParameters2.add(new DynamicArray<>(Bytes32.class, byteInput2));



            final Function function2 = new Function(
                FUNC_SUBMITQUALITY_PROOFS,
                    inputParameters2,
                    Collections.<TypeReference<?>>emptyList()
	        );

         data = FunctionEncoder.encode(function2);

         nonce2 = txCount.add(BigInteger.valueOf(2*(currWorker+1)));

	  rawTransaction = RawTransaction.createTransaction(
                nonce2,
                gasPrice,
                DefaultGasProvider.GAS_LIMIT,
                contractAddress,
                BigInteger.ZERO, // No Value
                data
        );

        transactionManager = new RawTransactionManager(web3j, credentials_requestor);
        EthSendTransaction receipt2 = transactionManager.signAndSend(rawTransaction);

        Optional<TransactionReceipt> R1 =  web3j.ethGetTransactionReceipt(receipt1.getTransactionHash()).send().getTransactionReceipt();
        Optional<TransactionReceipt> R2 =  web3j.ethGetTransactionReceipt(receipt2.getTransactionHash()).send().getTransactionReceipt();

        log.info("Submitted Transaction .. for .. " + String.valueOf(currWorker) + receipt1.getTransactionHash());

            //log.info("Updated quality data for Worker + " + currWorker + "uploaded at..." + receipt1.getTransactionHash() + " and " + receipt2.getTransactionHash());
            log.info("Total Gas used for the worker to submit quality ..." + R1.get().getGasUsed().add(R2.get().getGasUsed()));
            t1 = System.nanoTime();
            requestorApp.qualityTimes.add(BigInteger.valueOf(t1-t0));
           // requestorApp.qualityTimes.add(receipt1.==().add(receipt2.==()));


	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

            /*

       TransactionReceipt receipt1 =  CTcontract_requestor.submitQuality_Com(new BigInteger(_hash).toByteArray(), _randomness.toByteArray(), new BigInteger(_com.get(0)).toByteArray(),
                new BigInteger(_com.get(1)).toByteArray(), _position).send();
        log.info("Submitted Transaction .. for .. " + String.valueOf(currWorker));
        TransactionReceipt receipt2 = CTcontract_requestor.submitQuality_Proofs(new BigInteger(_hash).toByteArray(), new BigInteger(_proofs.get(0)).toByteArray(), new BigInteger(_proofs.get(1)).toByteArray(),
                new BigInteger(_proofs.get(2)).toByteArray(), new BigInteger(_proofs.get(3)).toByteArray(), new BigInteger(_proofs.get(4)).toByteArray(),
                new BigInteger(_proofs.get(5)).toByteArray(), new BigInteger(_proofs.get(6)).toByteArray(), new BigInteger(_proofs.get(7)).toByteArray()).send();
        //Thread.sleep(100000);
       
        log.info("Updated quality data for Worker + " + currWorker + "uploaded at..." + receipt1.getTransactionHash() + " and " + receipt2.getTransactionHash());
        log.info("Total Gas used for the worker to submit quality ..." + receipt1.getGasUsed().add(receipt2.getGasUsed()));


            t1 = System.nanoTime();
            requestorApp.qualityTimes.add(BigInteger.valueOf(t1-t0));
*/

        } catch (Exception e) {
            e.printStackTrace();
        }
//        requestorApp.qualityTimes.add(t1-t0);
	//if (currWorker%32 == (App.workers_required-1) % 32 || currWorker == App.workers_required -1 ) {
        try {
            if (currWorker == App.workers_required - 1) {
                Thread.sleep(200000);
                System.out.println("SQ Times: " + requestorApp.qualityTimes);
                System.exit(0);
                /*
                if (phase == 11) {
                    requestorApp.submitQuality2();
                } else if (phase == 9) {
                    requestorApp.submitQuality3();
                } else if (phase == 10) {
                    requestorApp.submitQuality4();
                } else {
                    Thread.sleep(5000);
                    System.out.println("moving to payments...");
                    requestorApp.parallelPAY();
                }
                */
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
