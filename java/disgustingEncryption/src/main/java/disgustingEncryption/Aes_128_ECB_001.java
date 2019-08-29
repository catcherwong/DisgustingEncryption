package main.java.disgustingEncryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64;

public class Aes_128_ECB_001 {
    
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
    // private static final String ALGORITHMSTRV2 = "AES/ECB/NoPadding";
    
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        
        // v1
        encryptKey = StringPaddingUtils.padRight(encryptKey, 16, '0');     
        byte[] keyArray = encryptKey.getBytes("utf-8");
        byte[] toEncryptArray = content.getBytes("utf-8");

        SecretKeySpec keySpec = new SecretKeySpec(keyArray, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);        
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encData = cipher.doFinal(toEncryptArray);
        return Base64.getEncoder().encodeToString(encData);        

        //v2

        // encryptKey = StringPaddingUtils.padRight(encryptKey, 16, '0');        
        // content = StringPaddingUtils.pkcs7padding(content);
        // byte[] keyArray = encryptKey.getBytes("utf-8");

        // Cipher cipher = Cipher.getInstance(ALGORITHMSTRV2);
        // int blockSize = cipher.getBlockSize();
        // byte[] dataBytes = content.getBytes("utf-8");
        // int plaintextLength = dataBytes.length;
        // if (plaintextLength % blockSize != 0) {
        //     plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
        // }

        // byte[] plaintext = new byte[plaintextLength];
        // System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
        // SecretKeySpec keyspec = new SecretKeySpec(keyArray,  "AES");
        // cipher.init(Cipher.ENCRYPT_MODE, keyspec);
        // byte[] encrypted = cipher.doFinal(plaintext);        
        // return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String aesDecrypt(String content, String decryptKey) throws Exception {

        // v1
        decryptKey = StringPaddingUtils.padRight(decryptKey, 16, '0');
   
        byte[] keyArray = decryptKey.getBytes("utf-8");
        byte[] toDecryptArray = Base64.getDecoder().decode(content);

        SecretKeySpec keySpec = new SecretKeySpec(keyArray, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);        
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] encData = cipher.doFinal(toDecryptArray);
        return new String(encData);        

        //v2
        
        // decryptKey = StringPaddingUtils.padRight(decryptKey, 16, '0');
   
        // byte[] keyArray = decryptKey.getBytes("utf-8");
        // byte[] toDecryptArray = Base64.getDecoder().decode(content);

        // Cipher cipher = Cipher.getInstance(ALGORITHMSTRV2);
        // SecretKeySpec keyspec = new SecretKeySpec(keyArray, "AES");
        // cipher.init(Cipher.DECRYPT_MODE, keyspec);
        // byte[] original = cipher.doFinal(toDecryptArray);
        // String originalString = new String(original);
        // return StringPaddingUtils.pkcs7unpadding(originalString);
    }         
    
    public static void show(){
        try{
            String key1 = "12345678";
            String str1 = "123";
    
            String enc1 = aesEncrypt(str1, key1);
            // n7eeOZ2dF0NYog/6sN4G+w==
            System.out.println("after encrypt");
            System.out.println(enc1);
    
            String dec1 = aesDecrypt(enc1, key1);
            System.out.println("after decrypt");
            System.out.println(dec1);
    
    
            String key2 = "1234567812345678";
            String str2 = "你好abcd1234";
    
            String enc2 = aesEncrypt(str2, key2);
            // UvhtultsT3eDlbvtw+C0iQ==
            System.out.println("after encrypt");
            System.out.println(enc2);
    
            String dec2 = aesDecrypt(enc2, key2);
            System.out.println("after decrypt");
            System.out.println(dec2);
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
}