package com.felix.shoppingcentre.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * password encoder
 */
@Slf4j
public final class PasswordEncoder {

    private final static String[] hexDigits = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"
    };

    private static final String MD5 = "MD5";
    private static final String SHA = "SHA";

    private final String salt;
    private final String algorithm;

    public PasswordEncoder(String salt, String algorithm) {
        this.salt = salt;
        this.algorithm = algorithm;
    }

    public PasswordEncoder(String salt) {
        this(salt, MD5);
    }

    /**
     * encrypt password
     *
     * @param rawPassword original password
     * @return encrypted password
     */
    public String encodePassword(String rawPassword) {
        String result = "";
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            result = byteArrayToHexString(digest.digest(mergePasswordAndSalt(rawPassword)
                    .getBytes(ConstantUtils.UTF_8)));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.info("PasswordEncoder.encodePassword exception {}", e.getMessage());
        }
        return result;
    }

    /**
     * raw pass matches encrypted pass
     *
     * @param encPass encrypted password
     * @param rawPass raw password
     * @return true or false
     */
    public boolean matches(String encPass, String rawPass) {
        String pass1 = "" + encPass;
        String pass2 = encodePassword(rawPass);
        return pass1.equals(pass2);
    }

    /**
     * Convert byte array to hexadecimal string
     *
     * @param digest byte array
     * @return hexadecimal string
     */
    private String byteArrayToHexString(byte[] digest) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : digest) {
            stringBuilder.append(byteToHexString(b));
        }
        return stringBuilder.toString();
    }

    /**
     * convert byte to hexadecimal
     *
     * @param b byte
     * @return hexadecimal string
     */
    private String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * combine password and salt
     *
     * @param rawPassword raw password
     * @return combined string
     */
    private String mergePasswordAndSalt(String rawPassword) {
        String mergePassword = "";
        if (salt == null || "".equals(salt)) {
            mergePassword = rawPassword;
        } else {
            mergePassword = rawPassword + "{" + salt.toString() + "}";
        }
        return mergePassword;
    }
}
