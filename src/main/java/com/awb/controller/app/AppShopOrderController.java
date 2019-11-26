package com.awb.controller.app;

import com.awb.controller.WxController;
import com.awb.entity.vo.Result;
import com.awb.model.Shoporder;
import com.awb.service.OtherService;
import com.awb.service.ShopOrderService;
import com.awb.util.app.CommonUtil;
import com.awb.util.app.ConfigUtil;
import com.awb.util.app.PayCommonUtil;
import com.awb.util.app.XMLutil;
import com.awb.util.wx.Const;
import com.awb.util.wx.PayUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Administrator on 2019/10/14.
 */
@ApiModel(value = "商城")
@RestController
@RequestMapping("/app/shoporder")
public class AppShopOrderController {
    private static Logger logger = LoggerFactory.getLogger(AppShopOrderController.class);
    @Autowired
    private OtherService otherService;
    @Autowired
    private ShopOrderService shopOrderService;
    @ApiOperation(value = "新增商品订单", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "form"),
            @ApiImplicitParam(name = "address", value = "收货地址", required = true, paramType = "form"),
            @ApiImplicitParam(name = "phone", value = "收货人联系方式", required = true, paramType = "form"),
            @ApiImplicitParam(name = "num", value = "购买数量", required = true, paramType = "form"),
            @ApiImplicitParam(name = "shopid", value = "商品id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "totalmoney", value = "商品总价", required = true, paramType = "form"),

    })
    @PostMapping("/insertnewshoporder")
    public Result insert(Shoporder shop, HttpServletRequest request, String uid){
        if (StringUtils.isEmpty(shop.getAddress())||StringUtils.isEmpty(shop.getName())||StringUtils.isEmpty(shop.getPhone())||StringUtils.isEmpty(shop.getNum())||StringUtils.isEmpty(shop.getShopid())||StringUtils.isEmpty(shop.getTotalmoney())){
            throw new RuntimeException("参数错误");
        }
        if(shop.getNum()<=0){
            throw new RuntimeException("数量必须大于0");
        }
        Result result=new Result();
        shopOrderService.insertNewShoporder(shop,uid);
        result.setData(shop.getId());
        result.setMsg("提交成功");

        return result;
    }
    @ApiOperation(value = "获取preid", httpMethod = "POST", response = Result.class, notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "订单ids", required = true, paramType = "form"),
            @ApiImplicitParam(name = "type", value = "ios:2 android 1", required = true, paramType = "form"),

    })
    @PostMapping("/getapppreid")
    public Result getPreatyid(HttpServletRequest request, String ids,String type) throws Exception {
        Result result=new Result();
        if (StringUtils.isEmpty(ids)||StringUtils.isEmpty(type)){
            throw new RuntimeException("参数错误");
        }
        String prepayId=otherService.getPreid(request,ids);
        System.out.println("prepayId="+prepayId);
       result.setData(PayUtil.getPayParams(prepayId,type));
      //  result.setData(PayUtil.getPayParams(prepayId));
        return result;
    }

    @ApiOperation(value = "获取preid", httpMethod = "POST", response = Result.class, notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "订单ids", required = true, paramType = "form"),

    })
    @PostMapping("/getapppreid1")
    public Map<String, Object> getPreatyid1(HttpServletRequest request, String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            throw new RuntimeException("参数错误");
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {

            // 金额--->w微信支付金额参数以分为单位所以需要*100；
            SortedMap<Object, Object> map = new TreeMap<Object, Object>();
            map.put("appid", Const.WXAPP_APPID);
            map.put("mch_id", Const.MERCHANT_ID);
            map.put("nonce_str", PayUtil.create_nonce_str());
            map.put("body", "艾我吧");
            map.put("out_trade_no","0ddc202ed9194b3a852a6e3f8273ac62"); // 订单id
           // map.put("fee_type", "CNY");     //货币类型 默认人民币：CNY
            map.put("total_fee", "1");
            //获取用户ip
            map.put("spbill_create_ip", CommonUtil.getIpAddr(request));
            map.put("notify_url", Const.PREORDER_NOTIFY_URL);
            map.put("trade_type", "APP");
            for(Map.Entry<Object, Object> entry : map.entrySet()){
                Object mapKey = entry.getKey();
                Object mapValue = entry.getValue();
                System.out.println(mapKey+":"+mapValue);
            }
            //设置签名
            String sign = PayCommonUtil.createSign("UTF-8", map);
            map.put("sign", sign);
            //封装请求参数结束
            String requestXML = PayCommonUtil.getRequestXml(map);
            //调用统一下单接口
            String result = PayCommonUtil.httpsRequest("https://api.mch.weixin.qq.com/pay/unifiedorder", "POST", requestXML);
            logger.info("Wx-下单信息："+result);
            /**统一下单接口返回正常的prepay_id，再按签名规范重新生成签名后，
             * 将数据传输给APP。参与签名的字段名为
             * appId，partnerId，prepayId，nonceStr，timeStamp，package。
             * 注意：package的值格式为Sign=WXPay**/
            Map map2 = XMLutil.doXMLParse(result);
            SortedMap<Object,Object> map3 = new TreeMap<Object, Object>();
            map3.put("appid",  Const.WXAPP_APPID);
            map3.put("partnerid",  Const.MERCHANT_ID);
            map3.put("prepayid", map2.get("prepay_id"));
            map3.put("package", "Sign=WXPay");
            map3.put("noncestr", PayCommonUtil.CreateNoncestr());
            //生成时间戳 13位 ios默认规定10位
            map3.put("timestamp", Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10)));
            String sign2=PayCommonUtil.createSign("UTF-8", map3);
            map3.put("sign", sign2);
            resultMap.put("code", "200");
            resultMap.put("msg", map3);

            return resultMap;

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
}
