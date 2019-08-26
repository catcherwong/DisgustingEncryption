# -*- coding: utf-8 -*-

import pytest

from AES import aes_128_ecb_001

class TestAes_128_ECB_001:

    @pytest.mark.parametrize(
        'key, content, expected', (
            ('12345678', '123', 'n7eeOZ2dF0NYog/6sN4G+w=='),            
            ('1234567812345678', '你好abcd1234', 'UvhtultsT3eDlbvtw+C0iQ=='),            
        ))
    def test_valid(self, key, content, expected):        
        assert aes_128_ecb_001.aes_128_ecb_001().aes_encrypt(content, key) == expected
              