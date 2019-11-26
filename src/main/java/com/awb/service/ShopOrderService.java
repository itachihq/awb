package com.awb.service;

import com.awb.entity.param.ShoporderParam;
import com.awb.entity.vo.AppShopOrderVo;
import com.awb.entity.vo.PageResult;
import com.awb.entity.vo.ShopOrderVo;
import com.awb.entity.vo.ShopPriceVo;
import com.awb.model.Shoporder;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/9/8.
 */
public interface ShopOrderService {
    void insertShoporder(Shoporder shoporder,String uid);
    void insertNewShoporder(Shoporder shoporder,String uid);
    List<AppShopOrderVo> selectmyorder(String  uid,Integer status,Integer type);
    List<ShopOrderVo>  selectAllShopOrder(Integer status);
    PageResult<ShopOrderVo> selectAllShopOrder(ShoporderParam shoporderParam);
    void shoprder(Shoporder shoporder);
    List<ShopPriceVo> selectShopPrice(String shopid);
 void WxCallBack(String orderno,String transid,String dealtime,String money);
}
