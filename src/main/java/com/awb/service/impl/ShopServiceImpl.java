package com.awb.service.impl;

import com.awb.entity.param.ShopParam;
import com.awb.entity.vo.*;
import com.awb.mapper.OtherMapperExt;
import com.awb.mapper.ShopLeverMapper;
import com.awb.mapper.ShopMapper;
import com.awb.mapper.UserMapper;
import com.awb.model.*;
import com.awb.service.ShopService;
import com.awb.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/9/8.
 */
@Service
@Transactional
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ShopLeverMapper shopLeverMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OtherMapperExt otherMapperExt;


    @Override
    public void insertShop(Shop record, String leverLength) {
        try {
            User user=userMapper.selectByPrimaryKey(record.getUserid());
            if(user==null){
                throw new RuntimeException("商家不存在");
            }
            ShopExample admshopExample=new ShopExample();
            admshopExample.createCriteria().andShopNameEqualTo(record.getShopName()).andIsDeleteEqualTo(0).andUseridEqualTo(record.getUserid());
            List<Shop> admshopList= shopMapper.selectByExample(admshopExample);
            if(admshopList.size()>0){
                throw new RuntimeException("商品已存在");
            }
            String[] one=leverLength.split(",");
//            if(one.length!=5){
//                throw new RuntimeException("leverLength");
//            }
            String id= UuidUtil.get32UUID();
            record.setId(id);
            Date date=new Date();
            record.setCreatetime(date);
            for(String str:one){
                String[] two=str.split(":");
               ShopLever admShopLever=new ShopLever();
                admShopLever.setCreatetime(date);
                admShopLever.setNodelever(Integer.valueOf(two[0]));
                admShopLever.setShopid(id);
                admShopLever.setPrice(two[1]);
                //admShopLever.setAdmnum(Integer.valueOf(two[2]));
                shopLeverMapper.insertSelective(admShopLever);
            }
            shopMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void insertNewShop(Shop record) {
        Date date=new Date();
        record.setCreatetime(date);
        record.setId(UuidUtil.get32UUID());
        record.setSign(1);
        record.setUserid("d91ed53e9200495c9112132ced069603");
        shopMapper.insertSelective(record);
    }

    @Override
    public List<AdminShopVo> adminListShop(String id) {
        Map map=new HashMap();
        map.put("userid",id);
        map.put("sign",0);
        return otherMapperExt.listAdminShop(map);
    }

    @Override
    public List<AdminShopVo> adminListNewShop(String id) {
        Map map=new HashMap();
        map.put("userid",id);
        map.put("sign",1);
        return otherMapperExt.listAdminShop(map);
    }

    @Override
    public PageResult<AppShopVo> appListShop(ShopParam shopParam,String uid) {
        User user=userMapper.selectByPrimaryKey(uid);
        if(null==user){
            throw new RuntimeException("用户不存在");
        }
        shopParam.setUid(uid);
        PageHelper.startPage(shopParam.getCurrentPage(),shopParam.getPageSize());
        List<AppShopVo> shopList=otherMapperExt.appListShop(shopParam);
        PageInfo<AppShopVo> pageInfo = new PageInfo<AppShopVo>(shopList);
        return new PageResult(pageInfo.getTotal(),shopList,shopParam.getCurrentPage()) ;
    }

    @Override
    public PageResult<Shop> appListNewShop(ShopParam shopParam) {
        User user=userMapper.selectByPrimaryKey(shopParam.getUid());
        if(null==user){
            throw new RuntimeException("用户不存在");
        }
        ShopExample shopExample=new ShopExample();
        shopExample.createCriteria().andSignEqualTo(1);
        PageHelper.startPage(shopParam.getCurrentPage(),shopParam.getPageSize());
        List<Shop> shopList=otherMapperExt.appListNewShop(shopParam);
        PageInfo<Shop> pageInfo = new PageInfo<Shop>(shopList);
        return new PageResult(pageInfo.getTotal(),shopList,shopParam.getCurrentPage()) ;
    }

    @Override
    public PageResult<Shop> appListNewShop1(ShopParam shopParam) {
        User user=userMapper.selectByPrimaryKey(shopParam.getUid());
        if(null==user){
            throw new RuntimeException("用户不存在");
        }
        ShopExample shopExample=new ShopExample();
        shopExample.createCriteria().andSignEqualTo(1);
        PageHelper.startPage(shopParam.getCurrentPage(),shopParam.getPageSize());
        List<Shop> shopList=otherMapperExt.appListNewShop1(shopParam);
        PageInfo<Shop> pageInfo = new PageInfo<Shop>(shopList);
        return new PageResult(pageInfo.getTotal(),shopList,shopParam.getCurrentPage()) ;
    }

    @Override
    public List<User> listUser() {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andTypeEqualTo(2);
        userExample.setOrderByClause("createtime desc");
        return userMapper.selectByExample(userExample);
    }

    @Override
    public AppMerchantsVo selectMerchant(String id) {
        return otherMapperExt.selectMerchant(id);
    }

    @Override
    public AppMerchantsVo selectNewMerchant(String id) {
        return otherMapperExt.selectNewMerchant(id);
    }

    @Override
    public List<Shop> listMerchant(String userid) {
        ShopExample shopExample=new ShopExample();
        shopExample.createCriteria().andUseridEqualTo(userid).andIsDeleteEqualTo(0);
        shopExample.setOrderByClause("createtime desc");
        return shopMapper.selectByExample(shopExample);
    }

    @Override
    public void modifyShopAttribute(String id,Integer type,Integer sign) {
        Shop shop=shopMapper.selectByPrimaryKey(id);
        if(null==shop){
            throw new RuntimeException("商品不存在");
        }
        if(sign!=0&&sign!=1){
            throw new RuntimeException("sign参数错误");
        }
        if(type==1){
            shop.setIsdirectly(sign);
        }else if(type==2){
            shop.setIsselling(sign);
        }else {
            throw new RuntimeException("type参数错误");
        }
        shopMapper.updateByPrimaryKeySelective(shop);
    }

    @Override
    public List<MerchantVo> listmerchantvo() {
        return otherMapperExt.listmerchantvo();
    }

    @Override
    public void updateShopPrice(String shopid, Integer nodelever, String price) {
        if(nodelever==null){
            throw  new  RuntimeException("参数错误");
        }
        if(StringUtils.isEmpty(price)){
            throw  new  RuntimeException("参数错误");
        }
        ShopLeverExample admShopLeverExample=new ShopLeverExample();
        admShopLeverExample.createCriteria().andShopidEqualTo(shopid).andNodeleverEqualTo(nodelever);
        List<ShopLever>  admShopLeverList=shopLeverMapper.selectByExample(admShopLeverExample);
        if(admShopLeverList.size()<=0){
           // throw  new  RuntimeException("数据错误");
            ShopLever shopLever=new ShopLever();
            shopLever.setCreatetime(new Date());
            shopLever.setNodelever(nodelever);
            shopLever.setPrice(price);
            shopLever.setShopid(shopid);
            shopLeverMapper.insertSelective(shopLever);
        }else {
            ShopLever admShopLever=admShopLeverList.get(0);
            admShopLever.setPrice(price);
            shopLeverMapper.updateByPrimaryKeySelective(admShopLever);
        }

    }

    @Override
    public void updateByPrimaryKeySelective(Shop shop) {
        Shop admshop= shopMapper.selectByPrimaryKey(shop.getId());
        if(null==admshop){
            throw new RuntimeException("商品不存在");
        }
        shopMapper.updateByPrimaryKeySelective(shop);
    }

    @Override
    public Shop selectShop(String uid,String shopid) {
        Map map=new HashMap();
        map.put("uid",uid);
        map.put("shopid",shopid);
        return otherMapperExt.shopdetail(map);
    }

    @Override
    public Shop selectShop(String shopid) {
        return shopMapper.selectByPrimaryKey(shopid);
    }

    @Override
    public Shop selectShopDetail(String id) {
        return shopMapper.selectByPrimaryKey(id);
    }

    @Override
    public Shop selectShopDetail(String id, String uid) {
        User user=userMapper.selectByPrimaryKey(uid);
        if(null==user){
            throw new RuntimeException("用户不存在");
        }
        Map map=new HashMap();
        map.put("uid",uid);
        map.put("shopid",id);
        return otherMapperExt.appListNewShopDetail(map);
    }
}
