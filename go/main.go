package main

import (
	mypck "github.com/catcherwong/DisgustingEncryption/go/disgustingEncryption"
)

func main() {
	
	cbc_001 := new(mypck.Aes_128_cbc_001)

	cbc_001.Show()
	
	
	cbc_002 := new(mypck.Aes_128_cbc_002)

	cbc_002.Show()
}