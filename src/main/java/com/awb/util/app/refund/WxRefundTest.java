package com.awb.util.app.refund;

import com.awb.util.Coderutil;
import com.awb.util.UuidUtil;
import com.awb.util.app.PayCommonUtil;
import com.awb.util.wx.Const;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Administrator on 2019/10/17.
 */
public class WxRefundTest {

//    public String doRefoundByWX(Map<String,Object> map) throws Exception {
//
//
//        String refund_money = "1"; //退款金额
//        String out_trade_no = map.get("transaction_id").toString();//微信订单号
//        String channel = map.get("channel").toString();
//        String sumMoney = map.get("sum_money").toString(); //订单总价
//
//        String result = "";
//        //根据app渠道获取微信的appid及appSecret
//        //WxpayUtil.loadWxAppIdAndSecret(Integer.valueOf(channel));
//        String mch_id = "1494675952";
//        String appid = "wx43e48e6bd13d8b4a";
//      //  String partnerkey = WxpayUtil.WECHATPAY_PARTNERKEY;
//        String nonce_str = UuidUtil.get32UUID();//随机字符串
//        String out_refund_no = Coderutil.getAwbOrderNo("0250");//商户退款单号
//        Double total_fee = 0d;
//        try {
//          //  total_fee = StringUtil.getDouble(sumMoney);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //总金额以分为单位
//    //    long totalAmount = new BigDecimal(total_fee * 100d).longValue();
//        Double refund_fee = Double.parseDouble(refund_money);
//        //退款金额以分为单位
//      //  long Amount = new BigDecimal(refund_fee * 100d).longValue();
//
//        String totalAmount="1";
//        String Amount="1";
//        //签名算法
//        SortedMap<Object, Object> SortedMap = new TreeMap<Object, Object>();
//        SortedMap.put("appid", appid);
//        SortedMap.put("mch_id", mch_id);
//        SortedMap.put("nonce_str", nonce_str);
//        SortedMap.put("out_trade_no", out_trade_no);
//        SortedMap.put("out_refund_no", out_refund_no);
//        SortedMap.put("total_fee", "1");
//        SortedMap.put("refund_fee", "1");
//
//
//        String sign = PayCommonUtil.createSign("UTF-8",SortedMap);
//        //获取最终待发送的数据
//        String requestXml = "<xml>" +
//                "<appid>" + appid + "</appid>" +
//                "<mch_id>" + mch_id + "</mch_id>" +
//                "<nonce_str>" + nonce_str + "</nonce_str>" +
//                "<out_trade_no>" + out_trade_no + "</out_trade_no>" +
//                "<out_refund_no>" + out_refund_no + "</out_refund_no>" +
//                "<total_fee>" + totalAmount + "</total_fee>" +
//                "<refund_fee>" + Amount + "</refund_fee>" +
//                "<sign>" + sign + "</sign>" +
//                "</xml>";
//        //建立连接并发送数据
//        HashMap<String, Object> resultMap = null;
//        try {
//            result = WeiXinUtil.WeixinSendPost(requestXml,mch_id,channel);
//            //解析返回的xml
//            resultMap = new HashMap<String, Object>(XMLParse.parseXmlToList2(result));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        //退款返回标志码
//        String return_code = resultMap.get("return_code").toString();
//        String result_code = resultMap.get("result_code").toString();
//        String msg = "";
//        if(return_code.equals("SUCCESS") && result_code.equals("SUCCESS")){
//            String userId = payorderMapper.getUserIdByOutTradeOrder(out_trade_no);
//            //减少用户余额
//            Integer updateAmount = this.accountMapper.subtractAmount(userId, refund_money);
//            // 生成我的钱包流水
//            Double remainAmount = this.accountMapper.findAmountByUserId(userId);
//            AccountFlow flow = AccountFlow.newBuilder().setUserId(userId).setOrderCode(out_trade_no).setBody("微信原路退款扣除金额")
//                    .setIsInflow("0").setTotal_amount(refund_money).setRemainAmount(remainAmount.toString()).build();
//            Integer insertAccountFlow = this.accountFlowMapper.insert(flow.toMap());
//
//            msg = "微信原路返款成功!";
//            if(updateAmount != 1 || insertAccountFlow != 1) {
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                return JsonUtil.toJson(new ResultBean(true, "微信原路返款失败", null));
//            }
//        }else if(return_code.equals("SUCCESS") && result_code.equals("FAIL")){
//            msg = "微信原路返款失败!";
//        }else{
//            msg = "微信原路返款未知错误!";
//        }
//        return JsonUtil.toJson(new ResultBean(true, msg, null));
//
//    }

    public static void main(String[] args) {
       new WxRefundTest(). refundWeChat(null,null);
    }

    private boolean refundWeChat(String transactionId, Long orderAmount)
            throws RuntimeException {
        TreeMap<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("appid","wx43e48e6bd13d8b4a");
        parameters.put("mch_id", "1494675952");
        parameters.put("nonce_str", CommonUtil.getRandomString(32));
       // parameters.put("transaction_id", "4200000426201910170880366694");
        parameters.put("out_trade_no", "025620191119142200067818");
        parameters.put("out_refund_no", CommonUtil.getRandomString(32));
        parameters.put("total_fee", "1");
        parameters.put("refund_fee", "1");
        //parameters.put("notify_url", Const.REFUND_NOTIFY_URL);
        String sign = CommonUtil.createSign("UTF-8", parameters);
        parameters.put("sign", sign);

        String resContent = "";
        String tosend = CommonUtil.getRequestXml(parameters);
        try {
            resContent = CommonUtil.httpsRequest2(WeChatConfig.REFUND_URL, "POST", tosend);
            Map<String, String> map = CommonUtil.doXMLParse(resContent);
            if (map.get("return_code").toString().equalsIgnoreCase("SUCCESS")) {
                if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
                    //此处可以添加退款成功业务逻辑
                    return true;
                } else {
                    //此处可以添加退款失败业务逻辑
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




}
