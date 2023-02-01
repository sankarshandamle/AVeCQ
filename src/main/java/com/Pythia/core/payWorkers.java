package com.Pythia.core;

//import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticEIP1559GasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;

import java.util.*;
import java.math.BigInteger;

public class payWorkers extends Thread {

    Web3j web3j;
    private final Logger log = LoggerFactory.getLogger(submitQuality.class);
    int currWorker;
    String contractAddress;
    private CSTask_sol_crowdsourcingTask CTcontract_requestor;
    static Credentials credentials_requestor;
    String Address;
    int phase;

    public payWorkers(Web3j x, int y, String z, String _add, int _p) throws Exception {
        web3j = x;
        currWorker = y;
        contractAddress = z;
        Address = _add;
        phase = _p;
    }

    public void run() {



        Long t0 = System.nanoTime();
        BigInteger gasPrice = BigInteger.valueOf(1_100_000_000L);

        //credentials_requestor = Credentials.create(workerResponse.workersData[currWorker%32]);
        credentials_requestor = Credentials.create("0df4a5343622e882842e6495c599929d4bfd3ee91c07b220b9ff24f9f455d303");

        StaticEIP1559GasProvider staticEIP1559GasProvider = new StaticEIP1559GasProvider(5, gasPrice ,  BigInteger.valueOf(50), DefaultGasProvider.GAS_LIMIT);
        //CTcontract_requestor = CSTask_sol_crowdsourcingTask.load(contractAddress, web3j, credentials_requestor, staticEIP1559GasProvider);

        /* fast raw transaction */
        FastRawTransactionManager fastRawTxMgr =new FastRawTransactionManager(web3j, credentials_requestor, new PollingTransactionReceiptProcessor(web3j, 3000, 40));
        CTcontract_requestor = CSTask_sol_crowdsourcingTask.load(contractAddress, web3j, fastRawTxMgr, staticEIP1559GasProvider);
        if (requestorApp.correctWorker.get(currWorker) == 1) {

        try {
		/* Process proc = Runtime.getRuntime().exec("python keccak.py");
    Scanner scanner = new Scanner(proc.getInputStream());
    scanner.useDelimiter("\r\n");
    String myBytes = scanner.next().split(" ")[0];
    scanner.close();
    proc.waitFor(); */

            //TransactionReceipt receipt = CTcontract_requestor.payWorkers(Address).send();

	    //System.out.println("Total Gas used to pay... " + receipt.getGasUsed());
            //requestorApp.paymentTimes.add(receipt.getGasUsed());
        } catch (Exception e) {
            e.printStackTrace();
        }
}

        /*
        Long t1 = System.nanoTime();
        //requestorApp.paymentTimes.add(t1-t0);
        if (currWorker == App.workers_required -1) {
           try {
                Thread.sleep(55000);
                if (phase == 11) {
			requestorApp.payWorkers2();
		}
                else if (phase == 9) {
			requestorApp.payWorkers3();
		}
                else if (phase == 10) {
			requestorApp.payWorkers4();
		}
		else {
                Thread.sleep(10000);
			System.out.println("Payment Times: " + requestorApp.paymentTimes);
                	System.out.println("Total Time for last phase: " + (System.nanoTime() - requestorApp.LPS0));
                        System.exit(0);
                }
               }

        catch (Exception e) {
            e.printStackTrace();
        }}
    */
        
    }
}
