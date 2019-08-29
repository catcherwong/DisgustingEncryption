package main.java.disgustingEncryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64;

public class Aes_128_Cbc_001 {

    private static final String ALGORITHMSTR = "AES/CBC/PKCS5Padding";
    
    public static String aesEncrypt(String content, String encryptKey) throws Exception {

        byte[] keyArray = SHA256Util.getBytes(encryptKey);
        byte[] toEncryptArray = content.getBytes("utf-8");
        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };

        SecretKeySpec keySpec = new SecretKeySpec(keyArray, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);        
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
        byte[] encData = cipher.doFinal(toEncryptArray);
        return Base64.getEncoder().encodeToString(encData);        
    }

    public static String aesDecrypt(String content, String decryptKey) throws Exception {

        byte[] keyArray = SHA256Util.getBytes(decryptKey);
        byte[] toDecryptArray = Base64.getDecoder().decode(content);
        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };

        SecretKeySpec keySpec = new SecretKeySpec(keyArray, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);        
        cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
        byte[] resultArray = cipher.doFinal(toDecryptArray);
        return new String(resultArray);
    }

    public static void show(){
        try{
            String key1 = "12345678";
            String str1 = "123";
    
            String enc1 = aesEncrypt(str1, key1);
            // 3gVLeGnili1JBTYLHAk8pQ==
            System.out.println("after encrypt");
            System.out.println(enc1);
    
            String dec1 = aesDecrypt(enc1, key1);
            System.out.println("after decrypt");
            System.out.println(dec1);
    
    
            String key2 = "1234567812345678";
            String str2 = "你好abcd1234";
    
            String enc2 = aesEncrypt(str2, key2);
            // Qkz+MXCIESJZVgHJffouTQ==
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