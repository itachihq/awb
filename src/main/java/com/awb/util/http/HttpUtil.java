package com.awb.util.http;

import com.alibaba.fastjson.JSONObject;
import com.awb.util.wx.Const;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2019/10/23.
 */
public class HttpUtil {
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
        String url="http://c49y65.natappfree.cc/other/shopdetail?id=a2340073ce324b7db484a0bdc3117c6a";
        String result=HttpUtil.getHttpResultZwb(url);
        /* result 状如下
        { "access_token":"ACCESS_TOKEN",
        "expires_in":7200,
        "refresh_token":"REFRESH_TOKEN",
        "openid":"OPENID",
        "scope":"SCOPE" }
         */
        //System.out.println("getOpenid result:"+result);
        JSONObject jsonObject=JSONObject.parseObject(result);
        String openid=jsonObject.getString("success");
        System.out.println(openid);
        JSONObject jo=JSONObject.parseObject(openid);
        System.out.println(jo.getString("shopName"));
        if(StringUtils.isEmpty(openid)){
            throw new RuntimeException(result);
        }

    }
}
