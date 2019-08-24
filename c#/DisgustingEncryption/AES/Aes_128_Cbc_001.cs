namespace DisgustingEncryption
{
    using System;
    using System.Security.Cryptography;
    using System.Text;

    public class Aes_128_Cbc_001
    {
        public static void Show()
        {
            var key1 = "12345678";
            var str1 = "123";

            var enc1 = AesEncrypt(str1, key1);
            // 3gVLeGnili1JBTYLHAk8pQ==
            Console.WriteLine("after encrypt");
            Console.WriteLine(enc1);

            var dec1 = AesDecrypt(enc1, key1);
            Console.WriteLine("after decrypt");
            Console.WriteLine(dec1);


            var key2 = "1234567812345678";
            var str2 = "你好abcd1234";

            var enc2 = AesEncrypt(str2, key2);
            // Qkz+MXCIESJZVgHJffouTQ==
            Console.WriteLine("after encrypt");
            Console.WriteLine(enc2);

            var dec2 = AesDecrypt(enc2, key2);
            Console.WriteLine("after decrypt");
            Console.WriteLine(dec2);
        }


        public static string AesEncrypt(string toEncrypt, string key)
        {
            byte[] keyArray = SHA256(key);
            byte[] toEncryptArray = Encoding.UTF8.GetBytes(toEncrypt);
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };

            RijndaelManaged rDel = new RijndaelManaged
            {
                Key = keyArray,
                IV = iv,
                Mode = CipherMode.CBC,
                Padding = PaddingMode.PKCS7,
                BlockSize = 128
            };

            ICryptoTransform cTransform = rDel.CreateEncryptor();

            byte[] resultArray = cTransform.TransformFinalBlock(toEncryptArray, 0, toEncryptArray.Length);

            return Convert.ToBase64String(resultArray, 0, resultArray.Length);
        }

        private static byte[] SHA256(string str)
        {
            byte[] SHA256Data = Encoding.UTF8.GetBytes(str);
            SHA256Managed Sha256 = new SHA256Managed();
            byte[] by = Sha256.ComputeHash(SHA256Data);
            return by;
        }

        public static string AesDecrypt(string toDecrypt, string key)
        {
            byte[] keyArray = SHA256(key);
            
            byte[] toDecryptArray = Convert.FromBase64String(toDecrypt); ;
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };

            RijndaelManaged rDel = new RijndaelManaged
            {
                Key = keyArray,
                IV = iv,
                Mode = CipherMode.CBC,
                Padding = PaddingMode.PKCS7,
                BlockSize = 128
            };

            ICryptoTransform cTransform = rDel.CreateDecryptor();

            byte[] resultArray = cTransform.TransformFinalBlock(toDecryptArray, 0, toDecryptArray.Length);

            return Encoding.UTF8.GetString(resultArray);
        }

    }
}
