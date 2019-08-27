package mypck

import (
 "bytes" 
 "crypto/sha256"
 "strings"
)

func GetSha256(str string)[]byte{
	h := sha256.New()
    h.Write([]byte(str))
	bs := h.Sum(nil)
	return bs
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

func AddTo16(str string)string{
	l := len(str)

	if l > 16 {
		str = str[:15]
	} else if l < 16 {

		str =  str + strings.Repeat("0", 16 -l)
	}
	return str
}

