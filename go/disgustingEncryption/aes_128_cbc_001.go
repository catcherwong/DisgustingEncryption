package mypck

import (
 "crypto/cipher"
 "crypto/aes"
 "encoding/base64"
 "fmt"
)

type Aes_128_cbc_001 struct {

}

func (a *Aes_128_cbc_001) Show() {
	
	key1 := "12345678"
	str1 := "123"

	enc1 := a.AesEncrypt(str1, key1)
	// 3gVLeGnili1JBTYLHAk8pQ==
	fmt.Println("after encrypt")
	fmt.Println(enc1)

	dec1 := a.AESDecrypt(enc1, key1)
	fmt.Println("after decrypt")
	fmt.Println(dec1)

	key2 := "1234567812345678"
	str2 := "你好abcd1234"
	
	enc2 := a.AesEncrypt(str2, key2)
	// Qkz+MXCIESJZVgHJffouTQ==
	fmt.Println("after encrypt")
	fmt.Println(enc2)

	dec2 := a.AESDecrypt(enc2, key2)
	fmt.Println("after decrypt")
	fmt.Println(dec2)
}



func (a *Aes_128_cbc_001) AesEncrypt(toEncrypt, key string)string{

	keyArray := GetSha256(key)
	toEncryptArray := []byte(toEncrypt)
	iv := []byte{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

	block, _ := aes.NewCipher(keyArray)
	toEncryptArray = PKCS7Padding(toEncryptArray, block.BlockSize())

	blockMode := cipher.NewCBCEncrypter(block, iv)
		
	resultArray := make([]byte,len(toEncryptArray))
		
	blockMode.CryptBlocks(resultArray, toEncryptArray)
	
	return base64.StdEncoding.EncodeToString(resultArray)
}

func (a *Aes_128_cbc_001) AESDecrypt(toDecrypt, key string)string{
	
	keyArray := GetSha256(key)
	toDecryptArray, _ := base64.StdEncoding.DecodeString(toDecrypt)
	iv:=[]byte{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

	block,_ := aes.NewCipher(keyArray)

	blockMode := cipher.NewCBCDecrypter(block, iv)

	resultArray := make([]byte,len(toDecryptArray))

	blockMode.CryptBlocks(resultArray, toDecryptArray)

	resultArray = PKCS7UnPadding(resultArray)

	return string(resultArray)
}