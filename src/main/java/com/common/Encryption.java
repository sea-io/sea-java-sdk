
public abstract class Coder {
    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";
 
    /** 
     * <pre>
     * HmacMD5 
     * HmacSHA1 
     * HmacSHA256 
     * HmacSHA384 
     * HmacSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacMD5";
 

    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }
 

    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
 
 
    public static byte[] encryptMD5(byte[] data) throws Exception {
 
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
 
        return md5.digest();
 
    }
 
 
    public static byte[] encryptSHA(byte[] data) throws Exception {
 
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);
 
        return sha.digest();
 
    }
 

    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
 
        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }
 
 
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
 
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
 
        return mac.doFinal(data);
 
    }
}