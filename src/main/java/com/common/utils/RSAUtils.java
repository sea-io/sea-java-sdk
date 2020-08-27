
public class RSAUtils {

    public static String PRIVATE_KEY = "MIIEv***XFu4=";

    public static String PUBLIC_KEY = "MIIB***DAQAB";

   
    public static String CHARSET = "utf-8";


    public static final String SIGNATURE_INSTANCE = "SHA1WithRSA";

    /**
     * 
     * @param keyLength
     * @return
     * @throws Exception
     */
    public static KeyPair getKeyPair(int keyLength) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keyLength);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     *
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] publicKeyBytes = new byte[0];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            publicKeyBytes = Base64.getDecoder().decode(publicKey.getBytes());
        }else {
            publicKeyBytes = new BASE64Decoder().decodeBuffer(publicKey);
        }
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    /**
     *
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {


        byte[] privateKeyBytes = new byte[0];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            privateKeyBytes = Base64.getDecoder().decode(privateKey.getBytes());
        } else {
            privateKeyBytes = new BASE64Decoder().decodeBuffer(privateKey);
        }
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     *
     * @param content
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    public static byte[] encryptByPublicKey(byte[] content) throws Exception {
        return encryptByPublicKey(content, getPublicKey(PUBLIC_KEY));
    }

    public static String encryptByPublicKey(String content, String publicKey) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new String(Base64.getEncoder().encode(encryptByPublicKey(content.getBytes(CHARSET), getPublicKey(publicKey))));
        }
        return new BASE64Encoder().encode(encryptByPublicKey(content.getBytes(CHARSET), getPublicKey(publicKey)));
    }

    public static String encryptByPublicKey(String content) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new String(Base64.getEncoder().encode(encryptByPublicKey(content.getBytes(CHARSET))));
        }
        return new BASE64Encoder().encode(encryptByPublicKey(content.getBytes(CHARSET)));
    }

    /**
     *
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    public static byte[] decryptByPrivateKey(byte[] content) throws Exception {
        return decryptByPrivateKey(content, getPrivateKey(PRIVATE_KEY));
    }

    public static String decryptByPrivateKey(String content, String privateKey) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new String(decryptByPrivateKey(Base64.getDecoder().decode(content), getPrivateKey(privateKey)), CHARSET);
        }
        return new String(decryptByPrivateKey(new BASE64Decoder().decodeBuffer(content), getPrivateKey(privateKey)), CHARSET);
    }

    public static String decryptByPrivateKey(String content) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new String(decryptByPrivateKey(Base64.getDecoder().decode(content)), CHARSET);
        }
        return new String(decryptByPrivateKey(new BASE64Decoder().decodeBuffer(content)), CHARSET);
    }

    /**
     *
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    public static byte[] encryptByPrivateKey(byte[] content) throws Exception {
        return encryptByPrivateKey(content, getPrivateKey(PRIVATE_KEY));
    }

    public static String encryptByPrivateKey(String content, String privateKey) throws Exception {
        return new String(encryptByPrivateKey(content.getBytes(CHARSET), getPrivateKey(privateKey)), CHARSET);
    }

    public static String encryptByPrivateKey(String content) throws Exception {
        return new String(encryptByPrivateKey(content.getBytes(CHARSET)), CHARSET);
    }

    /**
     *
     * @param content
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] decrypByPublicKey(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    public static byte[] decrypByPublicKey(byte[] content) throws Exception {
        return decrypByPublicKey(content, getPublicKey(PUBLIC_KEY));
    }

    public static String decrypByPublicKey(String content, String publicKey) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new String(decrypByPublicKey(Base64.getDecoder().decode(content), getPublicKey(publicKey)), CHARSET);
        }

        return new String(decrypByPublicKey(new BASE64Decoder().decodeBuffer(content), getPublicKey(publicKey)), CHARSET);
    }

    public static String decrypByPublicKey(String content) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new String(decrypByPublicKey(Base64.getDecoder().decode(content)), CHARSET);
        }
        return new String(decrypByPublicKey(new BASE64Decoder().decodeBuffer(content)), CHARSET);
    }

    /**
     *
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] sign(byte[] content, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_INSTANCE);
        signature.initSign(privateKey);
        signature.update(content);
        return signature.sign();
    }

    public static byte[] sign(byte[] content) throws Exception {
        return sign(content, getPrivateKey(PRIVATE_KEY));
    }

    public static String sign(String content, String privateKey) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new String(Base64.getEncoder().encode(sign(content.getBytes(CHARSET), getPrivateKey(privateKey))), CHARSET);
        }
        return new BASE64Encoder().encode(sign(content.getBytes(CHARSET), getPrivateKey(privateKey)));
    }

    public static String sign(String content) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new String(Base64.getEncoder().encode(sign(content.getBytes(CHARSET))), CHARSET);
        }
        return new BASE64Encoder().encode(sign(content.getBytes(CHARSET)));
    }

    /**
     * @param content
     * @param sign
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] content, byte[] sign, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_INSTANCE);
        signature.initVerify(publicKey);
        signature.update(content);
        return signature.verify(sign);
    }

    public static boolean verify(byte[] content, byte[] sign) throws Exception {
        return verify(content, sign, getPublicKey(PUBLIC_KEY));
    }

    public static boolean verify(String content, String sign, String publicKey) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return verify(content.getBytes(CHARSET), Base64.getDecoder().decode(sign), getPublicKey(publicKey));
        }
        return verify(content.getBytes(CHARSET), new BASE64Decoder().decodeBuffer(sign), getPublicKey(publicKey));
    }

    public static boolean verify(String content, String sign) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return verify(content.getBytes(CHARSET), Base64.getDecoder().decode(sign), getPublicKey(PUBLIC_KEY));
        }
        return verify(content.getBytes(CHARSET), new BASE64Decoder().decodeBuffer(sign), getPublicKey(PUBLIC_KEY));
    }

    public static String decrypt(String content, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte [] b = cipher.doFinal(content.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(b);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
