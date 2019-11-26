package com.awb.util.app.refund;

/**
 * Created by Administrator on 2019/10/17.
 */
public class WeChatConfig {
    public static String APP_ID = "xxxxxx";
    public static String MCH_ID = "xxxxxx";
    public static String MCH_KEY = "xxxxxx";
    public static String APP_SECRET = "xxxxxx";
    public static String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static String NOTIFY_URL = "http://xxx.xxx.xxx.xxx:8080/XqlApi/wechatpay/paynotify";
    public static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    public static String REFUND_NOTIFY_URL = "http://xxx.xxx.xxx.xxx:8080/XqlApi/wechatpay/refundnotify";
    public static String TRADE_TYPE = "APP";
    public static String CERT_URL="E:\\cert\\apiclient_cert.p12";

}
