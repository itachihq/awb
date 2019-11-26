//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.awb.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

public class EncryptDataSign {
    private static final String MAC_NAME = "HmacSHA1";
    private static final String MD5_NAME = "MD5";
    private static final String ENCODING = "UTF-8";
    private static final String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public EncryptDataSign() {
    }

    public static String encodeMd5(String origin, String charsetname) {
        String resultString = null;
        resultString = new String(origin);

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var6) {
            throw new RuntimeException(var6);
        }

        if(charsetname != null && !"".equals(charsetname)) {
            try {
                resultString = Hex.encodeHexString(md.digest(resultString.getBytes(charsetname)));
            } catch (UnsupportedEncodingException var5) {
                throw new RuntimeException(var5);
            }
        } else {
            resultString = Hex.encodeHexString(md.digest(resultString.getBytes()));
        }

        return resultString;
    }

    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes("UTF-8");
        SecretKey secretKey = new SecretKeySpec(data, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKey);
        byte[] text = encryptText.getBytes("UTF-8");
        return mac.doFinal(text);
    }

    public static String HmacSHA1EncryptHexString(String encryptText, String encryptKey) {
        try {
            byte[] data = encryptKey.getBytes("UTF-8");
            SecretKey secretKey = new SecretKeySpec(data, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKey);
            byte[] text = encryptText.getBytes("UTF-8");
            String result = byteArrayToHexString(mac.doFinal(text));
            return result;
        } catch (Exception var7) {
            var7.printStackTrace();
            return null;
        }
    }

    public static String sha1Encrypt(String filename) {
        InputStream fis = null;
        byte[] buffer = new byte[64];
        boolean var3 = false;

        Object var6;
        try {
            fis = new FileInputStream(filename);
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");

            int numRead;
            while((numRead = fis.read(buffer)) > 0) {
                sha1.update(buffer, 0, numRead);
            }

            String var5 = byteArrayToHexString(sha1.digest());
            return var5;
        } catch (Exception var16) {
            System.out.println("error");
            var6 = null;
        } finally {
            try {
                if(fis != null) {
                    fis.close();
                }
            } catch (IOException var15) {
                var15.printStackTrace();
            }

        }

        return (String)var6;
    }

    public static String encodeByMd5(String str) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(str.getBytes());
        byte[] digest = md5.digest();
        String hex = byteArrayToHexString(digest);
        return hex;
    }

    private static String byteToHexString(byte b) {
        int ret = b;
        if(b < 0) {
            ret = b + 256;
        }

        int m = ret / 16;
        int n = ret % 16;
        return hexDigits[m] + hexDigits[n];
    }

    public static String byteArrayToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < bytes.length; ++i) {
            sb.append(byteToHexString(bytes[i]));
        }

        return sb.toString();
    }

    public static String dataSignature(List<String> echostr, String timestamp, String nonce, String encryptKey) {
        try {
            echostr.add(timestamp);
            echostr.add(nonce);
            echostr.add(encryptKey);
            System.out.println("排序前：" + echostr.toString());
            Collections.sort(echostr);
            String data = echostr.toString();
            System.out.println("排序后：" + data);
            byte[] signature = HmacSHA1Encrypt(data, encryptKey);
            String resultdata = byteArrayToHexString(signature);
            System.out.println("签名串:" + resultdata);
            return resultdata;
        } catch (Exception var7) {
            var7.printStackTrace();
            return "";
        }
    }

    public static String dataSignatureMd5(String echostr, String timestamp, String nonce, String encryptKey) {
        try {
            List<String> list = new ArrayList();
            list.add(timestamp);
            list.add(nonce);
            list.add(encryptKey);
            System.out.println("排序前：" + list.toString());
            Collections.sort(list);
            String data = echostr.toString();
            System.out.println("排序后：" + data);
            String resultdata = encodeByMd5(data);
            System.out.println("签名串:" + resultdata);
            return resultdata;
        } catch (Exception var7) {
            var7.printStackTrace();
            return "";
        }
    }
}
