from Crypto.Cipher import AES
import base64
import hashlib

class aes_128_cbc_001:


    def get_sha256(self, data):
        sha256 = hashlib.sha256()
        sha256.update(data.encode("utf-8"))    
        res = sha256.digest()        
        return res

    def pkcs7padding(self, text):

        bs = AES.block_size 
        length = len(text)
        bytes_length = len(bytes(text, encoding='utf-8'))

        padding_size = length if(bytes_length == length) else bytes_length
        padding = bs - padding_size % bs
        
        padding_text = chr(padding) * padding
        return text + padding_text


    def pkcs7unpadding(self, text):
        length = len(text)
        unpadding = ord(text[length-1])
        return text[0:length-unpadding]        

    def aes_encrypt(self, content, key):

        key_bytes = self.get_sha256(key)
        iv = "\0".encode("utf-8") * 16

        aes = AES.new(key_bytes, AES.MODE_CBC, iv)

        content_padding = self.pkcs7padding(content)

        encrypt_bytes = aes.encrypt(bytes(content_padding, encoding='utf-8'))

        result = str(base64.b64encode(encrypt_bytes), encoding='utf-8')
        return result

    def aes_decrypt(self, content, key):

        key_bytes = self.get_sha256(key)
        iv = "\0".encode("utf-8") * 16

        aes = AES.new(key_bytes, AES.MODE_CBC, iv)    

        encrypt_bytes = base64.b64decode(content)        
        decrypt_bytes = aes.decrypt(encrypt_bytes)  

        result = str(decrypt_bytes, encoding='utf-8')
        result = self.pkcs7unpadding(result)
        return result 

    def show(self):
        mystr1 = "123"
        mykey1 = "12345678"

        enc1 = self.aes_encrypt(mystr1, mykey1)
        # 3gVLeGnili1JBTYLHAk8pQ==
        print("after encrypt")
        print(enc1)        

        dec1 = self.aes_decrypt(enc1, mykey1)
        print("after decrypt")
        print(dec1)        

        mystr2 = "你好abcd1234"
        mykey2 = "1234567812345678"

        
        enc2 = self.aes_encrypt(mystr2, mykey2)        
        # Qkz+MXCIESJZVgHJffouTQ==
        print("after encrypt")
        print(enc2)        

        dec2 = self.aes_decrypt(enc2, mykey2)
        print("after decrypt")
        print(dec2)        


