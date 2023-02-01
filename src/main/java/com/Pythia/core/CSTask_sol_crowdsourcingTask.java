package com.Pythia.core;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("rawtypes")
public class CSTask_sol_crowdsourcingTask extends Contract {
    public static final String BINARY = "60806040526000600a556000601655600060185560006019556000601a556000601b556000601c556000601d556000601e5534801561003d57600080fd5b50600980546001600160a01b03191633179055601f805460ff19169055611672806100696000396000f3fe60806040526004361061007b5760003560e01c80635ca656b91161004e5780635ca656b91461010b57806385acb24a1461012b578063dfe0728e1461014b578063ed53f17d1461016b57600080fd5b80630df5794614610080578063459551b2146100a2578063586db2b3146100b757806358a6d8f1146100d7575b600080fd5b34801561008c57600080fd5b506100a061009b366004611058565b61017e565b005b3480156100ae57600080fd5b506100a0610379565b3480156100c357600080fd5b506100a06100d2366004611327565b6104ad565b3480156100e357600080fd5b506100f76100f2366004610f83565b6105e4565b604051901515815260200160405180910390f35b34801561011757600080fd5b506100a06101263660046112b2565b61070b565b34801561013757600080fd5b506100a06101463660046111bd565b6107f4565b34801561015757600080fd5b506100a06101663660046112e4565b610a25565b6100a0610179366004611373565b610b18565b600480601f5460ff166006811115610198576101986115fa565b146101be5760405162461bcd60e51b81526004016101b5906114a7565b60405180910390fd5b6009546001600160a01b031633146101e85760405162461bcd60e51b81526004016101b590611470565b60005b6018548110156102f9576102e78b828151811061020a5761020a611610565b60200260200101518b838151811061022457610224611610565b60200260200101518b848151811061023e5761023e611610565b60200260200101518b858151811061025857610258611610565b60200260200101518b868151811061027257610272611610565b60200260200101518b878151811061028c5761028c611610565b60200260200101518b88815181106102a6576102a6611610565b60200260200101518b89815181106102c0576102c0611610565b60200260200101518b8a815181106102da576102da611610565b6020026020010151610d1e565b806102f1816115c9565b9150506101eb565b50601f805460ff191660051790556000600a55604080516020808252601a908201527f506c6561736520636865636b20796f75207175616c69746965730000000000008183015290517f180c12c0533824f45ae561a034344b1f469134fb27bd8f9205a080de160f08ed916060908290030190a150505050505050505050565b600680601f5460ff166006811115610393576103936115fa565b146103b05760405162461bcd60e51b81526004016101b5906114a7565b43601e54601d546103c19190611540565b1061040e5760405162461bcd60e51b815260206004820152601860248201527f50726f74657374206973206e6f74206f7665722079657421000000000000000060448201526064016101b5565b6009546001600160a01b031633146104385760405162461bcd60e51b81526004016101b590611470565b6000601a5460155461044a9190611577565b6009546040519192506001600160a01b03169082156108fc029083906000818181858888f19350505050158015610485573d6000803e3d6000fd5b5050600060158190556017819055601a8190556016819055600a5550601f805460ff19169055565b600180601f5460ff1660068111156104c7576104c76115fa565b146104e45760405162461bcd60e51b81526004016101b5906114a7565b6040805160a08101825260608082018b815260808084018c905290835260208084018b905284519182018552898252818101899052818501889052918101869052828401523360009081526002918290529290922081519192909161054b91839190610dae565b5060208201516002820155604082015161056b9060038301906004610dec565b5050600a80549150600061057e836115c9565b9190505550601854600a54106105a157601f805460ff191660031790556000600a555b601854600a541080156105c25750601c54601d546105bf9190611540565b43115b156105da57601f805460ff191660061790556000600a555b5050505050505050565b6000600580601f5460ff166006811115610600576106006115fa565b1461061d5760405162461bcd60e51b81526004016101b5906114a7565b6009546001600160a01b031633146106475760405162461bcd60e51b81526004016101b590611470565b60005b6018548110156106f05783818151811061066657610666611610565b60200260200101516000146106de5784818151811061068757610687611610565b60200260200101516001600160a01b03166108fc601b549081150290604051600060405180830381858888f193505050501580156106c9573d6000803e3d6000fd5b50601b54601a546106da9190611540565b601a555b806106e8816115c9565b91505061064a565b5050601f805460ff19166006179055505043601d5550600190565b600380601f5460ff166006811115610725576107256115fa565b146107425760405162461bcd60e51b81526004016101b5906114a7565b6009546001600160a01b0316331461076c5760405162461bcd60e51b81526004016101b590611470565b6003859055600484905560058390556006829055604080516020808252601b908201527f506c65617365207665726966792066696e616c20616e737765722100000000008183015290517f180c12c0533824f45ae561a034344b1f469134fb27bd8f9205a080de160f08ed9181900360600190a15050601f805460ff19166004179055505050565b600480601f5460ff16600681111561080e5761080e6115fa565b1461082b5760405162461bcd60e51b81526004016101b5906114a7565b6009546001600160a01b031633146108555760405162461bcd60e51b81526004016101b590611470565b60005b6018548110156109b4576040518060a0016040528089838151811061087f5761087f611610565b6020026020010151815260200188838151811061089e5761089e611610565b602002602001015181526020018783815181106108bd576108bd611610565b6020026020010151815260200160405180604001604052808885815181106108e7576108e7611610565b6020026020010151815260200187858151811061090657610906611610565b6020026020010151815250815260200184838151811061092857610928611610565b6020026020010151815250600760008a848151811061094957610949611610565b60200260200101518152602001908152602001600020600082015181600001556020820151816001015560408201518160020155606082015181600301906002610994929190610dae565b5060809190910151600590910155806109ac816115c9565b915050610858565b507f180c12c0533824f45ae561a034344b1f469134fb27bd8f9205a080de160f08ed604051610a14906020808252601c908201527f506c6561736520636865636b20796f75207175616c6974696573212100000000604082015260600190565b60405180910390a150505050505050565b600180601f5460ff166006811115610a3f57610a3f6115fa565b14610a5c5760405162461bcd60e51b81526004016101b5906114a7565b6040805160c0810182526080810189815260a0820189905281528151808301835287815260208181018890528083019190915282518084018452868152808201869052828401526001606083018190523360009081529152919091208151610ac79082906002610dae565b506020820151610add9060028084019190610dae565b506040820151610af39060048301906002610dae565b50606091909101516006909101805460ff191691151591909117905550505050505050565b8760968110610b735760405162461bcd60e51b815260206004820152602160248201527f6d6f7265207468616e2031353020776f726b657273206e6f7420616c6c6f77656044820152601960fa1b60648201526084016101b5565b87601e8110610bc45760405162461bcd60e51b815260206004820181905260248201527f6d6f7265207468616e2033302063686f69636573206e6f7420616c6c6f77656460448201526064016101b5565b600080601f5460ff166006811115610bde57610bde6115fa565b14610bfb5760405162461bcd60e51b81526004016101b5906114a7565b6009546001600160a01b03163314610c255760405162461bcd60e51b81526004016101b590611470565b8a89610c318183611558565b341015610c805760405162461bcd60e51b815260206004820152601c60248201527f696e73756666696369656e74206d6f6e6579206465706f73697465640000000060448201526064016101b5565b3460155560188d905560198c9055601b8b9055601c8a90558751610caa90602090818b0190610e19565b50866021600001558560216001018190555088601e8190555043601d819055507f9f7af36d30e1e1f9f121bc61cdbbcdbdd097496bf883e137dc36a16a7a36fb6b8e604051610cf9919061141b565b60405180910390a15050601f805460ff19166001179055505050505050505050505050565b6040805160c0810182528082018a815260608083018b905260808084018b905260a084018a90529183528351918201845287825260208281018890528285018790529082018590528083019190915260008c81526008909152919091208151610d8a9082906004610dec565b506020820151610da09060048084019190610dec565b505050505050505050505050565b8260028101928215610ddc579160200282015b82811115610ddc578251825591602001919060010190610dc1565b50610de8929150610e8c565b5090565b8260048101928215610ddc5791602002820182811115610ddc578251825591602001919060010190610dc1565b828054610e259061158e565b90600052602060002090601f016020900481019282610e475760008555610ddc565b82601f10610e6057805160ff1916838001178555610ddc565b82800160010185558215610ddc5791820182811115610ddc578251825591602001919060010190610dc1565b5b80821115610de85760008155600101610e8d565b600082601f830112610eb257600080fd5b81356020610ec7610ec28361151c565b6114eb565b80838252828201915082860187848660051b8901011115610ee757600080fd5b60005b85811015610f0657813584529284019290840190600101610eea565b5090979650505050505050565b600082601f830112610f2457600080fd5b813567ffffffffffffffff811115610f3e57610f3e611626565b610f51601f8201601f19166020016114eb565b818152846020838601011115610f6657600080fd5b816020850160208301376000918101602001919091529392505050565b60008060408385031215610f9657600080fd5b823567ffffffffffffffff80821115610fae57600080fd5b818501915085601f830112610fc257600080fd5b81356020610fd2610ec28361151c565b8083825282820191508286018a848660051b8901011115610ff257600080fd5b600096505b8487101561102a5780356001600160a01b038116811461101657600080fd5b835260019690960195918301918301610ff7565b509650508601359250508082111561104157600080fd5b5061104e85828601610ea1565b9150509250929050565b60008060008060008060008060006101208a8c03121561107757600080fd5b893567ffffffffffffffff8082111561108f57600080fd5b61109b8d838e01610ea1565b9a5060208c01359150808211156110b157600080fd5b6110bd8d838e01610ea1565b995060408c01359150808211156110d357600080fd5b6110df8d838e01610ea1565b985060608c01359150808211156110f557600080fd5b6111018d838e01610ea1565b975060808c013591508082111561111757600080fd5b6111238d838e01610ea1565b965060a08c013591508082111561113957600080fd5b6111458d838e01610ea1565b955060c08c013591508082111561115b57600080fd5b6111678d838e01610ea1565b945060e08c013591508082111561117d57600080fd5b6111898d838e01610ea1565b93506101008c01359150808211156111a057600080fd5b506111ad8c828d01610ea1565b9150509295985092959850929598565b60008060008060008060c087890312156111d657600080fd5b863567ffffffffffffffff808211156111ee57600080fd5b6111fa8a838b01610ea1565b9750602089013591508082111561121057600080fd5b61121c8a838b01610ea1565b9650604089013591508082111561123257600080fd5b61123e8a838b01610ea1565b9550606089013591508082111561125457600080fd5b6112608a838b01610ea1565b9450608089013591508082111561127657600080fd5b6112828a838b01610ea1565b935060a089013591508082111561129857600080fd5b506112a589828a01610ea1565b9150509295509295509295565b600080600080608085870312156112c857600080fd5b5050823594602084013594506040840135936060013592509050565b60008060008060008060c087890312156112fd57600080fd5b505084359660208601359650604086013595606081013595506080810135945060a0013592509050565b600080600080600080600060e0888a03121561134257600080fd5b505085359760208701359750604087013596606081013596506080810135955060a0810135945060c0013592509050565b60008060008060008060008060006101208a8c03121561139257600080fd5b893567ffffffffffffffff808211156113aa57600080fd5b6113b68d838e01610f13565b9a5060208c0135995060408c0135985060608c0135975060808c0135965060a08c0135955060c08c01359150808211156113ef57600080fd5b506113fc8c828d01610f13565b93505060e08a013591506101008a013590509295985092959850929598565b600060208083528351808285015260005b818110156114485785810183015185820160400152820161142c565b8181111561145a576000604083870101525b50601f01601f1916929092016040019392505050565b6020808252601c908201527f616363657373206f6e6c7920746f2074686520726571756573746f7200000000604082015260600190565b60208082526024908201527f66756e6374696f6e206e6f7420617661696c61626c65206174207468697320736040820152637461746560e01b606082015260800190565b604051601f8201601f1916810167ffffffffffffffff8111828210171561151457611514611626565b604052919050565b600067ffffffffffffffff82111561153657611536611626565b5060051b60200190565b60008219821115611553576115536115e4565b500190565b6000816000190483118215151615611572576115726115e4565b500290565b600082821015611589576115896115e4565b500390565b600181811c908216806115a257607f821691505b602082108114156115c357634e487b7160e01b600052602260045260246000fd5b50919050565b60006000198214156115dd576115dd6115e4565b5060010190565b634e487b7160e01b600052601160045260246000fd5b634e487b7160e01b600052602160045260246000fd5b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052604160045260246000fdfea2646970667358221220bdbae8b9bae36811daf0abef17b89d1091cc9c7f7b4f34284ccd94fb2942a82064736f6c63430008060033";

    public static final String FUNC_BROADCASTTASK = "broadcastTask";

    public static final String FUNC_PAYWORKERS = "payWorkers";

    public static final String FUNC_REINITVALUES = "reinitValues";

    public static final String FUNC_SUBMITAUTHCALC = "submitAuthCalc";

    public static final String FUNC_SUBMITQUALITY_COM = "submitQuality_Com";

    public static final String FUNC_SUBMITQUALITY_PROOFS = "submitQuality_Proofs";

    public static final String FUNC_SUBMITRESPONSE_METHOD_COMMITMENT = "submitresponse_method_commitment";

    public static final String FUNC_SUBMITRESPONSE_METHOD_ENCRYPTIONS = "submitresponse_method_encryptions";

    public static final Event QUALITYSUBMITTED_EVENT = new Event("qualitySubmitted",
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event TASKEMIT_EVENT = new Event("taskEmit",
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected CSTask_sol_crowdsourcingTask(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CSTask_sol_crowdsourcingTask(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CSTask_sol_crowdsourcingTask(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CSTask_sol_crowdsourcingTask(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<QualitySubmittedEventResponse> getQualitySubmittedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(QUALITYSUBMITTED_EVENT, transactionReceipt);
        ArrayList<QualitySubmittedEventResponse> responses = new ArrayList<QualitySubmittedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            QualitySubmittedEventResponse typedResponse = new QualitySubmittedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.announce = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<QualitySubmittedEventResponse> qualitySubmittedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, QualitySubmittedEventResponse>() {
            @Override
            public QualitySubmittedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(QUALITYSUBMITTED_EVENT, log);
                QualitySubmittedEventResponse typedResponse = new QualitySubmittedEventResponse();
                typedResponse.log = log;
                typedResponse.announce = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<QualitySubmittedEventResponse> qualitySubmittedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(QUALITYSUBMITTED_EVENT));
        return qualitySubmittedEventFlowable(filter);
    }

    public static List<TaskEmitEventResponse> getTaskEmitEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TASKEMIT_EVENT, transactionReceipt);
        ArrayList<TaskEmitEventResponse> responses = new ArrayList<TaskEmitEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TaskEmitEventResponse typedResponse = new TaskEmitEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.task = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TaskEmitEventResponse> taskEmitEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TaskEmitEventResponse>() {
            @Override
            public TaskEmitEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TASKEMIT_EVENT, log);
                TaskEmitEventResponse typedResponse = new TaskEmitEventResponse();
                typedResponse.log = log;
                typedResponse.task = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TaskEmitEventResponse> taskEmitEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TASKEMIT_EVENT));
        return taskEmitEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> broadcastTask(String task, BigInteger number_1, BigInteger number_2, BigInteger _amount, BigInteger _epoch, BigInteger _protest, String _pubkey, BigInteger _alpha, BigInteger _beta, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BROADCASTTASK,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(task),
                        new org.web3j.abi.datatypes.generated.Uint256(number_1),
                        new org.web3j.abi.datatypes.generated.Uint256(number_2),
                        new org.web3j.abi.datatypes.generated.Uint256(_amount),
                        new org.web3j.abi.datatypes.generated.Uint256(_epoch),
                        new org.web3j.abi.datatypes.generated.Uint256(_protest),
                        new org.web3j.abi.datatypes.Utf8String(_pubkey),
                        new org.web3j.abi.datatypes.generated.Uint256(_alpha),
                        new org.web3j.abi.datatypes.generated.Uint256(_beta)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> payWorkers(List<String> _a, List<BigInteger> _amt) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAYWORKERS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                                org.web3j.abi.datatypes.Address.class,
                                org.web3j.abi.Utils.typeMap(_a, org.web3j.abi.datatypes.Address.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                                org.web3j.abi.datatypes.generated.Uint256.class,
                                org.web3j.abi.Utils.typeMap(_amt, org.web3j.abi.datatypes.generated.Uint256.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> reinitValues() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REINITVALUES,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> submitAuthCalc(byte[] _p1, byte[] _p2, byte[] _p3, byte[] _p4) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SUBMITAUTHCALC,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_p1),
                        new org.web3j.abi.datatypes.generated.Bytes32(_p2),
                        new org.web3j.abi.datatypes.generated.Bytes32(_p3),
                        new org.web3j.abi.datatypes.generated.Bytes32(_p4)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> submitQuality_Com(List<byte[]> _hash, List<byte[]> _randomness, List<byte[]> attack_rand, List<byte[]> _alpha, List<byte[]> _beta, List<BigInteger> _pos) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SUBMITQUALITY_COM,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                                org.web3j.abi.datatypes.generated.Bytes32.class,
                                org.web3j.abi.Utils.typeMap(_hash, org.web3j.abi.datatypes.generated.Bytes32.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                                org.web3j.abi.datatypes.generated.Bytes32.class,
                                org.web3j.abi.Utils.typeMap(_randomness, org.web3j.abi.datatypes.generated.Bytes32.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                                org.web3j.abi.datatypes.generated.Bytes32.class,
                                org.web3j.abi.Utils.typeMap(attack_rand, org.web3j.abi.datatypes.generated.Bytes32.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                                org.web3j.abi.datatypes.generated.Bytes32.class,
                                org.web3j.abi.Utils.typeMap(_alpha, org.web3j.abi.datatypes.generated.Bytes32.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                                org.web3j.abi.datatypes.generated.Bytes32.class,
                                org.web3j.abi.Utils.typeMap(_beta, org.web3j.abi.datatypes.generated.Bytes32.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                                org.web3j.abi.datatypes.generated.Uint256.class,
                                org.web3j.abi.Utils.typeMap(_pos, org.web3j.abi.datatypes.generated.Uint256.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> submitQuality_Proofs(List<byte[]> _hash, List<byte[]> _p1, List<byte[]> _p2, List<byte[]> _p3, List<byte[]> _p4, List<byte[]> _p5, List<byte[]> _p6, List<byte[]> _p7, List<byte[]> _p8) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SUBMITQUALITY_PROOFS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                                org.web3j.abi.datatypes.generated.Bytes32.class,
                                org.web3j.abi.Utils.typeMap(_hash, org.web3j.abi.datatypes.generated.Bytes32.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                                org.web3j.abi.datatypes.generated.Bytes32.class,
                                org.web3j.abi.Utils.typeMap(_p1, org.web3j.abi.datatypes.generated.Bytes32.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                                org.web3j.abi.datatypes.generated.Bytes32.class,
                                org.web3j.abi.Utils.typeMap(_p2, org.web3j.abi.datatypes.generated.Bytes32.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                                org.web3j.abi.datatypes.generated.Bytes32.class,
                                org.web3j.abi.Utils.typeMap(_p3, org.web3j.abi.datatypes.generated.Bytes32.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                                org.web3j.abi.datatypes.generated.Bytes32.class,
                                org.web3j.abi.Utils.typeMap(_p4, org.web3j.abi.datatypes.generated.Bytes32.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                                org.web3j.abi.datatypes.generated.Bytes32.class,
                                org.web3j.abi.Utils.typeMap(_p5, org.web3j.abi.datatypes.generated.Bytes32.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                                org.web3j.abi.datatypes.generated.Bytes32.class,
                                org.web3j.abi.Utils.typeMap(_p6, org.web3j.abi.datatypes.generated.Bytes32.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                                org.web3j.abi.datatypes.generated.Bytes32.class,
                                org.web3j.abi.Utils.typeMap(_p7, org.web3j.abi.datatypes.generated.Bytes32.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                                org.web3j.abi.datatypes.generated.Bytes32.class,
                                org.web3j.abi.Utils.typeMap(_p8, org.web3j.abi.datatypes.generated.Bytes32.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> submitresponse_method_commitment(byte[] _alpha, byte[] _beta, byte[] _h1, byte[] _proof1, byte[] _proof2, byte[] _proof3, byte[] _proof4) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SUBMITRESPONSE_METHOD_COMMITMENT,
                Arrays.<Type>asList(stringToBytes32(_alpha),
                        stringToBytes32(_beta),
                        stringToBytes32(_h1),
                        stringToBytes32(_proof1),
                        stringToBytes32(_proof2),
                        stringToBytes32(_proof3),
                        stringToBytes32(_proof4)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> submitresponse_method_encryptions(byte[] _e1, byte[] _e2, byte[] _e3, byte[] _e4, byte[] _r1, byte[] _r2) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SUBMITRESPONSE_METHOD_ENCRYPTIONS,
                Arrays.<Type>asList(stringToBytes32(_e1),
                        stringToBytes32(_e2),
                        stringToBytes32(_e3),
                        stringToBytes32(_e4),
                        stringToBytes32(_r1),
                        stringToBytes32(_r2)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static CSTask_sol_crowdsourcingTask load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CSTask_sol_crowdsourcingTask(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CSTask_sol_crowdsourcingTask load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CSTask_sol_crowdsourcingTask(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CSTask_sol_crowdsourcingTask load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CSTask_sol_crowdsourcingTask(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CSTask_sol_crowdsourcingTask load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CSTask_sol_crowdsourcingTask(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CSTask_sol_crowdsourcingTask> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CSTask_sol_crowdsourcingTask.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<CSTask_sol_crowdsourcingTask> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CSTask_sol_crowdsourcingTask.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CSTask_sol_crowdsourcingTask> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CSTask_sol_crowdsourcingTask.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CSTask_sol_crowdsourcingTask> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CSTask_sol_crowdsourcingTask.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class QualitySubmittedEventResponse extends BaseEventResponse {
        public String announce;
    }

    public static class TaskEmitEventResponse extends BaseEventResponse {
        public String task;
    }

    // from https://ethereum.stackexchange.com/questions/23549/convert-string-to-bytes32-in-web3j/
    public static Bytes32 stringToBytes32(byte[] byteValue) {
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length-1);
        return new Bytes32(byteValueLen32);
    }
}