package main

import (
 "crypto/cipher"
 "crypto/aes"    
 "crypto/sha256"
 "encoding/base64"
 "bytes"
 "fmt"
)


func main() {
	
	key1 := "12345678"
	str1 := "123"

	enc1 := AesEncrypt(str1, key1)
	// 3gVLeGnili1JBTYLHAk8pQ==
	fmt.Println("after encrypt")
	fmt.Println(enc1)

	dec1 := AESDecrypt(enc1, key1)
	fmt.Println("after decrypt")
	fmt.Println(dec1)

	key2 := "1234567812345678"
	str2 := "你好abcd1234"
	
	enc2 := AesEncrypt(str2, key2)
	// Qkz+MXCIESJZVgHJffouTQ==
	fmt.Println("after encrypt")
	fmt.Println(enc2)

	dec2 := AESDecrypt(enc2, key2)
	fmt.Println("after decrypt")
	fmt.Println(dec2)
}

func GetSha256(str string)[]byte{
	h := sha256.New()
    h.Write([]byte(str))
	bs := h.Sum(nil)
	return bs
}

func AesEncrypt(toEncrypt, key string)string{

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

func AESDecrypt(toDecrypt, key string)string{
	
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

func PKCS7Padding(origData []byte, blockSize int)[]byte{
	padding := blockSize-len(origData)%blockSize
	padtext := bytes.Repeat([]byte{byte(padding)},padding)	
	return append(origData,padtext...)
}

func PKCS7UnPadding(cipherText []byte)[]byte{	
	length := len(cipherText)
	unpadding := int(cipherText[length-1])
	return cipherText[:length-unpadding]
}