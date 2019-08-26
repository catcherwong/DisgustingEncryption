# -*- coding: utf-8 -*-

import pytest

from AES import aes_128_cbc_001

class TestAes_128_CBC_001:

    @pytest.mark.parametrize(
        'key, content, expected', (
            ('12345678', '123', '3gVLeGnili1JBTYLHAk8pQ=='),            
            ('1234567812345678', '你好abcd1234', 'Qkz+MXCIESJZVgHJffouTQ=='),            
        ))
    def test_valid(self, key, content, expected):        
        assert aes_128_cbc_001.aes_128_cbc_001().aes_encrypt(content, key) == expected
