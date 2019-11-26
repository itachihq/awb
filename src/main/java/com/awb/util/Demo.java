package com.awb.util;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

public class Demo {

	
	public static void main(String[] args) throws Exception {
		// Demo api = new Demo();
        //System.out.println( "sf="+     api. getOrderTracesByJson("DBL","7896506122"));

        String str="/web/login";
        System.out.println(str.contains("/web"));
        //String result = api.getOrderTracesByJson("SF", "000000");
//        List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
//        String result = api.getOrderTracesByJson("UC", "518708827303");
//        JSONObject jsonObject=JSONObject.parseObject(result);
//        String str=jsonObject.getString("Success");
//        String state=jsonObject.getString("State");
//
//            String str1=jsonObject.getString("Traces");
//            JSONArray jsonArray= JSONArray.parseArray(str1);
//            for(int i=0;i<jsonArray.size();i++){
//                Map map=new TreeMap();
//                map.put("AcceptStation",(String) jsonArray.getJSONObject(i).get("AcceptStation"));
//                map.put("AcceptTime",(String)jsonArray.getJSONObject(i).get("AcceptTime"));
//                list.add(map);
//            }
//for (Map li:list){
//    System.out.println(li.get("AcceptStation")+","+li.get("AcceptTime"));
//}
//        JSONObject jsonObject=JSONObject.fromObject(result);
//        String str1=jsonObject.getString("Success");
//        if("false".equals(str1)){
//            System.out.println(1);
//        }else {
//            System.out.println(2);
//        }
//        System.out.println(str1);
//        JSONObject jsonObject=JSONObject.parseObject(result);
//        String str=jsonObject.getString("Traces");
//        System.out.println(str);
//        JSONArray jsonArray=JSONArray.parseArray(str);
//        System.out.println(jsonArray.size());
//        for(int i=0;i<jsonArray.size();i++){
//            System.out.println(jsonArray.getJSONObject(i).get("AcceptStation"));
//        }
//        System.out.println(result);
	}
	
	
	//����ID
    private String EBusinessID="1315158";
    //���̼���˽Կ��������ṩ��ע�Ᵽ�ܣ���Ҫй©
    private String AppKey="c57ae8d8-3de2-4bde-8888-bb9e4d5d5b60";
    //����url
    private String ReqURL="http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";

    /**
     * Json��ʽ ��ѯ���������켣
     * @throws Exception
     */
    public  String getOrderTracesByJson(String expCode, String expNo) throws Exception{
        String requestData= "{'OrderCode':'','ShipperCode':'" + expCode + "','LogisticCode':'" + expNo + "'}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", EBusinessID);
        params.put("RequestType", "1002");
        String dataSign=encrypt(requestData, AppKey, "UTF-8");
        params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        String result=sendPost(ReqURL, params);

        //���ݹ�˾ҵ�����ص���Ϣ......

        return result;
    }

    /**
     * MD5����
     * @param str ����
     * @param charset ���뷽ʽ
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * base64����
     * @param str ����
     * @param charset ���뷽ʽ
     * @throws UnsupportedEncodingException
     */
    private String base64(String str, String charset) throws UnsupportedEncodingException{
        String encoded = base64Encode(str.getBytes(charset));
        return encoded;
    }

    @SuppressWarnings("unused")
    private String urlEncoder(String str, String charset) throws UnsupportedEncodingException{
        String result = URLEncoder.encode(str, charset);
        return result;
    }

    /**
     * ����Signǩ������
     * @param content ����
     * @param keyValue Appkey
     * @param charset ���뷽ʽ
     * @throws UnsupportedEncodingException ,Exception
     * @return DataSignǩ��
     */
    @SuppressWarnings("unused")
    private String encrypt (String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception
    {
        if (keyValue != null)
        {
            return base64(MD5(content + keyValue, charset), charset);
        }
        return base64(MD5(content, charset), charset);
    }

    /**
     * ��ָ�� URL ����POST����������
     * @param url ��������� URL
     * @param params ����Ĳ�������
     * @return Զ����Դ����Ӧ���
     */
    @SuppressWarnings("unused")
    private String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
            // ����POST�������������������
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST����
            conn.setRequestMethod("POST");
            // ����ͨ�õ���������
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // ��ȡURLConnection�����Ӧ�������
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // �����������
            if (params != null) {
                StringBuilder param = new StringBuilder();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if(param.length()>0){
                        param.append("&");
                    }
                    param.append(entry.getKey());
                    param.append("=");
                    param.append(entry.getValue());
                    //System.out.println(entry.getKey()+":"+entry.getValue());
                }
                //System.out.println("param:"+param.toString());
                out.write(param.toString());
            }
            // flush������Ļ���
            out.flush();
            // ����BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ʹ��finally�����ر��������������
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }


    private static char[] base64EncodeChars = new char[] {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/' };

    public static String base64Encode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len)
            {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len)
            {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }
	

}
