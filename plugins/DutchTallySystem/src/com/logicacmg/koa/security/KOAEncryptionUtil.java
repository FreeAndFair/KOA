/** -----------------------------------------------------------------------
 *
 * com.logicacmg.koa.security.KOAEncryptionUtil.java
 *
 * -----------------------------------------------------------------------
 *
 * (c) 2003 Ministerie van Binnenlandse Zaken en Koninkrijkrelaties
 *
 */

package com.logicacmg.koa.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.security.Security;
import com.logicacmg.koa.exception.KOAException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.logicacmg.koa.constants.ErrorConstants;

/**
 * Utility class that contains does the encryption actions
 * for the KOA System.
 *
 */
public class KOAEncryptionUtil {

  private final static String PUBLIC_KEY_CRYPT_ALGO = "RSA";
  private final static String SECRET_KEY_CRYPT_ALGO = "DESede/CBC/PKCS5Padding";
  private final static String SECRET_KEY_GENERATOR_ALGO = "DESede";
  private final static int SECRET_KEY_LENGTH = 168;
  private static final byte[] SALT = {
    (byte) 0x19, (byte)0x36, (byte)0x78, (byte)0x99,
    (byte)0x52, (byte)0x3e, (byte)0xea, (byte)0xf2
  };
  private static KeyGenerator xKeyGen;
  private final static String encryptionKey = "KOAKey";

  static {
    java.security.Security.addProvider (new iaik.security.provider.IAIK());
  }

  /**
   * encrypts the String with the public key and returns the encrtypted data
   * as a byte array
   *
   * @param xRSAKey the public key used te crypt the data
   * @param sData the data that is cryped
   * @return byte[] the cryped data
   */
  public static byte[] encrypt(PublicKey xRSAKey, String sData) throws KOAException{
    try {
      // generate triple des key
      if (xKeyGen == null) {
        xKeyGen = KeyGenerator.getInstance(SECRET_KEY_GENERATOR_ALGO);
        xKeyGen.init(SECRET_KEY_LENGTH);
      }
      SecretKey xDesKey = xKeyGen.generateKey();
      // generete salt + data
      String sDataToCript = RandomGenerator.getInstance().getSalt() + sData;
      // cript key with rsa
      Cipher xCipherKey = Cipher.getInstance(PUBLIC_KEY_CRYPT_ALGO);
      xCipherKey.init(Cipher.WRAP_MODE, xRSAKey);
      byte[] baCriptKey = xCipherKey.wrap(xDesKey);
      //cript salt + tekst
      Cipher xCipherData = Cipher.getInstance(SECRET_KEY_CRYPT_ALGO);
      IvParameterSpec xParamSpec = new IvParameterSpec(SALT);
      xCipherData.init(Cipher.ENCRYPT_MODE, xDesKey, xParamSpec);
      byte[] baCriptData = xCipherData.doFinal(sDataToCript.getBytes());
      // stream triple des key + cript tekst
      ByteArrayOutputStream xByteArrayOutput = new ByteArrayOutputStream();
      DataOutputStream xDataOutput = new DataOutputStream(xByteArrayOutput);
      // write length of key
      xDataOutput.writeInt(baCriptKey.length);
      // write key
      xDataOutput.write(baCriptKey, 0, baCriptKey.length);
      // write length of key
      xDataOutput.writeInt(baCriptData.length);
      // write cript data
      xDataOutput.write(baCriptData, 0, baCriptData.length);
      // close streams
      xDataOutput.close();
      xByteArrayOutput.close();
      // return byte array (length criptkey + criptkey + length cript data + cript data)
      return xByteArrayOutput.toByteArray();
    } catch (NoSuchAlgorithmException nsae) {
      /* (code die een aantal fouten uit de encryptie bibliotheek opvangt
       * en omzet naar KoA fouten weggelaten).
       */
    }
  }

  // Het ontcijferen van de stem:
  /**
   * decrypts the encryped byte aray with the private key and returns decrtypted
   * data as a String
   *
   * @param xRSAKey the private key used te decrypt the data
   * @param encryptedBytes the encryped bytearray
   * @return String the decryped data
   */
  public static String decrypt(PrivateKey xRSAKey, byte[] encryptedBytes) throws KOAException {
    try {
      // (debug code weggelaten)
      // create stream from encrypted bytes
      ByteArrayInputStream xByteArrayInput = new ByteArrayInputStream(encryptedBytes);
      DataInputStream xDataInput = new DataInputStream(xByteArrayInput);
      // decrypt key with rsa
      int iKeyLenght = xDataInput.readInt();
      byte[] baCriptKey = new byte[iKeyLenght];
      xDataInput.read(baCriptKey);
      Cipher xCipherKey = Cipher.getInstance(PUBLIC_KEY_CRYPT_ALGO);
      // (debug code weggelaten)
      xCipherKey.init(Cipher.UNWRAP_MODE, xRSAKey);
      SecretKey xDesKey = (SecretKey)xCipherKey.unwrap(baCriptKey, SECRET_KEY_GENERATOR_ALGO, Cipher.SECRET_KEY);
      // (debug code weggelaten)
      // decript salt + data
      Cipher xCipherData = Cipher.getInstance(SECRET_KEY_CRYPT_ALGO);
      IvParameterSpec xParamSpec = new IvParameterSpec(SALT);
      xCipherData.init(Cipher.DECRYPT_MODE, xDesKey, xParamSpec);
      // (debug code weggelaten)
      int iDataLenght = xDataInput.readInt();
      byte[] baCriptData = new byte[iDataLenght];
      xDataInput.read(baCriptData);
      byte[] baDecryptedData = xCipherData.doFinal(baCriptData);
      // (debug code weggelaten)
      // remove salt from decript tekst.
      return new String(baDecryptedData).substring(RandomGenerator.SALT_LENGTE);
    } catch (NoSuchAlgorithmException nsae) {
      /* (code voor afhandelen van foutmeldingen uit cryptobibliotheek
       * weggelaten)
       */
    }
  }

  /* Vingerafdrukken worden in de KoA applicatie gemaakt met de MessageDigest
   * klasse uit de Java Security API1. Het gebruikte algoritme is MD5.
   * Onderstaand de methode die gebruikt wordt om een vingerafdruk te
   * genereren. De methode maakt deel uit van de KOAEncryptionUtil klasse die
   * is besproken in § B.2. De methode is algemeen van opzet: bij de aanroep
   * wordt opgegeven om welke databasetabel het gaat en welke kolommen moeten
   * worden gebruikt. Voor de vercijferde stemmen is er slechts één kolom van
   * toepassing.
   */
  /**
   * Creates the fingerprint of the provided database table. Special method for tables containing blobs
   *
   * @param datasourceName The datasource to get the fingerprint for
   * @param schemaName The schema name to get the fingerprint for
   * @param tableName The table name to get the fingerprint for
   * @param columns The columns to use in the creation of the fingerprint
   * @param sortKey the key to sort the rows on
   *
   * @return byte [] containing the fingerprint
   *
   * @throws KOAException when something goes wrong during the creation of the fingerprint.
   *
   */
  public static byte[] getBLOBFingerPrint(String datasourceName, String schemaName, String tableName, String[] columns, String sortKey) throws KOAException {
    // Initialiseer de fingerprint:
    DBUtils db = new DBUtils(datasourceName);
    Connection conn = db.getConnection();
    Statement stmt = null;
    BLOBResultSet rSet = null;
    FingerPrint fPrint = new FingerPrint(encryptionKey);
    try {
      // Haal de gegevens op:
      stmt = conn.createStatement();
      String cols;
      if (columns.length > 0) {
        rSet = new BLOBResultSet(datasourceName, schemaName, tableName, sortKey, columns);
        // Werk de fingerprint bij voor alle rijen:
        while (rSet.next()) {
          // System.out.println("rst.next");
          for (int i=1; i <= columns.length; ++i) {
            byte[] tmp = null;
            /* rows can be fetched as a byte[]
             */
            tmp = rSet.getBytes(columns[i-1]);
            if (tmp != null) {
              fPrint.update(tmp);
            }
            else {
              // No value
            }
          }
        }
      }
      else {
      }
    } catch (Exception e) {
      // error handling
    } finally {
      rSet.close();
    }
    // Converteer naar een hexadecimale string:
    byte[] bDigest = null;
    bDigest = fPrint.getDigest();
    StringBuffer sb = new StringBuffer(2*bDigest.length);
    for (int i=0; i<bDigest.length; ++i) {
      int k = bDigest[i] & 0xFF;
      if(k<0x10) sb.append('0');
      sb.append(Integer.toHexString(k));
    }
    return sb.toString().getBytes();
  }
}
