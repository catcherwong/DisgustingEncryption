namespace DisgustingEncryption.Tests
{
    using DisgustingEncryption;
    using Xunit;

    public class Aes_128_Cbc_002_Test
    {
        [Theory]
        [InlineData("12345678", "123", "DE054B7869E2962D4905360B1C093CA5")]
        [InlineData("1234567812345678", "你好abcd1234", "424CFE3170881122595601C97DFA2E4D")]
        public void Encrypt_Should_Succeed(string key, string str, string res)
        {
            var result = Aes_128_Cbc_002.AesEncrypt(str, key);

            Assert.Equal(res, result);
        }

        [Theory]
        [InlineData("12345678", "DE054B7869E2962D4905360B1C093CA5", "123")]
        [InlineData("1234567812345678", "424CFE3170881122595601C97DFA2E4D", "你好abcd1234")]
        public void Decrypt_Should_Succeed(string key, string str, string res)
        {
            var result = Aes_128_Cbc_002.AesDecrypt(str, key);

            Assert.Equal(res, result);
        }
    }
}
