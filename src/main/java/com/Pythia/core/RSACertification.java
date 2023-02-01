// get required package
package com.Pythia.core;

// Crypto imports
import javax.crypto.Cipher;
import java.io.InputStream;
import java.security.*;
import java.util.Base64;
// conversion imports
import static java.nio.charset.StandardCharsets.UTF_8;
// for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// arrray list
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

public class RSACertification extends Thread {
  private final Logger log = LoggerFactory.getLogger(RSACertification.class);
  String workerInput;
  String certificateTemp;

  // constructor
  public RSACertification(String _workerInput) {
    workerInput = _workerInput;
  }

  /* Registration Authority generates a certificate for each worker
     taking their input as the message
     *** certificate returned as a string ***
  */
  public static String getCertificate(String workerInput, PrivateKey privateKey) throws Exception {
    Signature privateCertificate = Signature.getInstance("SHA256withRSA");
    privateCertificate.initSign(privateKey);
    privateCertificate.update(workerInput.getBytes(UTF_8));
    byte[] certificate = privateCertificate.sign();
    return Base64.getEncoder().encodeToString(certificate);
  } // method block

  public void run() {
    try {
      Signature privateCertificate = Signature.getInstance("SHA256withRSA");
      privateCertificate.initSign(App.master_private_key);
      privateCertificate.update(workerInput.getBytes(UTF_8));
      byte[] certificate = privateCertificate.sign();
      //App.certificate_list.add(Base64.getEncoder().encodeToString(certificate));
      certificateTemp = Base64.getEncoder().encodeToString(certificate);
      return;
    }
    catch (Exception e) {
      System.out.println(e);
    }
  } // run block

  public String getCertificateFromThread() {
    return certificateTemp;
  }

  /* method to verify the certificate */
  public static boolean verifyCertificate(String workerInput, String certificate, PublicKey publicKey) throws Exception {
    Signature publicCertificate = Signature.getInstance("SHA256withRSA");
    publicCertificate.initVerify(publicKey);
    publicCertificate.update(workerInput.getBytes(UTF_8));
    byte[] certificateInBytes = Base64.getDecoder().decode(certificate);
    return publicCertificate.verify(certificateInBytes);
  } // method block

  /* method to encrypt a message (input from the worker)
     ** This RSA encryption not required for certification **
  */
  // function to encrypt the responses
  public static String encrypt(String workerResponse, PublicKey requestor_publicKey) throws Exception {
    Cipher encryptCipher = Cipher.getInstance("RSA");
    encryptCipher.init(Cipher.ENCRYPT_MODE, requestor_publicKey);
    byte[] cipherText = encryptCipher.doFinal(workerResponse.getBytes(UTF_8));
    return Base64.getEncoder().encodeToString(cipherText);  // return the cipthertext as a string
  } // method block

  // function to decrypt the responses
  public static String decrypt(String ResponseCipherText, PrivateKey requestor_privateKey) throws Exception {
    byte[] bytes = Base64.getDecoder().decode(ResponseCipherText);
    Cipher decriptCipher = Cipher.getInstance("RSA");
    decriptCipher.init(Cipher.DECRYPT_MODE, requestor_privateKey);
    return new String(decriptCipher.doFinal(bytes), UTF_8);
    }

} // class block
