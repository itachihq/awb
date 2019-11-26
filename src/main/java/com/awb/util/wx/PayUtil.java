package com.awb.util.wx;




import com.awb.util.UuidUtil;
import org.springframework.util.StringUtils;


import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
* @author zwb
* 微信支付工具
* @create 2018/2/3
*/
public class PayUtil {


    /**
     * 获得支付所需 的参数
     * @author zwb
     * @date
     * @param
     * @return
     */
//    public static Map<String,String> getPayParams(String prepayId,String type)  throws Exception {
//        Map<String,String> map=new HashMap<>();
//        String nonceStr = UuidUtil.get32UUID();//随机字符串
//        String signType="MD5";
//        if("2".equals(type)){
//            map.put("appId",Const.WXAPP_APPID);
//            map.put("signType",signType);
//        }else {
//            map.put("appid",Const.WXAPP_APPID);
//        }
//        map.put("partnerid", Const.MERCHANT_ID);
//        map.put("prepayid", prepayId);
//        map.put("package", "Sign=WXPay");
//        map.put("nonceStr",nonceStr);
//        map.put("timeStamp",Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10))+"");
//        String paySign= WXPayUtil.generateSignature(map,Const.SHANGHU_KEY);
//        map.put("paySign",paySign);
//        return map;
//    }


    public static Map<String,String> getPayParams(String prepayId,String type)  throws Exception {
        Map<String,String> map=new HashMap<>();
        String nonceStr = UuidUtil.get32UUID();//随机字符串
        String signType="MD5";
      if("2".equals(type)){
          map.put("appid",Const.WXAPP_APPID);
          map.put("partnerid", Const.MERCHANT_ID);
          map.put("prepayid", prepayId);
          map.put("package", "Sign=WXPay");
          map.put("noncestr",nonceStr);
          map.put("timestamp",Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10))+"");
      }else {
            map.put("appid",Const.WXAPP_APPID);
            map.put("partnerid", Const.MERCHANT_ID);
            map.put("prepayid", prepayId);
            map.put("package", "Sign=WXPay");
            map.put("nonceStr",nonceStr);
            map.put("timeStamp",Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10))+"");
       }

        String paySign= WXPayUtil.generateSignature(map,Const.SHANGHU_KEY);
        map.put("paySign",paySign);
        return map;
    }
    public static Map<String,String> getPayParams(String prepayId)  throws Exception {
        Map<String,String> map=new HashMap<>();
        String timeStamp = create_timestamp();//时间戳
        String nonceStr = UuidUtil.get32UUID();//随机字符串
        String pkg = "prepay_id="+prepayId;//订单详情扩展字符串,如prepay_id=123456789
        String signType="MD5";
        //String urlParams = "appId=" + Const.WX_APPID + "&timeStamp=" + timeStamp + "&nonceStr=" + nonceStr + "&package=" + pkg + "&signType="+signType+"&key=" + Const.SHANGHU_KEY;
        //String urlParams = "appId=" + Const.WX_APPID + "&nonceStr=" + nonceStr  + "&package=" + pkg + "&signType="+signType + "&timeStamp=" + timeStamp  +"&key=" + Const.SHANGHU_KEY;
        //System.out.println("++++++++getPayParams-urlParams:"+urlParams);
        //String paySign=MD5(urlParams).toUpperCase();
        //map.put("appId",Const.WX_APPID);
        map.put("appid",Const.WXAPP_APPID);
        map.put("partnerid", Const.MERCHANT_ID);
        map.put("prepayid", prepayId);
        map.put("package", "Sign=WXPay");
        map.put("noncestr",nonceStr);
        map.put("timestamp",Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10))+"");

       // map.put("signType",signType);
        String paySign= WXPayUtil.generateSignature(map,Const.SHANGHU_KEY);
        map.put("paySign",paySign);
        return map;
    }
//    public static Map<String,String> getPayParams(String prepayId)  throws Exception {
//        Map<String,String> map=new HashMap<>();
//        String timeStamp = create_timestamp();//时间戳
//        String nonceStr = UuidUtil.get32UUID();//随机字符串
//        String pkg = "prepay_id="+prepayId;//订单详情扩展字符串,如prepay_id=123456789
//        String signType="MD5";
//        //String urlParams = "appId=" + Const.WX_APPID + "&timeStamp=" + timeStamp + "&nonceStr=" + nonceStr + "&package=" + pkg + "&signType="+signType+"&key=" + Const.SHANGHU_KEY;
//        //String urlParams = "appId=" + Const.WX_APPID + "&nonceStr=" + nonceStr  + "&package=" + pkg + "&signType="+signType + "&timeStamp=" + timeStamp  +"&key=" + Const.SHANGHU_KEY;
//        //System.out.println("++++++++getPayParams-urlParams:"+urlParams);
//        //String paySign=MD5(urlParams).toUpperCase();
//        //map.put("appId",Const.WX_APPID);
//        map.put("appId",Const.WXAPP_APPID);
//        map.put("timeStamp",timeStamp);
//        map.put("nonceStr",nonceStr);
//        map.put("package",pkg);
//        map.put("signType",signType);
//        String paySign= WXPayUtil.generateSignature(map,Const.SHANGHU_KEY);
//        map.put("paySign",paySign);
//        return map;
//    }

    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    public static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }


    /** *
     *   获得预支付交易会话标识
     * @author zwb
     * @date ip 用户ip
     * @param fee 费，分
     * @return
     */
//    public static String getPrepayId(String ip,String openid,String fee,String orderNo,String attach) throws Exception{
//        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
//        WXPay pay=new WXPay(config);
//        Map<String, String> reqData=new HashMap<>();
//        reqData.put("appid",Const.WX_APPID);//公众账号ID
//        if(!StringUtils.isEmpty(attach)){ //附加信息，回调返回
//            reqData.put("attach",attach);
//        }
//        reqData.put("mch_id",Const.MERCHANT_ID);//商户号
//        //reqData.put("device_info",null);//设备号
//        reqData.put("nonce_str",PayUtil.create_nonce_str());//随机字符串
//        reqData.put("sign_type","MD5");//
//        reqData.put("body","艾我吧");//商品描述
//        reqData.put("out_trade_no", orderNo);//商户订单号  商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
//        reqData.put("total_fee",fee);//标价金额  订单总金额，单位为分
//        reqData.put("spbill_create_ip",ip);//终端IP  APP和网页支付提交用户端ip
//        reqData.put("notify_url",Const.PREORDER_NOTIFY_URL);//通知地址  异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
//        reqData.put("openid",openid);//用户标识  trade_type=JSAPI时（即公众号支付），此参数必传
//        reqData.put("trade_type","JSAPI");//交易类型  JSAPI，NATIVE，APP
//        //sign
//        String sign=WXPayUtil.generateSignature(reqData,Const.SHANGHU_KEY);
//        reqData.put("sign",sign);//签名
//
//        Map<String, String> respData=pay.unifiedOrder(reqData,5*1000,5*1000);
//        String prepay_id=null;
//        if("SUCCESS".equals(respData.get("return_code"))){
//            if("SUCCESS".equals(respData.get("result_code"))){
//                prepay_id=respData.get("prepay_id");
//            }else{
//                System.out.println("===================result_code:"+respData.get("result_code"));
//            }
//        }else{
//            System.out.println("===================return_msg:"+respData.get("return_msg"));
//        }
//        return prepay_id;
//    }

    public static String getAppPrepayId(String ip,String fee,String orderNo,String attach) throws Exception{
        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
        WXPay pay=new WXPay(config);
        Map<String, String> reqData=new HashMap<>();
        reqData.put("appid","wx1b591212449b4f47");//ID
        if(!StringUtils.isEmpty(attach)){ //附加信息，回调返回
            reqData.put("attach",attach);
        }
        reqData.put("mch_id",Const.MERCHANT_ID);//商户号
        //reqData.put("device_info",null);//设备号
        reqData.put("nonce_str",PayUtil.create_nonce_str());//随机字符串
        //新增1014
       // reqData.put("fee_type", "CNY");     //货币类型 默认人民币：CNY
       reqData.put("sign_type","MD5");//
        reqData.put("body","艾我吧");//商品描述
        reqData.put("out_trade_no", orderNo);//商户订单号  商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
        reqData.put("total_fee",fee);//标价金额  订单总金额，单位为分
        reqData.put("spbill_create_ip",ip);//终端IP  APP和网页支付提交用户端ip
        reqData.put("notify_url",Const.PREORDER_NOTIFY_URL);//通知地址  异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
       // reqData.put("openid",openid);//用户标识  trade_type=JSAPI时（即公众号支付），此参数必传
        reqData.put("trade_type","APP");//交易类型  JSAPI，NATIVE，APP
        //sign
        String sign=WXPayUtil.generateSignature(reqData,Const.SHANGHU_KEY);
        reqData.put("sign",sign);//签名

        Map<String, String> respData=pay.unifiedOrder(reqData,5*1000,5*1000);
        String prepay_id=null;
        if("SUCCESS".equals(respData.get("return_code"))){
            if("SUCCESS".equals(respData.get("result_code"))){
                prepay_id=respData.get("prepay_id");
            }else{
                System.out.println("===================result_code:"+respData.get("result_code"));
            }
        }else{
            System.out.println("===================return_msg:"+respData.get("return_msg"));
        }
        return prepay_id;
    }

    public static void main(String[] args) throws Exception {

        String prepayId =    PayUtil.getAppPrepayId("192.168.100.103", "100", new Date().getTime()+"", null);
        System.out.println("prepayId="+prepayId);
    }
//    public static String payBill(PayBillParam param) throws Exception {
//        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
//        WXPay pay =new WXPay(config);
//        Map<String, String> reqData  =new HashMap<>();
//        reqData.put("appid",Const.WX_APPID);//公众账号ID
//        reqData.put("mch_id",Const.MERCHANT_ID);//商户号
//        reqData.put("nonce_str",PayUtil.create_nonce_str());//随机字符串
//        reqData.put("sign",WXPayUtil.generateSignature(reqData,Const.SHANGHU_KEY));//签名
//        reqData.put("sign_type","MD5");//签名类型
//        reqData.put("bill_date",param.getBillDay());//对账单日期
//        reqData.put("bill_type",param.getBillType());//对账单类型
//        Map<String, String> respData = pay.downloadBill(reqData,5*1000,5*1000);
//        String result ;
//        if("SUCCESS".equals(respData.get("return_code"))){
//            result = respData.get("data") ;
//        }else {
//            result = "fail"+ respData.get("return_msg ");
//        }
//        return result ;
//    }
}
