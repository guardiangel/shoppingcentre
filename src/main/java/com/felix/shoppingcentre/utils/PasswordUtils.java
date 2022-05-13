package com.felix.shoppingcentre.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * utility class for password
 */
public final class PasswordUtils {

    /**
     * if raw pass matches encpass
     *
     * @param salt
     * @param rawPass
     * @param encPass
     * @return
     */
    public static boolean matches(String salt, String rawPass, String encPass) {
        return new PasswordEncoder(salt).matches(rawPass, encPass);
    }

    /**
     * encrypt password
     *
     * @param rawPass
     * @param salt
     * @return
     */
    public static String encode(String rawPass, String ...salt) {
        return new PasswordEncoder(salt[0]).encodePassword(rawPass);
    }

    public static String getSalt() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
    }

    public static void main(String[] args) {
        String rawPassword = "aaaa";
        String salt = "f10150b2da944411b415";
        String password = new PasswordEncoder(salt).encodePassword(rawPassword);
        System.err.println(password);

        List<String> avatarListType
                = new ArrayList<String>(Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/bmp", "image/gif"));
        StringBuilder stringBuilder = new StringBuilder("");
        avatarListType.forEach((value)->{
            stringBuilder.append(value);
        });

    }
}
