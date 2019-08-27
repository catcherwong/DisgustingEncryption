# -*- coding: utf-8 -*-

import pytest

from AES import aes_128_ecb_002

class TestAes_128_ECB_002:

    @pytest.mark.parametrize(
        'key, content, expected', (
            ('12345678', '123', '9FB79E399D9D174358A20FFAB0DE06FB'),            
            ('1234567812345678', '你好abcd1234', '52F86DBA5B6C4F778395BBEDC3E0B489'),            
        ))
    def test_valid(self, key, content, expected):        
        assert aes_128_ecb_002.aes_128_ecb_002().aes_encrypt(content, key) == expected
              