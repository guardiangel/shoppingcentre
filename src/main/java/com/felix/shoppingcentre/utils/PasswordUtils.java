package com.felix.shoppingcentre.utils;

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
    public static String encode(String rawPass, String salt) {
        return new PasswordEncoder(salt).encodePassword(rawPass);
    }

    public static String getSalt() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
    }
}
