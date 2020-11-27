
import java.security.Key;
import java.security.SecureRandom;
 
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
 
 
public abstract class DESCoder extends Coder {
   
    public static final String ALGORITHM = "DES";
 
    private static Key toKey(byte[] key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(dks);
 
        // SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
 
        return secretKey;
    }
 
    public static byte[] decrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));
 
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
 
        return cipher.doFinal(data);
    }
 

    public static byte[] encrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
 
        return cipher.doFinal(data);
    }

    public static String initKey() throws Exception {
        return initKey(null);
    }
 
 
    public static String initKey(String seed) throws Exception {
        SecureRandom secureRandom = null;
 
        if (seed != null) {
            secureRandom = new SecureRandom(decryptBASE64(seed));
        } else {
            secureRandom = new SecureRandom();
        }
 
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
        kg.init(secureRandom);
 
        SecretKey secretKey = kg.generateKey();
 
        return encryptBASE64(secretKey.getEncoded());
    }
 
    public static String commonString() {
        return "common";
    }
 
     public static void main(String[] args) {
        String re1 = "abc";
        System.out.println("abc".matches(re1));
        System.out.println("Abc".matches(re1));
        System.out.println("abcd".matches(re1));

        String re2 = "a\\&c";
        System.out.println("a&c".matches(re2));
        System.out.println("a-c".matches(re2));
        System.out.println("a&&c".matches(re2));
        System.out.println("final");
    }
}
