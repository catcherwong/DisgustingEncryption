package main.java.disgustingEncryption;

import java.math.BigInteger;

public class HexUtils {

    public static String bytes2Hex(byte[] bytes) { 
        if (bytes == null || bytes.length == 0) { 
            return null; 
        } 

        return new BigInteger(1, bytes).toString(16).toUpperCase();
    } 
    
    public static byte[] hex2Bytes(String hex) { 
        if (hex == null || hex.length() == 0) { 
            return null; 
        }
        
        char[] hexChars = hex.toCharArray();
        byte[] bytes = new byte[hexChars.length / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt("" + hexChars[i * 2] + hexChars[i * 2 + 1], 16);
        }
        
        return bytes;
    }
}