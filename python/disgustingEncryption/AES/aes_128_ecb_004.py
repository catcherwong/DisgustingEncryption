from Crypto.Cipher import AES
from Crypto.Util.Padding import pad, unpad
import base64
import hashlib

class aes_128_ecb_004:

    def add_to_16(self, value):

        l = len(value)

        if l > 16:
            value = value[:15]
        elif l < 16:
            value =  value + "0" * (16 -l)
         
        return value
     
    def pkcs5padding(self, text):

        bs = AES.block_size 
        length = len(text)
        bytes_length = len(bytes(text, encoding='utf-8'))

        padding = bs - (bytes_length % bs)
        
        padding_text = chr(padding) * padding
        return text + padding_text


    def pkcs5unpadding(self, text):
        length = len(text)
        unpadding = ord(text[length-1])
        return text[0:length-unpadding]        


    def aes_encrypt(self, content, key):

        key_bytes = bytes(self.add_to_16(key), encoding='utf-8')
       
        aes = AES.new(key_bytes, AES.MODE_ECB)

        content_padding = self.pkcs5padding(content)
        encrypt_bytes = aes.encrypt(bytes(content_padding, encoding='utf-8'))

        result = encrypt_bytes.hex().upper()
        # result = str(base64.b64encode(encrypt_bytes), encoding='utf-8')
        return result

    def aes_decrypt(self, content, key):

        key_bytes = bytes(self.add_to_16(key), encoding='utf-8')           

        aes = AES.new(key_bytes, AES.MODE_ECB) 

        # encrypt_bytes = base64.b64decode(content)        
        encrypt_bytes = bytes.fromhex(content)
        decrypt_bytes = aes.decrypt(encrypt_bytes)  

        result = str(decrypt_bytes, encoding='utf-8')
        result = self.pkcs5unpadding(result)
        
        return result 

    def show(self):
        mystr1 = "123"
        mykey1 = "12345678"

        enc1 = self.aes_encrypt(mystr1, mykey1)
        # 9FB79E399D9D174358A20FFAB0DE06FB
        print("after encrypt")
        print(enc1)        

        dec1 = self.aes_decrypt(enc1, mykey1)
        print("after decrypt")
        print(dec1)        

        mystr2 = "你好abcd1234"
        mykey2 = "1234567812345678"

        
        enc2 = self.aes_encrypt(mystr2, mykey2)        
        # 52F86DBA5B6C4F778395BBEDC3E0B489
        print("after encrypt")
        print(enc2)        

        dec2 = self.aes_decrypt(enc2, mykey2)
        print("after decrypt")
        print(dec2)        


