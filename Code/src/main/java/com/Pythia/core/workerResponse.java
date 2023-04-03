package com.Pythia.core;

//import org.graalvm.compiler.hotspot.meta.DefaultHotSpotLoweringProvider;
import com.kenai.jnr.x86asm.SerializerIntrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.StaticEIP1559GasProvider;
import org.web3j.utils.Convert;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigInteger ;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.*;
// import for randomness
// import for slicing arrays


public class workerResponse extends Thread {

//c0944e38ef119cc068c9b7a8ce96bba95fe6655efb2bb01320ed4e96dec7a9fa
//2dff08ca97d82044f597c30d79336659b06510ac38dceb9628afe966db660532
//


// new rinkeby accounts.... 4 oct 2022
//

public static String[] newWorkers = {

"b888ebf0befa7eedc505bc3bf49759a8cb6afa04cdf0f54158cc7f3554fbd4ce",
"1ac28f717d185c15544956f3f45c57872e360069c97849361130491920fd8937",
"2dff08ca97d82044f597c30d79336659b06510ac38dceb9628afe966db660532",
"c6a29fd5ab1e76a6270f0f68cb98f89dafc422cf03e2ce3ceb0901dac3bda632",
"91cb129be5506459ac29b2900e45c3e914174c8a651369279fecbd0ea232f663",
"e067d994b5457a2afa563d3488d270e8696d9b1ab9bef57acc9469e23819198f",
"4924b5eca03b5ee44ddedf4c4629c8dddc764e10aba64667ea2fc7e9fe8f53d5",
"d7a9d7517ec2ac3c7b18d3f561c9ab84fc9e857d19543b606bcec600b4574d0f",
"88a3fe6bb8c0ed69e3505da2955c13840cda96c0b3aefac0944ba3c7f6b3c36b",
"6dbfa8f95da27a74ec21eeb3266c64c83440d2f058012c2b2b6c5e45a77b9392"

};



/* ~~~~~~~~~~~~~~~~~~~~~~~~ RINKEBY ACCOUNTS 
*/


/*
public static String[] workersData = { 

"901003b028d94902b977c4963cf489a90f2eb1becedbcce28a425a572f108569",
            "ab0f755ba0ab6e38b89928b196c6a847f0de00c141d210943d107e1842a74591",
            "98fe8d3db0b4923dfbc7e9988fab5a92180b49ef3740b94127c77b85e3c4dfae",
            "92a23b683ded0bb366c1d724d220da057c16a08d830b19f8934ff0ceabb62f30",
            "d771a1c8d2423bf3160eac4c977ea9ce07b8b6f8d766a97ca8738ae80d30ba9f",
            "67472edc3717f060f9580969eceb72b2ceae518a33bdac0aca4df661f9e132a1",
            "9d6d7c4a06d46375e37032e459f6f93232a7a38de547a338eacd37f1644f5e98",
            "0aa265a1f18ac9072a16ec792717816ecfb2c7284ba4ed57d42a9e2877ed4dc1",
            "17d797c49cf97361dab221faabd9d76b061982d7159287bde8dcc5017880ad96",
            "cb754485b6599d33ad5185413fcb7a8f408449158cee7c1270116c8fcfb8b35d",
            "47c405d0e85ad6e100d0d33a8ca7e291bd4838cf4c601b2ebc65ed12c08e29bf",
            "0ed6eb84147b143280d57509d5814e4e62ed9bdf98312e96efd91825164c05d3",
            "f16335b2f2a018af02bf25ba2b9311cf819d371ae76c46ae514e37616a390414",
            "0b0a6c6fd8dce1099988470690e97ce99b13b7b527ab7525947869f67be52ce5",
            "7d186c56cdf8fe5b0d5375bfe6def94f8678799d1e0ae8b13c38daa2994e7464",
            "bc5f762e4f833893a2c2dae88cd0ec16a1368d730f5c040cca8a71c1a0176591",
            "bb58cc8552caf6f733c63707efd2e1b2976fb5e1b171ea762ba9cb4fb1099011",
            "659eb886781077c74355f3bf3251bd5c9efe7ec5b446433b04c7db05a4d001bb",
            "f7943f54a4958a53701be95ca5079df2a8f0c7a3ddb4f4dcd8c5b8719af58181",
            "cff613ab4033a22df90ba370529bd5e48e873061470bdc074f508ab24087a0eb",
            "a4f47dfb20eddf0fa8cbdad31a104a74c225fff330e2705ae048828d4785826d",
            "2ccc61084d8445e90318d39116d6c9997c056243d8f06429aaa393ed697c28b9",
            "2e927526732492217e6fd3d5a93e2b9c78f00f6d32e65415c50d5a633c45a11a",
            "880997d9df1c0fbab427a81d68e48c42f2616eb3e20d0138b967529ec4ad03b9",
            "b5d2fe3ea79c98023ebb10dc586de7b21f04986e08ffccc9769feaa1544d578e",
            "40a7bc66f5737a2431f22cf03c03212e11b1c54be4fe310fdb56d7d0e7538ed8",
            "aa841fb3b761833ae59d839ecc448e752a64274747f1f4ba726b18f2af0a9454",
            "026c96e3d00fd3be15e8ee1a996bc991c1c72bd9d230829514ee8fad5e583e4e",
            "f990ac1f3993b4a50079525f771ec43e054d8625cebe171c55b8fa933578e83b",
            "177cc93326826a11a34f571c686d44a4bcc05d04ebdf798df6c0a03849475286",
            "1ce3aa9da6c2db1b3311d84b9dcb08cf649fbd3b28fc31d4b713613344669bfa",
            "f20dc74abcbc0262534f607fcbdaafa8e824c54ec577ddd1735ab99b4c63f11d",
            "f3bcb90a9ab349663693f301a6606e9ff0d7c4f46175b1e9cd8520ebe747918b",
            "a82560acdbcff632f4d7e520cd247c9c3bfcf5764cacab7912b029b5bbf50ae6",
            "bdbf7bdad65b6184daf50d748953cc0808581e509d8b35a39541fce7e57c5a26",
            "ab4344e60a189b1a99859721f6c79d9efe06d79cfe700ce7114d8eb3195e7974",
            "d1a5e516d3a4f5d70f1e8015d46303ebcd56f192903786bd600fd05a0cdf1f50",
            "1de610d4df183e4878ad1f19399d68778d8ca260d2da87f63d4897da50569989",
            "c77755e8049e1aafccec4c9be0dcd9b256b29abfedcc149ef1ad59939283b4a5",
            "eb612ec9bb4edfb883b0eaa595765823d6cf47bba20140f5f4103c37e28af0e1",
            "0ce951e8791381b2bc78ad7d0fdc73dcf57fe5db8a63d5f2db9210d6833a0605",
            "a08c7f783e0c9f1eee98af4b08f3f795972488570c1945c13dc849d87a1e38c1",
            "5d6b1dec9262e56744f4b9f2fc09232b5548bc3471b89efac10e802ba5e6eae3",
            "8ec37a79b1313ae670d0ddb07282f8737209e006d483e806dcb8fb62309d24a8",
            "7e959063a876f5a51c4a422ef62c9be4405089a3213bcedb6fcdadb673c8d610",
            "26e5ed5b800dad35ce5a0c3a2d780fbc4fa77986cad86088c3892de0ac235470",
            "d82f92c5dd1c6e6966ed52f0e9a01fc4954b740ef56a1ee8559738f282da7c28",
            "c07e484c4969e310dbaddf63b6bc2ff69d5d0703bf652dfea4fae66999497290",
            "2a3381a2eba7824e1c9feefac6568397c5b918d5bc1f99c089797f372d8b6e4b",
            "22d66658da75ca4d3b0f04d473db53c9f19cd6bcd05b8efd7cc02e490ea75421",
            "c7d6d69df4d794bccc62add4555adf34a4dd67c3d50a61645ab47667cb3d8542",
            "2f21fd2d7b3b890c020a8be0a99ac222faf12c8c0576cfe38da6305bc7fa47da",
            "9a3924895205cc66622e7e75222701abae6f6befea8ff459d24679eb78c8ffb1",
            "41b66b34d0eae074283b311abdb699fead6d5760aae1f505bebe0785552f0035",
            "5c85ffec425b2f53feb6044f5dd485528e138e2aecc38389c8d43226f3600353",
            "18bf21e6d5392e24245ee8e9654ba5ca6bc75854549029cd53df9e54a894749b",
            "d1a713e1d2fbf1e09ad955f88db38945f4a8c454a70be846fbf636bbe364e7bd",
            "469bf3060517481fa1680c74f146bf338ccc60205e9d39d5abb86eb4358e73cd",
            "40c96d735604845e4e73dfa722d2322898f557cce02f1fd5f025b0d8c55edfc9",
            "6c31ac1d05b86d67908a08c7dd7396c05c5702115b54c3d8d4a8656cae9ca2cf",
            "a51233f5a445d1728e87f14239c3b9005ffe5e80cd5c8358d86c9226c9a79a01",
            "2651d49745d4978c05bdf0dcafbd9484344d3dc943cbae7b1977508304e36d01",
            "1148565c589a02fcf4f885c67ddc2d864a085338ab19d636dc4e2ff5b524896f",
            "6fcf7c803a788ae8534d3558156ed26ba1fbb7563b4157d5f7def7c2e18e518c",
            "206c083e5bd9991e1dc62d9135f8258274d4b9dc4eaa9f2ee47bfd424b242a45",
            "69c773b91abf0fa81513ef9d2d77dc5c462ec458a6fdc67d2b9147c22b54b46d",
            "35a7ff1e991671d72b821a0207a5b330dbac46e75fde40829163889072a3752c",
            "424faf49bb54e7fa09c65f3dab7e25251a18f06584fd4cb307e5787b5ba5b357",
            "c79c5f4f6159e098ab9691f2209a67e7ba4098d65b2d0f5f83fe4b82a56ac1d4",
            "aa05147c38d084edb4dbfb53eebbdf7e62ff883a13727858e8d11bdbf65117ae",
            "1c96767acc64b915beb3ecefd9be22b2248e9063705cf3769d4c27972cd54e3a",
            "ed07da5e284f84d8f738f4f9b2c765c533e605a5e3ef18ccc67e921e87597b08",
            "33cbda36c2d6d0fdb9088ba8c3619a8d1868b5a5aa6839eae90f7d998aeb3038",
            "34dd0b482ce85aa6167f1a80b791e1ad60fbd77c67af70e7a69030816e06d7bd",
            "2a86648cb417474db4d2ad23acfb9232659f7991691866b3c100602f3cc8243a",
            "49d3a99fe40c76e15e2c4b398699ada6dbe3abdb7eee915f252648d19d2ffcb8",
            "b1511c9b0efa38116599eaabfb81091b6f0071d36e4c50885af1eefdc6c6e7e5",
            "cc17275592bd3bfdd71cae1ba9716c84d6a6e29a351065c919e507a2fbf94807",
            "f46992c480c50a7cf8aeb01673a1a2260eb130dc078125129949e91aca30875d",
            "f2091c64010151286ea7085f4d3751c1590037ebaa7d8d217c9e9ec1c241c1ab",
            "048415f822f4693d97488ece4e76128b4ff0fb794446bb374cab5b63024271fb",
            "cc4c45def2add8d98a642fc233f81b349e05964005650c198617470439107ec9",
            "14f69e9a0419b2d122a75c0fd7858deeea628c12f8bf6e67f859d3563ee73350",
            "2de1ecb20778c7a680bd92d86077d43f96c7ebb9a38ab08b64de59561cf0e879",
            "99d505a8130bb3eb6e5c214ee57b4d11b1aeb6611accdfede7ebfbe80c7bfb5b",
            "285343b57c6f3651325b196294a2e4c4d66e0183a958acacc70ab63e2b5654be",
            "1cefe07b6da0a1d2863305f82347dbea85a68f484aeb996a88fcd451cdf938a8",
            "07bacc83b071ba1c3f60e321be05040feabd53b27f089f37d0aa86a70e907ea9",
            "de3279498f03156ed20fc37ccfee5ff89f514b48d7a3f445b771b9a31d2d09d2",
            "9e9b87756bee6471e99a02b445c258e76c778ea0921e5fe0ae9b0562bd08ffe4",
            "8890b92fbc51c36f55e59d0c8e250d60576485957ad500fb306e17fba3eb685c",
            "b7d2ba7e0f5f797126901f0ed577030774d2f3cd66afebb6646a066e36e03286",
            "4aa09928dd7bd2bc289396ff9d5b5e23d5e9137b041e88dcc31d5eea3b4a1b4b",
            "d77fdcfb43c842eab473ca3b53e343c44e5bffc7281a699901faa247c7f65a34",
            "dfbcc02e7b4a8c2b2d9153b24bd58a13064d4bff7bc1e2c2cbc0bc49ccd37262",
            "421b31331a0dabc99f5c613ff234ff4506ad8bee9cb2baa040f2de3fa16e6cb5",
            "486f4b0ae6346a828db0c952e01fdaa5af67d3d6ac9080cb720258b212d4e1a7",
            "0e36482661e9507961c9f0c7ef38daf8905e7d5790dcf277073c84715793296a",
            "8f5053e2878f7d05a976eac36f4077fe5a78833b0b740fea440bd608abe2aff1",
            "cda81f454190633285b5b64601ad62ccd21ebc1b06f639f85e04047bcc1552c7",
            "e30a109e05a141518918176395e525f50eba9295cfea1f93a5c1b7e0bd58ccb4",
            "ad045a3652d34053828a5b51ddbcabd83f0b69ac9d920aa471c29714835ddc34",
            "2566ad5114a711bf26730ffa4375a47603dcebbd524358e17ee75855073e6b45",
            "59d65ea9f6904a55ec84c5a21df0668fbd8b8fcb0ecce4809757f073f071fb5a",
            "78b58d7428d9a4d3f458376248705c6cddcde8660a5505218ea7e76b1853c726",
            "51108b3a55697b53c5271d3d0ddafb1d1d322249fd6dcdb1b6a5d2e5323dec1d",
            "f97848dd304f38a95c8ff3cdfd85dfbafe4995bff14fb3f2174354c26fc6168f",
            "eb403a775d22678362ca3f2eb5f1ff38435937949d5e32fa43ca8296982ee8ae",
            "f8ede0398fc11048d7835d7ae64cd93a3aff7c797e366f61dec8fe5077aa8145",
            "54f8e84af48236c4b722fb3bbdc798704c03af304a0aae05ad36f93339dbc2ef",
            "43d1aa02beb2c1c4129c19b27d480eb17a1e54f009209302fe14571ed19d56b8",
            "7cc87ed2349e1c837ba62bf634514b22c9313230dd1478ee9380eec54a56665d",
            "addc2401559355bdad11b5cee4948d1ee43f26e330c4c1a4745b7dec8c9ff810",
            "c5f3fe424688535bdb2b09107d0f21542f45d52b5e9440900a805852a7cd728c",
            "23e3093bb4a325963ee5acbbd82a66275bf48c022e1f91d0594e9c3d702ee4df",
            "25e60cb96a5836f7055aaf14d638ba8fcbca20ae55bf4c8d8342090e87358b7c",
            "f77a750eeeb1cdae5af141ecd4d0d5b28ca5bf71915b3f5b7fb7eb1403e8195f",
            "a90ea650a076bc9cc4d656785aae5c5bbbd58f43fa7e6d88d5865a6e1cf4c731",
            "13502cf06c4e3cdc5da533d40757ea46f06d5d19791937b849a3d1e9a6696b4b",
            "c181f9190aa3007b664f758578aeff7ddb19a87eebaef49c25079ba9dd342714",
            "11dc06ec5f85b372c0c455e3401eac2f03e6f91f4229ae18c0bd5d29f901b7d4",
            "ecd65ba08d8ce21d2b0207919153907d53732e5bb1bf6e1b082a9c941072cdb8",
            "137f244c25bfa6f7d1a012b30ac14ad666b35dc3ab1321f49535d373d7f27ec8",
            "c25ccd53192f5060702b855b6907771d1b2d6d4847d31d0d30a7040bc0bfd170",
            "a2700c91b42c7e3acaceaeb751931b20f35519d8afceacb2630bfe10e40b122c",
            "3ab574319fa98a2d23d47660527f614656abfeb26f573597ab4d8188eee9fea1",
            "664ee598211a5cb775bbd8616d926492f0fa3a00f7cad9f78a26b3afe095e535",
            "6e635e406aca8468a0c856b951833181250edfbfd6bdf2fc4050eefd04ba754a"


};
*/



/* ~~~~~~~~~~~~~~~~~~~~ GOERLI ACCOUNTS  */
    // ---- Private keys for input
    public static String[] workersData = { 
		
	 "9641d4e94992297fbd18fa90367fd11c60c56bc1413ef4a269b107da2dd7686b",
	  "c1d0ded270e3fb4b0e42f1a9d55744adda5f3b48683a91c27229082222b097c3",
	"2a6045a8b5e14f6634612514b097b8cb6625b5ddb6ad997813609a194512d3ba",
	   "fa760ec23dd2930e0f25da864509f2edd0b13dbcb805c2fe2998a3c985a0fe0d",
	"3ce26aa058e4af8ab7d26f2ed39c5b1e75f61639ebb2bd3c55dd5c4189e2ce1b",
	 "2ce95e2471a7401f4edda88c8d779a76005486c53ca3c16dce7cfebf5795a97c",
	"ffdd0cf8241d1c2e3c5d2112050f5302746c297193fd75708e0f05ea39f1450c",
	"7073bd20d5210df3c3ee50176fa5db495631f57fa0bf45abe0c7bbb649e1e7d5",
	"886796805623787f486931e77c27093352853718e6c725b0719377888fba9c33",
	"fec65299b6788c24598ab6a04cc2c315b74eeea40217e86117d2e0eb71d37bc4",
	"4979567ee9f54e65f0cc3591d9cb29a4cd480a1e5789f16ed6897efd9819b131",
	"04b47efa55afc93e1ccf3d91e4cd0e55f203a15d77f2393982944454174c290e",
	"4af1770c3a255433fea1854f6a47af5b0ef442586fde969593648e4f8a096553",
	 "dbc62a0dcbcb11d0b1546a8ecef7859c93e5fc00df6a6190801ef2561fac5d9d",
	 "32ea0a39ba2f23e7242b2141806ec877e228cff2fabac4d0925fe90cdb58f173",
	"cf81cf43dfa1f48fc37e178b6050fe9666dc03750c06565aa8d6442bf2920a17",
	"c92ec267d3b1fa3e79cde49d94f9767d7b823d43d72d8ee6e547bb7bca8861fb",
	"1976223bdf20e3560def650c1a8f0f4b64932c66828aab6999845fa444192322",
		 "8a3a1f804e9445d85b4e2f3ca4bfb476961575239e99719ac2c171b00887c0b5",
	"305c7db97802cac5138530f9c5d369628bc8f3f1e771bb643e7c76586eb4847f",
	"74ec5461f33537e690fa35beea49d2a12f5dfb15f360700f0797b015a791c9e5",
	"605f583433abc3bec7a178e9d6d2b5d44fed68fb104049653463351fef5a9bb9",
		 "11a1ac92c9a279bac919cddc94976bcb2809bc308572cd703a3f943c3415395e",
	"beac2ee57d64fe1bfed4e0722f23dd1c89a7d59c1af10ca0220e1765f9ead963",
	"59422c943940e6d39bb161c2200c2a712a70a3690285a717ad2db32a5b03c5c1",
	"7493e6fe5d2019b52b8cd521db329fc63eefd7a43fd11646a4def4549d5a4906",
	"b1a567c00f6ecf0bd267eb56aa7d2d818f8a976a4d318d1d3cb2bcdbe93ec0ff",
	"8bd8bec8036ada2ca9f44ba75d87b757e8c2774311cf36958fdcbc0fb49cbb6a",
	"735acac8fd68e969a25c1dced928b88720d2cd4b314ce2da422ef0c2faf030d4",
	"ee332c2198bf1cc98eacfcf049f9117c504d3356c8b41d08ff180b9ab9d97e90", // Worker 30th
	"351c9dcf2b68768a41d3b48c5fbcd76cab3ac8896b16d0d70cbe01ee0ee5e833",
	"49ef92a94c6ae323b66901ed02a7a3ea3dc242e800535b98d04ba0bcdd181e9e",
	"2ce8c94d6442c8a70793feb4c2f14998b8cdfc9fbe6244203a680f8a9183ae0c",
	"f26d09142798b6c3403c08135754dba06f857825dcc12e2b600c1f7d792a491f",
	"13db972c3e6cc7cacc3bfd9e6d5423fd8ab98e6066c6853e34a79694218916ea",
	"6c8898e793bb80a266b37d2d37b2569188287afee719a160ebace4283ca2ed3d",
	"5472eb64717f18b4dacb44fea5520c06dbf9bd8f9138f199b51ce007b3731182",
	"66570264d6c8c5b2a38d16c3b6c2e050f153df99725d3f668eae507c6c6693a2",
	"5c6d00ab30243efefe9f9e08a55b964bb78bd4493d2826db1e8633a6f716a163",
	"a4dff2ac633edac85eaa9e9fe2793843919ba2ac068ee93546c650ac9a1c9a10",
	"2820c9eb66aaaae95f9cacd31adafa03c55574da5affe40b2d3d27f4ff80445b",
"f2fc20425dfb66fd3a5b60dfc8277f77a523ca7c1aec3cde90e7ca2965064812",
"3f79b9ef5b5be78af609c6a3f033e1acef34394640fc4099495278a7b85797fe",
"822471d126d595e806d156a634c362ab8438ae83e2eeaa68ec421fdeb5e64523",
"a15319414bc2dca810e14e5f1b784c719ccb759104d8a0993460a9e863398c7a",
"433ace4980beba6ed6a82e202c4adc9e5fb294020aafab958cc382eb8a2bed80",
"7c9fa231f161d081fcf5221082d36d0c19446e1047686418f8a2a815992c3693",
"22b9230d888d15e5d2e0a66be8b4aadbc29910888ae4b648e3eaf45ff2691756",
"64f0397107084d6cb5c409e618e308a9e581881e3af9bf4437b538bc4be0b345",
"c01b3543373c7bd525a1f25c45e4cc506dc423c02809d645d096a3c7a98f5bd2",
"72c2a8a71e6bec0971b4904bddd4bb946e13d3e128f9199a616e4078fb6a7c07",
"695a8f203d399f7f3dd9be80c66405762e1ae59a13daf30dd3201ca57662f306",
"5ac3ea7054a3b49840bd9114ad36ed33e1f6397a5f9f4ae97e006f6f504769fb",
"0143f2ef3689ce9e1b64bc52b5949212a982d78efb5444e9874ccf84aa79c80f",
"ce739a06812b681f5dd5dfbc270d0f4f7b95edf66b3e1e8e1f2c243bb880347a",
"ad0e7fbe6ad3b6f8209f6b39e0b325825f9ec9d11b4bccc951000fc94e7239f9",
"40ddcd463bca7c383e7dee726d446936ba7594bfd9827fc65ed1541f23017f45",
"7bc4b9619f8d3e1dc94aa05ee413f62568c31b9cac4c27dd2dc3b141d98cdae2",
"41083ce4804cf69dba23ad03454916c950c3f4c3456b722be4833767091ef0dd",
"556b60fd9b610af5a072dd10d0d1f932d7c12a165918bb678400280ef90180f3",
"e00470b8d62bcf9ed829f2186a2d04c8d42f9725865f5e70c94dab33ec2a93bb",
"5789e56d1f2194c184df902cf4f819aea3d93104e84decf8bfc8c886ddc00309",
"41798698f583ece3e4a70161e31a79829d3127d691eec482227807fe85d57c38",
"b1ada44c73aefbee90560ca829a335e61269703a219d26ae8101d66fd9c6cc56",
"9057cf63ea069fc4d737fd04510d6426dc180f9d1a8f0467ec6b41f71d57aff9",
"f6bc3a2272ac2b4d8a0ce5e7a266450f5718ada3c9642f2cab0fd1878c849fac",
"0e40977e70920454553e30c41953568e5e51ca678ab0d43799afee88b6c3600a",
"cd0585418780ec5045e0f2aa4efbbb98fbd09b3dddc813915907a5065154b262",
"a6762b05c130b3a0468f81437322f3a2faeee0d8c25717074887b5486bdefef6",
"e5367c652f89ab6e7d8a6d27bc9486af82d38cba4d42d74e5ef5a4f1be632af5",
"72a4ea16e4fab96764b78c3e932cbe4a7e2a4bd000eb7c1f9defe94131639d1e",
"8bd97f9594487d7ded876489d00d1bd98546935d5c00e527b1078bd836db5b63",
"5d2208bc9b770dbd1f6e7016bc1741bc846eaca2921822eae0f5b344aa766b1f",
"5c4fa7d42aa2eb86c318879e4c94c6034e5bcc30425235838a0ef6d5c20bc902",
"2b042a4436dc643a3eadeb1909d3b9cb3c437b3bdaca1e09e05e564b891ec607",
"b7ecfb93f8d4bac52d22eb49ceb9f3ffcdefda56667ad079f1739600fcb435c1",
"0003df936e93dea54721655370e3e0eb11e4abd5980e64ff7b20ca51449469d0",
"477b9d373e1e158fabf4034a4948cac562d4a6d51da1ea082d74c9acfc626afe",
"4375ef5257253de4d20b5943437dcb5af842468086ea5c3e28f82e18d4fcee5e",
"23e942f19f2d7d9fbc36ce610a80c33382b1a541656fed629f5d472767f5b183",
"912e36aab6869a34431586519403133fc854da22dc83a01630e5e9a85c39d029",
"9e3e5100cf2a8672347ab4ff540384997fae0069cb90c159e427585a987873c5",
"8a4f2b1f54cadbb1c776181b2f45db280c868afaaefbe1a023f0b9a0720d4d9c",
"51fb704c59c1047df49da405f1ccd86f8f3b78eac30c0cda4c7a65c22f728229",
"725911bf8613632aa55c206840fd2f49e27ef34f62bb4c7d6819f966219f9715",
"36944ac09515e3aa445d0ba3b83a2d98c30e65fc4c32c35feb2cb685c97e57b6",
"0507c516ee07d5cc108b07f859b253cb0c1cdbc38165cc304db0c361198fe3f5",
"3eb071992c345f36d7ccd86b3cc5ca517ce8c16b3a1762f90431418a6973735a",
"79bc9f1405c410635a9521bbe754cf224d27ebfe2962df9223cf01a152967397",
"91767c2c6015313553c5bf6728f2706f1efb561a68bf9d33674ece66517cfcbc",
"5cbd33bcb40aa00577805670346a09091fbaf66c32f424715996ef4bf5865286",
"a5e2f3c7bf91dfff5dc7d32c08fd20c9716ec6a3f080b43bd2d8a81a9f8f9f7e",
"9bf0414008ea42255a9b6f7efee0af25239115630843125248107377b6085ed4",
"596cf0a08b4430b62e62e18182ead4456e6aba8f69df953c683b316b7783a61a",
"d1643f6417b0616f09cf9dbb1fdf605a047b0fc692bcdde873373523025b46eb",
"105a6ea8756945cd893733e87e1bd5ad16c0414485bd4e80364f142feed133fe",
"91dbfb25e8c32e1a1f30d7942375fb3fc7c68c1c688c8b8fcb0cd35e47f17c0d",
"c978b63539b23ca2f141815d62e2706632cf2f625d06449122c437a9617f8671",
"e8869cc460591377acd3954b98fba5d9cdb1de1e5ffd29a5ba319f0bec5daf6a",
"318f74e10c0393b50160eaf4fcdab5b1337db55d7bb6fd1b93529d7bf8ed505e",
"c7c20551712869d022749d359aa27ab7f903d67ed8ebc29dc94d738e3185d0db",
"6a65ebd6f416121590281a8302c7c2541d8a0d8b8b67a2b3df16b0ea09c2c9f3",
"ceaac40cbea04cc69e0735a18ca2fd398cf2b7f961d39a0cb05c095aacf16b86",
"68612a75f9cc14655cccbd09f7fb835ff007f546377716d53a6a5d6bd82457d9",
"04b6e11bcb729ccc5f2de0ce715a817f09ef05763d5fd1ed4598aa8eb2551fa5",
"ef1e80f4ace9193b1ea9d2938ddc353ec41769b469501c17e7a05ee4c091650d",
"b17101e6c99f52e22f26194e23745390e00fcc6fad8c36ba5f2f5783975ad12d",
"c4bdc82925b41158614adc2cacc5aef9c84507a7adbf80477b3274690da6a4a7",
"6c11a68cb43994d046bbc8083299a6df5717caded3b2460a6a8da1e8b45a6f55",
"85008d66c43d84098b20fd232d2c20acb1a48320184e390f2be774f06afcaf9d",
"8e9c445b3802607f1d1ba71451cff54770fb72ba9781bb4488ece83a13ba5964",
"9f132614d2e0c9006e501963bc623f4a6567f1238c2ef51d27e95e81dcad8c67",
"5959b53d67f598a3c3b186a8a97e14444b773f47d3a3cd5902afc7ac4f49cf27",
"095563fc58e3a5bec2de0bebb76e7868b674e20265ac2fd8d5b27d7391571e3d",
"0ee6c254967556acb08e3dc9fb6f5dc63bdc9441b34313f91cb59c3f09ad3561",
"136b1ac14287c3b2ef4873ed114a67ee7ea08c21b1def76c7cc718d2b5ce6877",
"ed55e1f6803620473a881c268ebf4429fb002901b8cd2b117d384735e5beb022",
"6045900db956548a082e649cf34c14c6dac59680e0324f4b3788905908d7fc57",
"ae4b8830015b18067b17877ba287a8977d6ba340efe4af8eeca7247239d93f5f",
"d472a421995a62d8b90c652639bc4ffca9eeb7ea7c0fdf13b69e889cc71bfa36",
"3404578af80b68bece62708ea787ddb823f05a76ad24f0b7947bda33b06d14aa",
"4996ecec6862d9c989625e7dcf177babb0c6b3c5ee277c7de57cdbca784f5d9e",
"e5010c6becfe00d006836c1f1c01a903dd1a720d6e2b1ebb0374c279aa928bf3",
"c3ef9f34aeb9f9aaffc2b1320e816ea0a83b12e26c589173631de4be3e97febb",
"24b6f99fc03a93c33d3b54ce91e8094ef952fbcc8aecc29829f2ca0786636fe5",
"9e0d7e64e8522cd87bd018eb280b67cca231cbdba5d3340f626cb57b2e5197a0",
"de5259097bee139a7d64fd7588ca38fea2d5bc3b29256deff7e2f05c61a7959d",
"83cca88de066e5233e4ec10e2153b2eccb235df7eb16812b682cb383b7848a84"

    };


  Web3j web3j;
  int currWorker;
  String contractAddress;
  private final Logger log = LoggerFactory.getLogger(workerResponse.class);

  // worker response tuple variables
  List<String> answer_encryption=new ArrayList<>();
  List<String> quality_commitment=new ArrayList<>();
  List<String> randomness_encryption=new ArrayList<>();
  String hash_tag;
  List<String> _proofs=new ArrayList<>();
  List<String> address_encryption = new ArrayList<>();

  // helper variables
  public static String authcalC0;
  public static String authcalC1;
  long startTime;
  long endTime;


  // constructor
  public workerResponse(Web3j x, int y, String z) throws Exception {
    web3j = x;
    currWorker = y;
    contractAddress = z;
      // start the time counter
      startTime = System.nanoTime();
      Process proc = Runtime.getRuntime().exec("python runSR.py");
      Scanner scanner = new Scanner(proc.getInputStream());
      scanner.useDelimiter("\r\n");
      List<String> statement = Arrays.asList(scanner.next().split("\n"));
      answer_encryption.add(statement.get(0));
      answer_encryption.add(statement.get(1));
      quality_commitment.add(statement.get(2));
      quality_commitment.add(statement.get(3));
      randomness_encryption.add(statement.get(4));
      randomness_encryption.add(statement.get(5));
      hash_tag = statement.get(6);
      address_encryption.add(statement.get(7));
      address_encryption.add(statement.get(8));
      scanner.close();
      proc.waitFor();

/*

      // first compile the circuit
      log.info("Compiling ProveQual for Worker: " + String.valueOf(currWorker));
      proc = Runtime.getRuntime().exec("zokrates compile -i newProveQual.zok ");
      proc.waitFor();

      // then create the proof
      log.info("Generating the proof..");
      proc = Runtime.getRuntime().exec("zokrates compute-witness -a $(cat mywitness.txt) ");
      proc.waitFor();
      proc = Runtime.getRuntime().exec("zokrates generate-proof");
      proc.waitFor();
 */   

      // get the compressed points
      proc = Runtime.getRuntime().exec("python compressProof.py");
      scanner = new Scanner(proc.getInputStream());
      scanner.useDelimiter("\r\n");
      List<String> tempProof = Arrays.asList(scanner.next().split("\n"));
      _proofs.add(tempProof.get(0));
      _proofs.add(tempProof.get(1));
      _proofs.add(tempProof.get(2));
      _proofs.add(tempProof.get(3));
      scanner.close();
      proc.waitFor();
  }

  public void runWorker() throws Exception {


  }

  // function to submit a response
  public void run() {
      try {

          /* upload the data to the smart contract*/
          BigInteger maxPriorFees = BigInteger.valueOf(1_000_000_000L);
          BigInteger gasPrice = BigInteger.valueOf(1_000_000_000L);
          Credentials worker = Credentials.create(workersData[currWorker]);
          StaticEIP1559GasProvider staticEIP1559GasProvider = new StaticEIP1559GasProvider(5, gasPrice, maxPriorFees,  DefaultGasProvider.GAS_LIMIT);

          CSTask_sol_crowdsourcingTask CTcontract_worker = CSTask_sol_crowdsourcingTask.load(contractAddress, web3j, worker, staticEIP1559GasProvider);
          
          TransactionReceipt receipt1 = null;
          TransactionReceipt receipt2 = null;
          //List<RemoteFunctionCall<TransactionReceipt>> receiptList = new ArrayList<>();
          try {
              EthBlockNumber result = web3j.ethBlockNumber().send();
              BigInteger d0 = result.getBlockNumber();
              System.out.println(" Latest Block Number: " + d0);

              // get block number for time calculation
              receipt1 = CTcontract_worker.submitresponse_method_encryptions(new BigInteger(answer_encryption.get(0)).toByteArray(), new BigInteger(answer_encryption.get(1)).toByteArray(),
                      new BigInteger(answer_encryption.get(0)).toByteArray(), new BigInteger(address_encryption.get(1)).toByteArray(),
                      new BigInteger(randomness_encryption.get(0)).toByteArray(), new BigInteger(randomness_encryption.get(1)).toByteArray()).send();

              receipt2 = CTcontract_worker.submitresponse_method_commitment(new BigInteger(quality_commitment.get(0)).toByteArray(), new BigInteger(quality_commitment.get(0)).toByteArray(),
                      new BigInteger(hash_tag).toByteArray(), new BigInteger(_proofs.get(0)).toByteArray(), new BigInteger(_proofs.get(1)).toByteArray(),
                      new BigInteger(_proofs.get(2)).toByteArray(), new BigInteger(_proofs.get(3)).toByteArray()).send();


                  result = web3j.ethBlockNumber().send();
                  BigInteger d1 = result.getBlockNumber();

                  BigInteger totalGas = receipt1.getGasUsed();
                  totalGas = totalGas.add(receipt2.getGasUsed());

                  System.out.println(" Latest Block Number: " + d1);
                  System.out.println("Gas Price: " + gasPrice);
                  log.info("Encryptions submitted by Worker: " + receipt1.getFrom());
                  log.info("Data submitted by Wokrer " + receipt2.getFrom());
                  log.info("Total Gas Used by Worker " + totalGas);
                  App.diff.add(d1.add(d0.negate()));
                  endTime = System.nanoTime();
                  System.out.println("Time required by the worker: " + (endTime - startTime));
                  requestorApp.workerTimes.add(endTime - startTime);
          }catch (Exception ex) {
              ex.printStackTrace();
          }

          log.info("Average block difference... " + App.diff);


          /* creating worker specific witness for authqual */

          String tempQual = "";
          String tempEnc0 = "";
          String tempEnc1 = "";

          // decompress the encryptions
          Process proc = Runtime.getRuntime().exec("python decompressPoint.py " + answer_encryption.get(0));
          Scanner scanner = new Scanner(proc.getInputStream());
          scanner.useDelimiter("\r\n");
          List<String> tempList = Arrays.asList(scanner.next().split("\n"));
          tempEnc0 = tempEnc0 + " " + tempList.get(0) + "  " + tempList.get(1);
          requestorApp.reqAuthCalc0 = requestorApp.reqAuthCalc0 + " " + tempEnc0;
          scanner.close();
          proc.waitFor();

          proc = Runtime.getRuntime().exec("python decompressPoint.py " + answer_encryption.get(1));
          scanner = new Scanner(proc.getInputStream());
          scanner.useDelimiter("\r\n");
          tempList = Arrays.asList(scanner.next().split("\n"));
          tempEnc1 = tempEnc1 + " " + tempList.get(0) + "  " + tempList.get(1);
          requestorApp.reqAuthCalc1 = requestorApp.reqAuthCalc1 + " " + tempEnc1;
          scanner.close();
          proc.waitFor();

          proc = Runtime.getRuntime().exec("python decompressPoint.py " + quality_commitment.get(0));
          scanner = new Scanner(proc.getInputStream());
          scanner.useDelimiter("\r\n");
          tempList = Arrays.asList(scanner.next().split("\n"));
          tempQual = tempQual + " " + tempList.get(0) + "  " + tempList.get(1);
          scanner.close();
          proc.waitFor();

          proc = Runtime.getRuntime().exec("python decompressPoint.py " + quality_commitment.get(1));
          scanner = new Scanner(proc.getInputStream());
          scanner.useDelimiter("\r\n");
          tempList = Arrays.asList(scanner.next().split("\n"));
          tempQual = tempQual + " " + tempList.get(0) + "  " + tempList.get(1);
          scanner.close();
          proc.waitFor();

          tempQual = tempQual.replace("null", "");
          String tempEnc = (tempEnc0 + " " + tempEnc1).replace("null", "");

          App.requestor_created.workerAnsEnc.add(tempEnc);
          App.requestor_created.workerQualCom.add(tempQual);




          if (currWorker == (App.workers_required - 1))    // if the responses equal the number required then move to aggregating responses
          {
              Thread.sleep(50000);
              log.info("Required responses received, waiting for SNARK verification...");
              try {
                  /* Creating witness files */
                  // authcalcWitness.txt
                  BufferedWriter writer = new BufferedWriter(new FileWriter("authcalcWitness.txt"));
                  writer.write(" 16540640123574156134436876038791482806971768689494387082833631921987005038935 20819045374670962167435360035096875258406992893633759881276124905556507972311 " +
                          "17324563846726889236817837922625232543153115346355010501047597319863650987830 20022170825455209233733649024450576091402881793145646502279487074566492066831 2 ");
                  writer.append(" ");
                  requestorApp.reqAuthCalc0 = requestorApp.reqAuthCalc0.replace("null", "");
                  writer.append(requestorApp.reqAuthCalc0);
                  writer.append(" ");
                  requestorApp.reqAuthCalc1 = requestorApp.reqAuthCalc1.replace("null", "");
                  writer.append(requestorApp.reqAuthCalc1);
                  writer.append("  123 2 ");
                  writer.close();
//                        long stopTime = System.nanoTime();
//                        System.out.println("Total Time Taken : " + String.valueOf(stopTime - startTime));
//                 System.exit(0);

                  //App.requestor_created.verifyProveQual();
                  App.requestor_created.allinOneSQ();

              } catch (Exception e) {
                  System.out.println(e);
              }
          }
      } catch (Exception e) {
          System.out.println(e);
      }
  }



} // class block
