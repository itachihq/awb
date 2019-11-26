//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.awb.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Coder {
    public static final String KEY_SHA = "SHA1";
    public static final String KEY_MD5 = "MD5";
    private static Base64 base64 = new Base64();
    public static final String KEY_MAC = "HmacMD5";

    public Coder() {
    }

    public static byte[] decryptBASE64(String key) throws Exception {
        return base64.decode(key);
    }

    public static String encryptBASE64(byte[] key) throws Exception {
        return base64.encodeAsString(key);
    }

    public static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.reset();
        md5.update(data);
        return md5.digest();
    }

    public static byte[] encryptSHA(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA1");
        sha.reset();
        sha.update(data);
        return sha.digest();
    }

    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), "HmacMD5");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }

    public static String toHex(byte[] b) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < b.length; ++i) {
            String s = Integer.toHexString(255 & b[i]);
            if(s.length() < 2) {
                sb.append("0");
            }

            sb.append(s);
        }

        return sb.toString();
    }

    public static String hexMD5(byte[] data) {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.reset();
            e.update(data);
            byte[] digest = e.digest();
            return toHex(digest);
        } catch (NoSuchAlgorithmException var3) {
            throw new RuntimeException("Error - this implementation of Java doesn't support MD5.");
        }
    }

    public static String hexMD5(ByteBuffer buf, int offset, int len) {
        byte[] b = new byte[len];

        for(int i = 0; i < len; ++i) {
            b[i] = buf.get(offset + i);
        }

        return hexMD5(b);
    }
}
