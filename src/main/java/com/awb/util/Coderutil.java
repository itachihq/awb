package com.awb.util;


import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2018/10/29.
 */
public class Coderutil {
    public static SimpleDateFormat fmyyyyMMddHHmmss2 = new SimpleDateFormat("yyyyMMddHHmmssss");
    public static String getAwbOrderNo(String prefix) throws Exception {
        if (StringUtils.isEmpty(prefix)) {
            prefix = "";
        }
        if (prefix.length() > 14) {
            throw new Exception("getAwbOrderNo前缀过长!");
        }
        return prefix + fmyyyyMMddHHmmss2.format(new Date()) + getSMSCode(4);
    }
    public static String getSMSCode(int digits){
        Random random = new Random();
        String sRand = "";
        for (int i = 0; i < digits; i++) {
            sRand += String.valueOf(random.nextInt(10));
        }
        return sRand;
    }

    public static void main(String[] args) throws Exception {
        System.out.println( DecimalUtil.calcNumber(3,0.6,"*").intValue());
    }
    /**
     * 获取客户端用户的真实IP;
     *
     * @param request HttpServletRequest
     * @return 真实IP
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf("0:0") != -1)
            ip = "127.0.0.1";
        if (!org.springframework.util.StringUtils.isEmpty(ip)) {
            String ips[] = ip.split(",");
            if (ips.length >= 2) {
                ip = ips[0];
            }
        }
        return ip;
    }
}
