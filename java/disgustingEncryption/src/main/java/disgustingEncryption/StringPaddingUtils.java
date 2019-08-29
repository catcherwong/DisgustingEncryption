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
import java.lang.Byte;

public class StringPaddingUtils {


    public static String padRight(String src, int len, char ch) {
        int diff = len - src.length();
        if (diff <= 0) {
            return src;
        }

        char[] charr = new char[len];
        System.arraycopy(src.toCharArray(), 0, charr, 0, src.length());
        for (int i = src.length(); i < len; i++) {
            charr[i] = ch;
        }
        return new String(charr);
    }

    public static String padLeft(String src, int len, char ch) {
        int diff = len - src.length();
        if (diff <= 0) {
            return src;
        }

        char[] charr = new char[len];
        System.arraycopy(src.toCharArray(), 0, charr, diff, src.length());
        for (int i = 0; i < diff; i++) {
            charr[i] = ch;
        }
        return new String(charr);
    }

    public static String pkcs7padding(String data) {

        String padding_text = "";
        try {
            int bs = 16;

            int len = data.length();
    
            byte[] bytes =  data.getBytes("utf-8");
    
            int bytes_len = bytes.length;
    
            int paddingSize = bytes_len == len ? len : bytes_len;
    
            int padding = bs - paddingSize % bs;
          
            for (int i = 0; i < padding; i++) {
                padding_text += (char)padding;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return data+padding_text;
    }

    public static String pkcs7unpadding(String data) {
        int len = data.length();
        
        int unpadding = (int)data.charAt(len-1);

        return data.substring(0, len - unpadding);
    }
}