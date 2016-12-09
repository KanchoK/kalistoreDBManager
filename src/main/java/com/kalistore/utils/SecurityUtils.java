package com.kalistore.utils;

import java.security.MessageDigest;

/**
 * Created by kanch on 5/27/2016.
 */
public class SecurityUtils {
    public static String getHashedPassword(String password) {
        try {
            MessageDigest mda = MessageDigest.getInstance("SHA-512");
            password = new String(mda.digest(password.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }
}
