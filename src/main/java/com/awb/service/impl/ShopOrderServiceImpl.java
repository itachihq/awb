package com.awb.service.impl;

import com.awb.entity.param.ShoporderParam;
import com.awb.entity.vo.AppShopOrderVo;
import com.awb.entity.vo.PageResult;
import com.awb.entity.vo.ShopOrderVo;
import com.awb.entity.vo.ShopPriceVo;
import com.awb.mapper.*;
import com.awb.model.*;
import com.awb.service.ShopOrderService;
import com.awb.util.Coderutil;
import com.awb.util.DecimalUtil;
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
public class ShopOrderServiceImpl implements ShopOrderService {
    @Autowired
    private ShoporderMapper shoporderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminfoMapper adminfoMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private DealMapper dealMapper;
    @Autowired
    private  RecordMapper recordMapper;
    @Autowired
    private  OtherMapperExt otherMapperExt;
    @Override
    public void insertShoporder(Shoporder shoporder, String uid) {
        try {
            User user = userMapper.selectByPrimaryKey(uid);
            if (null == user) {
                throw new RuntimeException("用户不存在");
            }
            Shop shop = shopMapper.selectByPrimaryKey(shoporder.getShopid());
            if (null == shop) {
                throw new RuntimeException("商品不存在");
            }
            User merchant=userMapper.selectByPrimaryKey(shop.getUserid());
            if(null==merchant){
                throw new RuntimeException("商家不存在");
            }
            AdminfoExample adminfoExample = new AdminfoExample();
            adminfoExample.createCriteria();
            List<Adminfo> adminfoList = adminfoMapper.selectByExample(adminfoExample);
            if (adminfoList.size() <= 0) {
                throw new RuntimeException("艾豆米信息不存在");//adminfoList.get(0).getPrice()
            }
            Date date = new Date();
            String no = Coderutil.getAwbOrderNo("0258");
            Integer nm= DecimalUtil.calcNumber(user.getAdmnum(),shoporder.getTotalmoney(),"-").intValue();
            if(nm<0){
                throw new RuntimeException("艾豆米数量不足");
            }
            user.setAdmnum(nm);
            userMapper.updateByPrimaryKeySelective(user);
            shoporder.setPrice(adminfoList.get(0).getPrice());
            shoporder.setOrdernum(no);
            shoporder.setId(UuidUtil.get32UUID());
            shoporder.setUserid(user.getId());
            shoporder.setStatus(1);
            shoporder.setType(1);
            shoporder.setCreatetime(date);
            shoporderMapper.insertSelective(shoporder);
            Deal admdeal = new Deal();
            admdeal.setId(UuidUtil.get32UUID());
            admdeal.setType(14);
            admdeal.setNum(Integer.valueOf(shoporder.getTotalmoney()));
            admdeal.setCreatetime(date);
            admdeal.setUpdatetime(date);
            admdeal.setPrice(adminfoList.get(0).getPrice());
            admdeal.setUserid(user.getId());
            admdeal.setStatus(1);
            admdeal.setRemark(shoporder.getId());
            dealMapper.insertSelective(admdeal);
            String description = "";
            description = "您从商城购买了" + shoporder.getNum() + "套" + shop.getShopName() + ",花费" + Integer.valueOf(shoporder.getTotalmoney()) + "枚艾豆米";
            Record record=new Record();
            record.setId(UuidUtil.get32UUID());
            record.setUserid(user.getId());
            record.setNum(Integer.valueOf(shoporder.getTotalmoney()));
            record.setType(2);
            record.setDetailType(14);
            record.setBalance(user.getAdmnum()+"");
            record.setDescription(description);
            record.setCreatetime(date);
            record.setDealid(admdeal.getId());
            recordMapper.insertSelective(record);
            Integer merchantnum=DecimalUtil.calcNumber(merchant.getAdmnum(),shoporder.getTotalmoney(),"+").intValue();
            merchant.setAdmnum(merchantnum);
            userMapper.updateByPrimaryKeySelective(merchant);
            description="用户"+user.getPhone()+"购买了"+shoporder.getNum() + "套" + shop.getShopName()+",你获得"+shoporder.getTotalmoney()+"枚艾豆米";
            Record record1=new Record();
            record1.setId(UuidUtil.get32UUID());
            record1.setUserid(merchant.getId());
            record1.setNum(Integer.valueOf(shoporder.getTotalmoney()));
            record1.setType(1);
            record1.setDetailType(15);
            record1.setBalance(merchant.getAdmnum()+"");
            record1.setDescription(description);
            record1.setCreatetime(date);
            recordMapper.insertSelective(record1);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void insertNewShoporder(Shoporder shoporder, String uid) {
        try{
        User user = userMapper.selectByPrimaryKey(uid);
        if (null == user) {
            throw new RuntimeException("用户不存在");
        }
        Shop shop = shopMapper.selectByPrimaryKey(shoporder.getShopid());
        if (null == shop) {
            throw new RuntimeException("商品不存在");
        }
        AdminfoExample adminfoExample = new AdminfoExample();
        adminfoExample.createCriteria();
        List<Adminfo> adminfoList = adminfoMapper.selectByExample(adminfoExample);
        if (adminfoList.size() <= 0) {
            throw new RuntimeException("艾豆米信息不存在");//adminfoList.get(0).getPrice()
        }
        Date date = new Date();
        String no = Coderutil.getAwbOrderNo("0256");
        if(Double.valueOf(shoporder.getTotalmoney())<=0){
            throw new RuntimeException("金额错误");
        }
        shoporder.setOrdernum(no);
        shoporder.setId(UuidUtil.get32UUID());
        shoporder.setUserid(user.getId());
        shoporder.setStatus(0);shoporder.setPrice(adminfoList.get(0).getPrice());
        shoporder.setCreatetime(date);
        shoporderMapper.insertSelective(shoporder);

    } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
    }
    }

    @Override
    public List<AppShopOrderVo> selectmyorder(String uid, Integer status,Integer type) {
        User user = userMapper.selectByPrimaryKey(uid);
        if (null == user) {
            throw new RuntimeException("用户不存在");
        }
        Map map=new HashMap();
        map.put("userid",uid);
        map.put("status",status);
        map.put("type",type);
        return  otherMapperExt.selectmyorder(map);
    }

    @Override
    public List<ShopOrderVo> selectAllShopOrder(Integer status) {
        Map map=new HashMap();
        map.put("status",status);
        return null;// otherMapperExt.selectAllShopOrder(map);
    }

    @Override
    public PageResult<ShopOrderVo> selectAllShopOrder(ShoporderParam shoporderParam) {
        PageHelper.startPage(shoporderParam.getCurrentPage(),shoporderParam.getPageSize());
        List<ShopOrderVo> list=otherMapperExt.selectAllShopOrder(shoporderParam);
        PageInfo<ShopOrderVo> pageInfo = new PageInfo<ShopOrderVo>(list);
        return new PageResult(pageInfo.getTotal(),list,shoporderParam.getCurrentPage()) ;
    }

    @Override
    public void shoprder(Shoporder shoporder) {
        shoporderMapper.updateByPrimaryKeySelective(shoporder);
    }

    @Override
    public List<ShopPriceVo> selectShopPrice(String shopid) {
        return otherMapperExt.selectShopPrice(shopid);
    }

    @Override
    public void  WxCallBack(String orderno, String transid, String dealtime, String money) {
        try{
            ShoporderExample shoporderExample=new ShoporderExample();
            shoporderExample.createCriteria().andOrdernumEqualTo(orderno);
            List<Shoporder> shoporderList=shoporderMapper.selectByExample(shoporderExample);
            if(shoporderList.size()<=0){
                throw new RuntimeException("订单不存在");
            }
            Shoporder shoporder=shoporderList.get(0);
            if(shoporder.getStatus()!=0){
                return;
            }
            shoporder.setTranid(transid);
            shoporder.setStatus(1);
            shoporder.setDealime(dealtime);
            shoporder.setUpdatetime(new Date());
            shoporder.setMoney(money);
            shoporderMapper.updateByPrimaryKeySelective(shoporder);
           Shop shop=  shopMapper.selectByPrimaryKey(shoporder.getShopid());
           if(null==shop){
               throw new RuntimeException("商品不存在");
           }
           if(shop.getIsdirectly()==1){
               return;
           }
            User user=  userMapper.selectByPrimaryKey(shoporder.getUserid());
            if(null==user){
                throw new RuntimeException("用户不存在");
            }
            int sendNum1=DecimalUtil.div2("399",shoporder.getPrice()).intValue();
            int sendNum2=DecimalUtil.calcNumber(sendNum1,shoporder.getNum(),"*").intValue();
            Date date=new Date();
            Deal deal=new Deal();
            deal.setId(UuidUtil.get32UUID());
            deal.setCreatetime(date);
            deal.setUserid(user.getId());
            deal.setPrice(shoporder.getPrice());
            deal.setUpdatetime(date);
            deal.setPhone("18000000001");
            deal.setNum(sendNum2);
            deal.setType(1);
            deal.setStatus(1);
            deal.setRemark("399");
            dealMapper.insertSelective(deal);
            String description="";
            description="购买成功，获得"+sendNum2+"枚艾豆米";
            Record record=new Record();
            record.setId(UuidUtil.get32UUID());
            record.setUserid(user.getId());
            record.setType(1);
            record.setDetailType(1);
            record.setBalance(user.getMoney()+"");
            record.setDescription(description);
            record.setNum(sendNum2);
            record.setCreatetime(date);
            record.setDealid(deal.getId());
            recordMapper.insertSelective(record);
            int newNum=     DecimalUtil.calcNumber(user.getAdmnum(),sendNum2,"+").intValue();
            double newMoney=DecimalUtil.calcNumber(user.getTotalmoney(),shoporder.getTotalmoney(),"+").doubleValue();
            int sendTimes=DecimalUtil.calcNumber(shoporder.getNum(),3,"*").intValue();
            int num=DecimalUtil.calcNumber(user.getCoupontimes(),sendTimes,"+").intValue();
            user.setMoney(newMoney);
            user.setAdmnum(newNum);
            user.setCoupontimes(num);
            if(0==user.getFlg()&&10==user.getNodelever()){
                user.setFlg(1);
            }
            userMapper.updateByPrimaryKeySelective(user);
            Reward(user,shoporder.getTotalmoney(),deal,date);
//            if(null!=shoporder.getProvince()) {
//                AreaReward(shoporder);
//            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    public void Reward(User user,String shouldmoney,Deal deal,Date date) {
        try {
            if(StringUtils.isEmpty(user.getParentid())){
                return;
            }
            if("1".equals(user.getParentid())||"d91ed53e9200495c9112132ced069603".equals(user.getParentid())){
                return;
            }
            User u1=userMapper.selectByPrimaryKey(user.getParentid());
            String description ="";
            if(null==u1){
                return;
            }else {
                if(u1.getNodelever()==10&&u1.getFlg()!=1){

                }else {
                    Double addMoney=DecimalUtil.calcNumber(shouldmoney,0.15,"*",2).doubleValue();
                    String u1oldbalance=  u1.getBalance()+"";
                    String newBalance1=DecimalUtil.calcNumber(u1.getBalance(),addMoney,"+").toString();
                    //  String newMoney1="";//DecimalUtil.calcNumber(oldMoney2,addMoney2,"+").toString();
                    u1.setBalance(Double.valueOf(newBalance1));
                    userMapper.updateByPrimaryKeySelective(u1);
                    description=  "您的下级购买上商品，您获得一级返利"+addMoney+"元";
                    Record record1=new Record();
                    record1.setId(UuidUtil.get32UUID());
                    record1.setAmt(addMoney+"");
                    record1.setBalance(u1.getBalance()+"");
                    record1.setNum(deal.getNum());
                    record1.setCreatetime(date);
                    record1.setUserid(u1.getId());
                    record1.setDealid(deal.getId());
                    record1.setType(1);
                    record1.setDetailType(6);
                    record1.setDescription(description);
                    record1.setIsDelete(0);
                    recordMapper.insertSelective(record1);
                }

            }

            if(StringUtils.isEmpty(u1.getParentid())){
                return;
            }
            if("1".equals(u1.getParentid())||"d91ed53e9200495c9112132ced069603".equals(u1.getParentid())){
                return;
            }
            User u2=userMapper.selectByPrimaryKey(u1.getParentid());
            if(null==u2){
                return;
            }else {
                //   String oldMoney2=u2.getTotalmoney();
                if(u2.getNodelever()==10&&u2.getFlg()!=1){
                    return;
                }
                Double addMoney2=DecimalUtil.calcNumber(shouldmoney,0.05,"*",2).doubleValue();
                String u2oldbalance=  u2.getBalance()+"";
                String newBalance2=DecimalUtil.calcNumber(u2.getBalance(),addMoney2,"+").toString();
                String newMoney2="";//DecimalUtil.calcNumber(oldMoney2,addMoney2,"+").toString();
                u2.setBalance(Double.valueOf(newBalance2));
                userMapper.updateByPrimaryKeySelective(u2);
                description=  "您的下下级购买上商品，您获得二级返利"+addMoney2+"元";
                Record record2=new Record();
                record2.setId(UuidUtil.get32UUID());
                record2.setAmt(addMoney2+"");
                record2.setBalance(newBalance2);
                record2.setCreatetime(date);
                record2.setOldbalance(u2oldbalance);
                record2.setUserid(u2.getId());
                record2.setDealid(deal.getId());
                record2.setType(1);
                record2.setDetailType(7);
                record2.setDescription(description);
                recordMapper.insertSelective(record2);
            }

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void AreaReward(Shoporder shoporder){
    try {
        List<User> userList=otherMapperExt.listUserArea(shoporder.getProvince()+shoporder.getCity()+shoporder.getArea());
        Date date=new Date();
        if(userList.size()<=3){
            for (User user:userList){
                int addMoney=DecimalUtil.calcNumber(10,shoporder.getNum(),"*").intValue();
             Double newMoney=   DecimalUtil.calcNumber(user.getBalance(),addMoney,"+").doubleValue();
             user.setBalance(newMoney);
             userMapper.updateByPrimaryKeySelective(user);
             String    description=  "您所代理区域会员购买399套餐获得返利"+addMoney+"元";
                Record record1=new Record();
                record1.setId(UuidUtil.get32UUID());
                record1.setAmt(addMoney+"");
                record1.setBalance(user.getBalance()+"");
                record1.setCreatetime(date);
                record1.setUserid(user.getId());
               // record1.setDealid(deal.getId());
                record1.setType(1);
                record1.setDetailType(17);
                record1.setDescription(description);
                record1.setIsDelete(0);
                recordMapper.insertSelective(record1);
            }
        }
    }catch (Exception e){
        throw new RuntimeException(e.getMessage());
    }
    }
}
