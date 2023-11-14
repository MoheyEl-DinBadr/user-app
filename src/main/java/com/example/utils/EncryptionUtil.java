package com.example.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptionUtil {

    public static String encryptText(String text, String salt) {
        var saltedText = text + salt;
        return DigestUtils.sha1Hex(saltedText);
    }

    private EncryptionUtil() {
    }
}
