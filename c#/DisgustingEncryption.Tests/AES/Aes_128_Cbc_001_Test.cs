namespace DisgustingEncryption.Tests
{
    using DisgustingEncryption;
    using Xunit;

    public class Aes_128_Cbc_001_Test
    {
        [Theory]
        [InlineData("12345678", "123", "3gVLeGnili1JBTYLHAk8pQ==")]
        [InlineData("1234567812345678", "你好abcd1234", "Qkz+MXCIESJZVgHJffouTQ==")]
        public void Encrypt_Should_Succeed(string key, string str, string res)
        {
            var result = Aes_128_Cbc_001.AesEncrypt(str, key);

            Assert.Equal(res, result);
        }

        [Theory]
        [InlineData("12345678", "3gVLeGnili1JBTYLHAk8pQ==", "123")]
        [InlineData("1234567812345678", "Qkz+MXCIESJZVgHJffouTQ==", "你好abcd1234")]
        public void Decrypt_Should_Succeed(string key, string str, string res)
        {
            var result = Aes_128_Cbc_001.AesDecrypt(str, key);

            Assert.Equal(res, result);
        }
    }
}
