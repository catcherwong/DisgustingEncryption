namespace DisgustingEncryption.Tests
{
    using DisgustingEncryption;
    using Xunit;

    public class Aes_128_Ecb_001_Test
    {
        [Theory]
        [InlineData("12345678", "123", "n7eeOZ2dF0NYog/6sN4G+w==")]
        [InlineData("1234567812345678", "你好abcd1234", "UvhtultsT3eDlbvtw+C0iQ==")]
        public void Encrypt_Should_Succeed(string key, string str, string res)
        {
            var result = Aes_128_Ecb_001.AesEncrypt(str, key);

            Assert.Equal(res, result);
        }

        [Theory]
        [InlineData("12345678", "n7eeOZ2dF0NYog/6sN4G+w==", "123")]
        [InlineData("1234567812345678", "UvhtultsT3eDlbvtw+C0iQ==", "你好abcd1234")]
        public void Decrypt_Should_Succeed(string key, string str, string res)
        {
            var result = Aes_128_Ecb_001.AesDecrypt(str, key);

            Assert.Equal(res, result);
        }
    }
}
