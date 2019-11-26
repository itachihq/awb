package com.awb.service;

import com.awb.entity.param.ShopParam;
import com.awb.entity.vo.*;
import com.awb.model.Shop;
import com.awb.model.User;

import java.util.List;

/**
 * Created by Administrator on 2019/9/8.
 */
public interface ShopService {
    void insertShop(Shop record, String leverLength);
    void insertNewShop(Shop record);
    List<AdminShopVo> adminListShop(String id);
    List<AdminShopVo> adminListNewShop(String id);
    PageResult<AppShopVo> appListShop(ShopParam shopParam,String uid);
    PageResult<Shop> appListNewShop(ShopParam shopParam);
    PageResult<Shop> appListNewShop1(ShopParam shopParam);
    List<User> listUser();
    AppMerchantsVo selectMerchant(String id);
    AppMerchantsVo selectNewMerchant(String id);
    List<Shop>  listMerchant(String userid);
    //
    void modifyShopAttribute(String id,Integer type,Integer sign);
    List<MerchantVo> listmerchantvo();
    void updateShopPrice(String shopid,Integer nodelever,String price);
     void  updateByPrimaryKeySelective(Shop shop);
     Shop selectShop(String uid,String shopid);
    Shop selectShop(String shopid);
    //void updateNewShop(Shop record);
    Shop selectShopDetail(String id);
    Shop selectShopDetail(String id,String uid);

}
