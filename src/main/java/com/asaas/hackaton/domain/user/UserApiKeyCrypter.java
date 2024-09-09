package com.asaas.hackaton.domain.user;

import java.util.Base64;

public class UserApiKeyCrypter {

    public static String encrypt(String key) {
        return Base64.getEncoder().encodeToString(key.getBytes());
    }

    public String decrypt(String encryptedKey) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedKey);
        return new String(decodedBytes);
    }
}
