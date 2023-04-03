
package com.Pythia.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import java.math.BigInteger;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import java.math.BigDecimal;
import java.util.function.*;
import org.web3j.abi.datatypes.Type;
import org.web3j.utils.Numeric;
// imports for gui application
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
// import for randomness
// imports for cryptos
import javax.crypto.Cipher;
import java.io.InputStream;
import java.security.*;
// arrray list
import java.util.List;
import org.web3j.abi.datatypes.generated.Bytes32;


public class App {
	private final Logger log = LoggerFactory.getLogger(App.class);
	// global datatypes
	CSTask_sol_crowdsourcingTask CTcontract;
  	Credentials credentials_requestor;
	Web3j web3j;
	Web3ClientVersion web3ClientVersion;
	String contractAddress;
	String[][] workers_data;
	int worker_count = 0;
	static int workers_required;
	static BigInteger taskBudget;
	static int choices_allowed;
	static int amountToBePaid;
	static int epochLength;
	int currWorker = 0;
	static requestorApp requestor_created;
	//static RSACertification rsa_certification_created;
	static KeyPair master_pair;
	static int cert_count = 0;
	static PrivateKey master_private_key;
	static PublicKey master_public_key;
	static KeyPair requestor_pair;
	static PrivateKey requestor_private_key;
	static PublicKey requestor_public_key;
	static int total_workers;
	public static BigInteger blocks = BigInteger.ZERO;

	// data structure to store all the certificates
	static List<String> certificate_list = new ArrayList<String>();
	static List<String> workerInput_list = new ArrayList<String>();
	static List<String> response_cipher_list = new ArrayList<String>();
	static List<BigInteger> diff = new ArrayList<>();


	// requester key for RINKEBY: e055286653578d77b36ba59a47ef57fadf9cd5b99a8a3a6c9e6a1a3b60809b20
	// requester key for RINKEBY: 75fc3c80d1e75b98ed9272d70c49bc48375d6afc74cf5a52eba8ed9ecc26e71e
	// requester key for GOERLI: 0df4a5343622e882842e6495c599929d4bfd3ee91c07b220b9ff24f9f455d303

	public static void main(String[] args) throws Exception {
		// set the required number of workers
		//total_workers = Integer.parseInt(args[0]);
		new App().initliazeData(); // invoke the class to log-in the Requester
	}

	//opening web3j
	public void initliazeData() throws Exception {
		log.info("Let's start crowdsourcing!");
//		BigInteger xxx = new BigInteger("59797354051500504835387225643501628981819200865021881747334854305352395504011");
//		System.out.println(new Bytes32(xxx.toByteArray()));
////		// Start Web3 connection
		//web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/7c5f848f90bd4278a438e592abe7b01c"));
		web3j = Web3j.build(new HttpService("https://goerli.infura.io/v3/9aa3d95b3bc440fa88ea12eaa4456161"));
		web3ClientVersion = web3j.web3ClientVersion().send();
		log.info("Web3j version used: " + web3ClientVersion.getWeb3ClientVersion());




		long startTime = System.nanoTime();
		// deploy the contract and register the requestor
		requestor_created = new requestorApp();
//		EthBlockNumber result = web3j.ethBlockNumber().send();
//		System.out.println(" Latest Block Number: " + result.getBlockNumber());
		//CTcontract = requestor_created.deployContract("e055286653578d77b36ba59a47ef57fadf9cd5b99a8a3a6c9e6a1a3b60809b20", web3j); // invoke the function to deploy the contract
		CTcontract = requestor_created.deployContract("0df4a5343622e882842e6495c599929d4bfd3ee91c07b220b9ff24f9f455d303", web3j); // invoke the function to deploy the contract
//		result = web3j.ethBlockNumber().send();
//		System.out.println(" Latest Block Number: " + result.getBlockNumber());
		contractAddress = CTcontract.getContractAddress();		// store the address of the contract

		Optional<TransactionReceipt> contract_receipt = CTcontract.getTransactionReceipt();
		log.info("Deployment Gas Used: " + contract_receipt.get().getGasUsed());
		log.info("Task is being deployed.....");

		/* sample the input for the task */
		Random rn = new Random();
		int range = 10;				// generate numbers between 1 to 10
		choices_allowed = rn.nextInt(range)+1;
		workers_required = 128;
		amountToBePaid = rn.nextInt(range)+1;
		epochLength = 500*(rn.nextInt(range)+1);	// generate numbers between
		taskBudget = BigInteger.valueOf(amountToBePaid * workers_required);
		String task = "Who do you want to be the next President of the USA?";
		int alpha = rn.nextInt(range)+1;	// threshold
		int beta = rn.nextInt(range)+1;		// threshold

		log.info("No Of Workers...." + workers_required);

		// sample the public key
		Process proc = Runtime.getRuntime().exec("python getPubKey.py");
		Scanner scanner = new Scanner(proc.getInputStream());
	    scanner.useDelimiter("\r\n");
    	String key = scanner.next().split("\n")[0];
		scanner.close();
		proc.waitFor();

		/* submit to the smart contract */
		requestor_created.taskBroadcast(task, BigInteger.valueOf(workers_required), BigInteger.valueOf(choices_allowed), BigInteger.valueOf(amountToBePaid), BigInteger.valueOf(epochLength),
				BigInteger.valueOf(epochLength), key, BigInteger.valueOf(alpha), BigInteger.valueOf(beta), taskBudget);

		/* output the time taken */
		long endTime = System.nanoTime();
		System.out.println(endTime - startTime);
		parallelGeneration();
	}



	/*
	public void taskInput() {
		JFrame frame = new JFrame("Task Input");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel label_0 = new JLabel("Enter Your Task");
		JLabel label_1 = new JLabel("Enter Required Workers");
		JLabel label_2 = new JLabel("Enter Number of Choices");
		JLabel label_3 = new JLabel("Enter Amount To be Paid");
		JLabel label_4 = new JLabel("Enter Epoch Length");
		JLabel label_5 = new JLabel("Enter Public Key");
		JLabel label_6 = new JLabel("Enter Threshold for Alpha");
		JLabel label_7 = new JLabel("Enter Threhold for Beta");
		JLabel label_8 = new JLabel("Enter Budget");
		JButton button = new JButton();
		button.setText("OK");
		JTextField textField_0 = new JTextField();
		textField_0.setPreferredSize(new Dimension(150, 25));
		JTextField textField_1 = new JTextField();
		textField_1.setPreferredSize(new Dimension(150, 25));
		JTextField textField_2 = new JTextField();
		textField_2.setPreferredSize(new Dimension(150, 25));
		JTextField textField_3 = new JTextField();
		textField_3.setPreferredSize(new Dimension(150, 25));
		JTextField textField_4 = new JTextField();
		textField_4.setPreferredSize(new Dimension(150, 25));
		JTextField textField_5 = new JTextField();
		textField_5.setPreferredSize(new Dimension(150, 25));
		JTextField textField_6 = new JTextField();
		textField_6.setPreferredSize(new Dimension(150, 25));
		JTextField textField_7 = new JTextField();
		textField_7.setPreferredSize(new Dimension(150, 25));
		JTextField textField_8 = new JTextField();
		textField_8.setPreferredSize(new Dimension(150, 25));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input_0 = textField_0.getText();	// task
				int input_1 =  Integer.parseInt(textField_1.getText());	// workers
				int input_2 =  Integer.parseInt(textField_2.getText()); // choices
				int input_3 =  Integer.parseInt(textField_3.getText()); // amount to be paid
				int input_4 =  Integer.parseInt(textField_4.getText());	// epoch length
				String key =  textField_5.getText();	// public key
				int input_6 =  Integer.parseInt(textField_6.getText());	// alpha
				int input_7 =  Integer.parseInt(textField_7.getText());	// beta
				int input_8 =  Integer.parseInt(textField_8.getText());	// budget
				taskBudget = BigInteger.valueOf(input_8);
				choices_allowed = input_2;
workers_required = input_1;
				amountToBePaid = input_3;
				epochLength = input_4;
				try {
					requestor_created.taskBroadcast(input_0, BigInteger.valueOf(workers_required), BigInteger.valueOf(choices_allowed), BigInteger.valueOf(amountToBePaid), BigInteger.valueOf(epochLength),
						     key, BigInteger.valueOf(input_6), BigInteger.valueOf(input_7), taskBudget);	// invoke the function to broadcast the task
					parallelGeneration(); // call the form for the workers to submit their request
				}
				catch(Exception err) {
				}
			}
		});

		panel.add(label_0);
		panel.add(textField_0);
		panel.add(label_1);
		panel.add(textField_1);
		panel.add(label_2);
		panel.add(textField_2);
		panel.add(label_3);
		panel.add(textField_3);
		panel.add(label_4);
		panel.add(textField_4);
		panel.add(label_5);
		panel.add(textField_5);
		panel.add(label_6);
		panel.add(textField_6);
		panel.add(label_7);
		panel.add(textField_7);
		panel.add(label_8);
		panel.add(textField_8);
		panel.add(button);
		frame.add(panel);
		frame.setSize(300, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	} // function block

	 */

	// function to generate parallel thread
	public void parallelGeneration() throws Exception {
		// open the threads
		for (int i=0; i<workers_required; i++) {
			workerResponse curr = new workerResponse(web3j, i, contractAddress);
			curr.start();
		}
	} // function block


	/* ---------- helper functions ------------ */
	// function to generate random number between a given range
	public BigInteger getRandomBigInteger(int min, int max) {
		Random rand = new Random();
    int x = rand.nextInt(max);
    return BigInteger.valueOf(x);
	}

} // class block
