namespace DisgustingEncryption.Tests
{
    using DisgustingEncryption;
    using Xunit;

    public class Aes_128_Ecb_002_Test
    {
        [Theory]
        [InlineData("12345678", "123", "9FB79E399D9D174358A20FFAB0DE06FB")]
        [InlineData("1234567812345678", "你好abcd1234", "52F86DBA5B6C4F778395BBEDC3E0B489")]
        public void Encrypt_Should_Succeed(string key, string str, string res)
        {
            var result = Aes_128_Ecb_002.AesEncrypt(str, key);

            Assert.Equal(res, result);
        }

        [Theory]
        [InlineData("12345678", "9FB79E399D9D174358A20FFAB0DE06FB", "123")]
        [InlineData("1234567812345678", "52F86DBA5B6C4F778395BBEDC3E0B489", "你好abcd1234")]
        public void Decrypt_Should_Succeed(string key, string str, string res)
        {
            var result = Aes_128_Ecb_002.AesDecrypt(str, key);

            Assert.Equal(res, result);
        }
    }
}
