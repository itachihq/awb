//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.awb.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class IDGenerator {
    private static final int UID_SIZE = 32;
    private static final int TOKEN_SIZE = 48;
    public static Random random = new Random();

    public IDGenerator() {
    }

    public static String getUserID(String Uid) {
        try {
            String result = UUID.randomUUID().toString();
            String nonce = Long.toString(System.currentTimeMillis());
            result = result.replace("-", "") + nonce;
            result = EncryptDataSign.byteArrayToHexString(result.getBytes());
            return result.length() > 32?result.substring(result.length() - 32, result.length()):result;
        } catch (Exception var3) {
            var3.printStackTrace();
            return "getUserID Error" + var3.getMessage();
        }
    }

    public static String getUserToken(String Uid) {
        try {
            String result = UUID.randomUUID().toString() + Uid;
            String nonce = "" + System.currentTimeMillis();
            String endStr = EncryptDataSign.byteArrayToHexString((result.replace("-", "") + getMac()).getBytes());
            nonce = endStr.substring(endStr.length() - 8) + nonce;
            return nonce.length() > 48?nonce.substring(nonce.length() - 48, 48):nonce;
        } catch (Exception var4) {
            var4.printStackTrace();
            return "getUserToken Error" + var4.getMessage();
        }
    }

    public static String getEventId(String eventName) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmss");
            String nonce = Long.toString(System.currentTimeMillis());
            String dateString = formatter.format(new Date());
            String endStr = EncryptDataSign.byteArrayToHexString(getMac().getBytes());
            int len = nonce.length();
            dateString = eventName + dateString + endStr.substring(endStr.length() - 6) + nonce.substring(len - 3, len);
            return dateString;
        } catch (Exception var6) {
            return eventName + System.currentTimeMillis();
        }
    }

    public static String getMac() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            return Coder.toHex(mac).toLowerCase();
        } catch (Exception var2) {
            return getUserID("1045");
        }
    }

    public static String getDigitCode(int size) {
        StringBuilder ret = new StringBuilder();

        for(int i = 0; i < size; ++i) {
            ret.append(Integer.toString(random.nextInt(10)));
        }

        return ret.toString();
    }

    public static void main(String[] args) {
        String usid = getUserID("1231");
        String ustokn = getUserToken("NN0");
        String eventid = getEventId("CX");
        System.out.println(usid);
        System.out.println(ustokn);
        System.out.println(eventid);
    }
}
