# -*- coding: utf-8 -*-

import pytest

from AES import aes_128_cbc_002

class TestAes_128_CBC_001:

    @pytest.mark.parametrize(
        'key, content, expected', (
            ('12345678', '123', 'DE054B7869E2962D4905360B1C093CA5'),            
            ('1234567812345678', '你好abcd1234', '424CFE3170881122595601C97DFA2E4D'),            
        ))
    def test_valid(self, key, content, expected):        
        assert aes_128_cbc_002.aes_128_cbc_002().aes_encrypt(content, key) == expected
