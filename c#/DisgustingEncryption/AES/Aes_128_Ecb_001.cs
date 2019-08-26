﻿namespace DisgustingEncryption
{
    using System;
    using System.Security.Cryptography;
    using System.Text;

    public class Aes_128_Ecb_001
    {
        public static void Show()
        {
            var key1 = "12345678";
            var str1 = "123";

            var enc1 = AesEncrypt(str1, key1);
            // n7eeOZ2dF0NYog/6sN4G+w==
            Console.WriteLine("after encrypt");
            Console.WriteLine(enc1);

            var dec1 = AesDecrypt(enc1, key1);
            Console.WriteLine("after decrypt");
            Console.WriteLine(dec1);


            var key2 = "1234567812345678";
            var str2 = "你好abcd1234";

            var enc2 = AesEncrypt(str2, key2);
            // UvhtultsT3eDlbvtw+C0iQ==
            Console.WriteLine("after encrypt");
            Console.WriteLine(enc2);

            var dec2 = AesDecrypt(enc2, key2);
            Console.WriteLine("after decrypt");
            Console.WriteLine(dec2);
        }


        public static string AesEncrypt(string toEncrypt, string key)
        {
            key = key.Length > 16 ? key.Substring(0, 15) : key.Length < 16 ? key.PadRight(16, '0') : key;

            byte[] keyArray = Encoding.UTF8.GetBytes(key);
            byte[] toEncryptArray = Encoding.UTF8.GetBytes(toEncrypt);

            RijndaelManaged rDel = new RijndaelManaged
            {
                Key = keyArray,
                Mode = CipherMode.ECB,
                Padding = PaddingMode.PKCS7,
                BlockSize = 128
            };

            ICryptoTransform cTransform = rDel.CreateEncryptor();

            byte[] resultArray = cTransform.TransformFinalBlock(toEncryptArray, 0, toEncryptArray.Length);

            return Convert.ToBase64String(resultArray, 0, resultArray.Length);
        }
  
        public static string AesDecrypt(string toDecrypt, string key)
        {
            key = key.Length > 16 ? key.Substring(0, 15) : key.Length < 16 ? key.PadRight(16, '0') : key;
            byte[] keyArray = Encoding.UTF8.GetBytes(key);            
            byte[] toDecryptArray = Convert.FromBase64String(toDecrypt);

            RijndaelManaged rDel = new RijndaelManaged
            {
                Key = keyArray,
                Mode = CipherMode.ECB,
                Padding = PaddingMode.PKCS7,
                BlockSize = 128
            };

            ICryptoTransform cTransform = rDel.CreateDecryptor();

            byte[] resultArray = cTransform.TransformFinalBlock(toDecryptArray, 0, toDecryptArray.Length);

            return Encoding.UTF8.GetString(resultArray);
        }

    }
}
