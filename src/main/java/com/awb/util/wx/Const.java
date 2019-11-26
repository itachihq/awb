package com.awb.util.wx;



import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
* @author zwb
* 长连接静态变量
* @create 2018/1/3
*/
public class Const {


    /*appid*/
    public static final String WX_APPID="wx43e48e6bd13d8b4a";
    /*secret*/
    public static final String WX_SECRET="e164c962eecc56af0b4e7b40c0b4703d";
    public static String MERCHANT_ID="1494675952";
    public static String SHANGHU_KEY="daa647889e44445e92dd083f650bcb86";//商户密钥
    public static String FEE="100";
  //  public static String PREORDER_NOTIFY_URL="web.myaiwoba.com/aiwobaMarket/mobile/wx/success";//统一支付，异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。

    //  public static String PREORDER_NOTIFY_URL="http://xjvmc5.natappfree.cc/other/success";

     public static String PREORDER_NOTIFY_URL="http://www.myaiwoba.com/appadm/other/success";
   // public static String PREORDER_NOTIFY_URL="web.myaiwoba.com/pro/mobile/wx/success";

    /*appidApp支付*/
    public static final String WXAPP_APPID="wx1b591212449b4f47";
    //退款通知
    public static String REFUND_NOTIFY_URL = "6pg222.natappfree.cc0/other/refund";
}
