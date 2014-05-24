/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.libs.libseext;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author luffy
 */
public class BytesUtils {

    public static String base64(byte[] src) {
        return java.util.Base64.getEncoder().encodeToString(src);
    }

    public static byte[] base64(String src) {
        return java.util.Base64.getDecoder().decode(src);
    }

    public static byte[] sha1(byte[] data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("sha1");
            messageDigest.update(data);
            return (messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String byes2Str(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            String t = Integer.toUnsignedString(b, 16);
            if (t.length() >= 8) {
                sb.append(t.substring(6));
            } else if (t.length() < 2) {
                sb.append('0');
                sb.append(t);
            } else {
                sb.append(t);
            }
        }
        return sb.toString();
    }
}
