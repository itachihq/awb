package com.awb.controller;

import com.alibaba.fastjson.JSONObject;
import com.awb.entity.vo.Result;
import com.awb.model.Shoporder;
import com.awb.service.OtherService;
import com.awb.service.ShopOrderService;
import com.awb.util.wx.PayUtil;
import com.awb.util.wx.WXPayUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/10/12.
 */
@RestController
@RequestMapping("/other")
public class WxController {
    private static Logger log = LoggerFactory.getLogger(WxController.class);
    @Autowired
    private OtherService otherService;

    @Autowired
    private ShopOrderService shopOrderService;


    @RequestMapping(value = "/success")
    public void acceptPayResult(HttpSession session, RedirectAttributes attr, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpServletResponse response) throws Exception   {
        //未做签名和数额验证！
        PrintWriter writer = response.getWriter();
        InputStream inStream = request.getInputStream();
        int _buffer_size = 1024;
        HashMap<String, String> map = new HashMap<String, String>();
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        //SAXReader reader = new SAXReader();
//        Document document =null;// reader.read(inputStream);
//        // 获得xml根元素
//        Element root = document.getRootElement();
//        // 得到根元素的所有子节点
//        List<Element> elementList =null;// root.elements();
//        for (Element e : elementList) {
//            map.put(e.getName(), e.getText());
//        }
        JSONObject json =null;// JSONObject.fromObject(map);
        if (inStream != null) {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] tempBytes = new byte[_buffer_size];
            int count = -1;
            while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
                outStream.write(tempBytes, 0, count);
            }
            outStream.flush();
            //将流转换成字符串
            String result = new String(outStream.toByteArray(), "UTF-8");
            System.out.println("accept result:" + result);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            String successResponse = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
            String orderNo = resultMap.get("out_trade_no");
            String money=resultMap.get("total_fee");
            String trans=resultMap.get("transaction_id");
            String time_end=resultMap.get("time_end");
            log.error("trans"+trans+",dealtime="+time_end);
            shopOrderService.WxCallBack(orderNo,trans,time_end,money);
            writer.write(successResponse);
            writer.flush();
        }
    }

    @RequestMapping(value = "/refund")
    public void refund(HttpSession session, RedirectAttributes attr, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpServletResponse response) throws Exception   {
        //未做签名和数额验证！
        PrintWriter writer = response.getWriter();
        InputStream inStream = request.getInputStream();
        int _buffer_size = 1024;
        HashMap<String, String> map = new HashMap<String, String>();
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        //SAXReader reader = new SAXReader();
//        Document document =null;// reader.read(inputStream);
//        // 获得xml根元素
//        Element root = document.getRootElement();
//        // 得到根元素的所有子节点
//        List<Element> elementList =null;// root.elements();
//        for (Element e : elementList) {
//            map.put(e.getName(), e.getText());
//        }
        JSONObject json =null;// JSONObject.fromObject(map);
        if (inStream != null) {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] tempBytes = new byte[_buffer_size];
            int count = -1;
            while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
                outStream.write(tempBytes, 0, count);
            }
            outStream.flush();
            //将流转换成字符串
            String result = new String(outStream.toByteArray(), "UTF-8");
            System.out.println("accept result:" + result);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            String successResponse = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
            String orderNo = resultMap.get("out_trade_no");
            String money=resultMap.get("total_fee");
            String trans=resultMap.get("transaction_id");
            String time_end=resultMap.get("time_end");
            log.error("trans"+trans+",dealtime="+time_end);
            shopOrderService.WxCallBack(orderNo,trans,time_end,money);
            writer.write(successResponse);
            writer.flush();
        }
    }
}
