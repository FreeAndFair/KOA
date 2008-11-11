/** -----------------------------------------------------------------------
 *
 * com.logicacmg.koa.security.GenerateKeyPair.java
 *
 * -----------------------------------------------------------------------
 *
 * (c) 2003 Ministerie van Binnenlandse Zaken en Koninkrijkrelaties
 *
 */

package com.logicacmg.koa.security;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * main application for the keypair generation
 *
 *
 */
public class GenerateKeyPair {

  public static final int PRIVATE_KEY = Cipher.PRIVATE_KEY;
  public static final int PUBLIC_KEY = Cipher.PUBLIC_KEY;
  /* Gebruikte algoritmes: het algoritme voor het sleutelpaar is RSA met
   * een sleutel lengte van 512. In de KOA applicatie is de sleutellengte
   * een configureerbare parameter (property). Het algoritme voor de opslag
   * van de sleutel in een bestand is password based encryption met gebruik
   * van MD5 en DES.
   */
  private static final String KEYPAIR_GENERATOR_ALGORITHM = "RSA";

  private int KEYPAIR_KEY_LENGTH = 512;
  private static final String KEY_ENCRIPTION_AKGORITHM = "PBEWithMD5AndDES";
  private static final byte[] SALT = {
    (byte)0x19, (byte)0x36, (byte)0x78, (byte)0x99,
    (byte)0x52, (byte)0x3e, (byte)0xea, (byte)0xf2
  }; // the wrapped key pair
  private KeyPair keyPair;

  /**
   * Zero argument constructor for generation a key pair
   * There will be a public key and a private key available
   *
   * @throws GeneralSecurityException
   * This exception will be thrown when there is a problem
   * while generating a key pair
   */
  public GenerateKeyPair() throws GeneralSecurityException {
    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEYPAIR_GENERATOR_ALGORITHM);
    keyPairGen.initialize(KEYPAIR_KEY_LENGTH);
    keyPair = keyPairGen.generateKeyPair();
  }
  /* Het ontcijferen van een sleutelpaar. Omdat van een sleutelpaar altijd
   * of de privé sleutel wordt gebruikt (tijdens de stemopneming) of de
   * publieke sleutel (tijdens het stemmen) wordt altijd maar één van de
   * twee ontcijferd en ter beschikking gesteld.
   */
  /**
   * Constructor for decryption of a key.
   * Only the public of private key of the <code>keyType</code> will be available the other will return null
   *
   * @param password The password used for decryption
   * @param criptKey A stream with the encrypted key
   * @param keyType The type of the key public (</code PUBLIC_KEY>) or private (</code PRIVATE_KEY>)
   * @throws GeneralSecurityException This exception will be thrown when there is a problem with decryption
   */
  public GenerateKeyPair(String password, InputStream cryptKey, int keyType) throws GeneralSecurityException, IOException {
    PBEParameterSpec paramSpec = new PBEParameterSpec(SALT, 20);
    PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
    SecretKeyFactory kf = SecretKeyFactory.getInstance (KEY_ENCRIPTION_AKGORITHM);
    SecretKey passwordKey = kf.generateSecret(keySpec);
    Cipher cipher = Cipher.getInstance(KEY_ENCRIPTION_AKGORITHM);
    cipher.init(Cipher.UNWRAP_MODE, passwordKey, paramSpec);
    byte[] dummy = new byte[128];
    int length;
    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
    while ((length = cryptKey.read(dummy)) != -1) {
      byteArray.write(dummy, 0, length);
    }
    if (keyType == PRIVATE_KEY) {
      Key unwrappedKey = cipher.unwrap(byteArray.toByteArray(), KEYPAIR_GENERATOR_ALGORITHM, keyType);
      keyPair = new KeyPair(null, (PrivateKey) unwrappedKey);
    }
    else if (keyType == PUBLIC_KEY) {
      Key unwrappedKey = cipher.unwrap(byteArray.toByteArray(), KEYPAIR_GENERATOR_ALGORITHM, keyType);
      keyPair = new KeyPair((PublicKey) unwrappedKey, null);
    }
    else {
      throw new InvalidKeyException("criptKey does not represent a wrapped key of type keyType");
    } 
  }

  // Het ophalen van de privé sleutel uit het sleutelpaar:
  /**
   * Returns a private key if available otherwise it returns null
   *
   * @return PrivateKey private key
   */
  public PrivateKey getPrivateKey() {
    return keyPair.getPrivate();
  }

  // Het ophalen van de publieke sleutel uit het sleutelpaar:
  /**
   * Returns a public key if available otherwise it returns null
   *
   * @return PublicKey public key
   */
  public PublicKey getPublicKey() {
    return keyPair.getPublic();
  }

  // Het vercijferen van een publieke sleutel met een wachtwoord:
  /**
   * Encrypt the public key with a password and returns the encypted key with the outputstream
   *
   * @param password The password used for encryption
   * @param output The encrypted key
   * @throws IOException This exception will be thrown when there is a problem with the stream
   * @throws GeneralSecurityException
   * This exception will be thrown when there is a problem with the decription
   */
  public void getPublicKeyEncrypt(String password, OutputStream output) throws GeneralSecurityException, IOException {
    if (keyPair.getPublic() != null) {
      getKeyEncrypt(password, output, keyPair.getPublic());
    }
    else {
      throw new GeneralSecurityException ("This key does not excist");
    }
  }

  // Het vercijferen van een privé sleutel met een wachtwoord:
  /**
   * Encrypt the private key with a password and returns the encypted key with the outputstream
   *
   * @param password The password used for encryption
   * @param output The encrypted key
   * @throws GeneralSecurityException
   * This exception will be thrown when there is a problem getting the encrypted private key
   */
  public void getPrivateKeyEncrypt (String password, OutputStream output) throws GeneralSecurityException, IOException {
    if (keyPair.getPrivate() != null) {
      getKeyEncrypt(password, output, keyPair.getPrivate());
    }
    else {
      throw new GeneralSecurityException ("This key does not excist");
    }
  }

  /* Deze methode implementeert het eigenlijke vercijferen van de privé
   * sleutel of de publieke sleutel van een paar met een wachtwoord en de
   * opslag ervan:
   */
  /** 
   * Encrypt a private key or public key with a password and returns the encypted key with the outputstream
   *
   * @param password The password used for encryption
   * @param output The encrypted key
   * @param key The public or private key
   * @throws GeneralSecurityException This exception will be thrown when there is a getting the key
   */
  private void getKeyEncrypt(String password, OutputStream output, Key key) throws GeneralSecurityException, IOException {
    // create a secrate key whit the password
    PBEParameterSpec paramSpec = new PBEParameterSpec(SALT, 20);
    PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
    SecretKeyFactory kf = SecretKeyFactory.getInstance (KEY_ENCRIPTION_AKGORITHM);
    SecretKey passwordKey = kf.generateSecret(keySpec);
    // encript the key
    Cipher c = Cipher.getInstance(KEY_ENCRIPTION_AKGORITHM);
    c.init(Cipher.WRAP_MODE, passwordKey, paramSpec);
    byte[] wrappedKey = c.wrap(key);

    // write the key to the stream
    output.write(wrappedKey);
  }
}

