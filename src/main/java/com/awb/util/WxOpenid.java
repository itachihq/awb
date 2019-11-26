package com.awb.util;

import com.alibaba.fastjson.JSONObject;
import com.awb.util.wx.Const;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.ParseException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/10/30.
 */
public class WxOpenid {
    public static String getOpenid(String code) throws Exception{
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ Const.WX_APPID+"&secret="+Const.WX_SECRET+"&code="+code+"&grant_type=authorization_code";
        String result=getHttpResultZwb(url);
        /* result 状如下
        { "access_token":"ACCESS_TOKEN",
        "expires_in":7200,
        "refresh_token":"REFRESH_TOKEN",
        "openid":"OPENID",
        "scope":"SCOPE" }
         */
        //System.out.println("getOpenid result:"+result);
        JSONObject jsonObject=JSONObject.parseObject(result);
        String openid=jsonObject.getString("openid");
        if(StringUtils.isEmpty(openid)){
            throw new RuntimeException(result);
        }
        return openid;
    }
    /**
     * 获得 微信公众号的 jsapi_ticket,需缓存，7200s
     *
     * @param
     * @return
     * @author zwb
     * @date
     */
    public static String getTicket(String accessToken) throws Exception {
        String result = getHttpResultZwb("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi");

        //{"errcode":0,"errmsg":"ok","ticket":"HoagFKDcsGMVCIY2vOjf9gwgHc8Ql4Ru3YOve14JB4ExXuR4T9u_Q63s8ys7G5UA703IYARFBTeZNHnXmhHjvw","expires_in":7200}
        JSONObject jsonObject=JSONObject.parseObject(result);
        String ticket=jsonObject.getString("ticket");
        if(StringUtils.isEmpty(ticket)){
            throw new RuntimeException(result);
        }
       // String ticketPart = result.split(",")[2].split(":")[1];
        return  ticket;//ticketPart.substring(1, ticketPart.length() - 1);
    }
    public static String getAccessToken() throws Exception {

        //result: {"access_token":"6_zr0V5iTthQl11ST1ihwXdbIZBP06uL0Bf_zoUztEfx464X7WinpNITbzAEcGB6EBvxzfg4r4E9aLDyQX2soQ-eXNkreY0VunGdgWVHqKJKkx-x1pkCMEVVGA1qjknCl35CxgrceOJHmFzf3oEQLiAFAPBW","expires_in":7200}
        String result = getHttpResultZwb("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Const.WX_APPID + "&secret=" + Const.WX_SECRET);
        if (StringUtils.isEmpty(result)) {
            throw new Exception("getAccessToken result为空");
        }

        JSONObject jsonObject=JSONObject.parseObject(result);
        String access_token=jsonObject.getString("access_token");
        System.out.println(result);
        if(StringUtils.isEmpty(access_token)){
            throw new RuntimeException(result);
        }
//        String accessPart = result.split(",")[0].split(":")[1];
        return access_token; //accessPart.substring(1, accessPart.length() - 1);
    }
//    public static String getAccess_token(String code) throws Exception{
//        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ Const.WX_APPID+"&secret="+Const.WX_SECRET+"&code="+code+"&grant_type=authorization_code";
//        String result=getHttpResultZwb(url);
//        JSONObject jsonObject=JSONObject.parseObject(result);
//        String access_token=jsonObject.getString("access_token");
//        System.out.println(result);
//        return access_token;
//    }
    private static String getHttpResultZwb(String url) throws Exception {
        String result = "";
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        HttpURLConnection conn = (HttpURLConnection) new URL(url.toString()).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        }
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) throws Exception {
        //15_G54uj8oDQs92wBW2355di_bJrmPPiZLeqT4NVhQwzSdLSRLY4Vr3eld5oj2vSgwnCn140RDHlf6KL9XnbqCrU16N74w4QkORyIPgIKe4qbqycTJfLFJU8RnkoqdHZELLt1boalNZW0WbFJgcANOfADAREF
        //HoagFKDcsGMVCIY2vOjf9gwgHc8Ql4Ru3YOve14JB4EciauQHNOAXjLqcHq9nJ07bfAJaL2edCSCo9vAc2fydg
        String accessToken = getAccessToken();
        System.out.println(accessToken);
     // String result= getTicket("15_G54uj8oDQs92wBW2355di_bJrmPPiZLeqT4NVhQwzSdLSRLY4Vr3eld5oj2vSgwnCn140RDHlf6KL9XnbqCrU16N74w4QkORyIPgIKe4qbqycTJfLFJU8RnkoqdHZELLt1boalNZW0WbFJgcANOfADAREF");
       // String result = getAccess_token("061sJH3u1L0xfc0Ivc5u1IDL3u1sJH3H");
      //  System.out.println( result);
    }
    public static String getOpenIdUrl(String username) throws Exception {
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
        url = url.replace("APPID",  URLEncoder.encode(Const.WX_APPID, "utf-8"));
        url = url.replace("STATE", "1");
        String no=Coderutil.getAwbOrderNo("bd");;
        String  ss="http://web.myaiwoba.com/aiwobaMarket/mobile/shop/getopen?shopId=bf66ae1a789f445aa8e0c60ff796ad3e&orderNumber="+no+"&userId=27a6989c8bfb43869b39da0b25a64e9f&price=5000&type=1";
        url = url.replace("REDIRECT_URI",  URLEncoder.encode(ss, "utf-8"));
        return url;
    }
    /**
     * 官方给的使用js的签名工具
     *
     * @author zwb
     */
    public static Map<String, String> sign(String url, String jsapiTicket)   {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";
        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapiTicket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println("string1:" + string1);
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }  catch (Exception e) {
            e.printStackTrace();
        }
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapiTicket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        return ret;
    }
    private static String create_nonce_str() {
        return UuidUtil.get32UUID();
    }
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    public static boolean isFalse(String url, String name) {
        url += "&";
        String pattern = "(\\?|&){1}#{0,1}" + name + "=[a-zA-Z0-9]*(&{1})";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(url);
        if (m.find( )) {
            //System.out.println(m.group(0));
            return true;
        } else {
            return false;
        }
    }
}
