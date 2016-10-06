package cryptography; 
import java.security.NoSuchAlgorithmException; 
import java.security.InvalidKeyException; 
import javax.crypto.NoSuchPaddingException; 
import javax.crypto.BadPaddingException; 
import javax.crypto.IllegalBlockSizeException; 
import java.io.UnsupportedEncodingException; 
import java.io.IOException; 
import java.security.InvalidAlgorithmParameterException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncrypterDecrypter { 
    Cipher ecipher; 
    Cipher dcipher; 
    final static String strPassword = "password12345678";
    static SecretKeySpec key = new SecretKeySpec(strPassword.getBytes(), "AES");
    EncrypterDecrypter() { 
        try{ 
            AlgorithmParameterSpec paramSpec = 
                    new IvParameterSpec(strPassword.getBytes()); 
            ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec); 
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException e) {}
    }
	
    // encrypt() inputs a string and returns an encrypted version 
    // of that String. 
    public String encrypt(String str) { 
        try { 
            // Encode the string into bytes using utf-8 
            byte[] utf8 = str.getBytes("UTF8"); 
            // Encrypt 
            byte[] enc = ecipher.doFinal(utf8); 
            // Encode bytes to base64 to get a string 
            return new sun.misc.BASE64Encoder().encode(enc); 
            } catch (BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException e) { 
            }
        return null; 
    }

    // decrypt() inputs a string and returns an encrypted version 
    // of that String. 
    public String decrypt(String str) { 
        try { 
            // Decode base64 to get bytes 
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str); 
            // Decrypt 
            byte[] utf8 = dcipher.doFinal(dec);
            // Decode using utf-8
            return new String(utf8, "UTF8"); 
        } catch (BadPaddingException | IllegalBlockSizeException e) { 
        } catch (UnsupportedEncodingException e) { 
        } catch (IOException e) { 
        }
        return null; 
    }	
// The following method is used for encrypting and decrypting 
// String using symmetric Secret Key using “DES” algorithm 
    /*public static void main(String[] args) throws NoSuchAlgorithmException {         
        String stringToBeEncrypted = "String to be encrypted"; 
        EncrypterDecrypter encrypter = new EncrypterDecrypter();
        // Encrypt the string 
        String encryptedString = encrypter.encrypt(stringToBeEncrypted); 
        // Decrypt the string 
        String decrypterString = encrypter.decrypt(encryptedString); 
        // Display values  
        System.out.println("\tOriginal String : " + stringToBeEncrypted); 
        System.out.println("\tEncrypted String : " + encryptedString); 
        System.out.println("\tDcrypted String : " + decrypterString);       
    }*/	
}
