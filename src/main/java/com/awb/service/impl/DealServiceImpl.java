package com.awb.service.impl;

import com.awb.entity.param.RecordParam;
import com.awb.entity.vo.*;
import com.awb.mapper.*;
import com.awb.model.*;
import com.awb.service.DealService;
import com.awb.util.DecimalUtil;
import com.awb.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/8/23.
 */
@Service
@Transactional
public class DealServiceImpl implements DealService {
    @Autowired
    private DealMapper dealMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminfoMapper adminfoMapper;
    @Autowired
    private OtherMapperExt otherMapperExt;
    @Autowired
    private MoneyrecordMapper moneyrecordMapper;
    @Override
    public synchronized void agentBuy(String uid, Integer num, String phone) {
        try {
            User user=userMapper.selectByPrimaryKey(uid);
            if(null==user){
                throw new RuntimeException("用户不存在");
            }
            UserExample admuserExample=new UserExample();
            admuserExample.createCriteria().andPhoneEqualTo(phone);
            List<User> a1=  userMapper.selectByExample(admuserExample);
            if(a1.size()<=0){
                throw new RuntimeException("买入用户不存在");
            }
            User buyer=a1.get(0);
            if(user.getPhone().equals(buyer.getPhone())){
                throw new RuntimeException("买家和卖家不能为同一个人");
            }
            if(user.getAdmnum()<num){
                throw new RuntimeException("数量不足");
            }
            AdminfoExample adminfoExample=new AdminfoExample();
            adminfoExample.createCriteria();
            List<Adminfo>  adminfoList= adminfoMapper.selectByExample(adminfoExample);
            if(adminfoList.size()<=0){
                throw new RuntimeException("艾豆米信息不存在");
            }
            Integer sellnum=DecimalUtil.calcNumber(user.getAdmnum(),num,"-").intValue();
            Integer buynum=DecimalUtil.calcNumber(buyer.getAdmnum(),num,"+").intValue();
            user.setAdmnum(sellnum);
            buyer.setAdmnum(buynum);
            userMapper.updateByPrimaryKeySelective(user);
            userMapper.updateByPrimaryKeySelective(buyer);
            Date date=new Date();
            Record record=new Record();
            Deal deal=new Deal();
            deal.setId(UuidUtil.get32UUID());
            deal.setCreatetime(date);
            deal.setUserid(uid);
            deal.setPhone(phone);
            deal.setPrice(adminfoList.get(0).getPrice());
            deal.setNum(num);
            deal.setType(2);
            deal.setStatus(1);
            deal.setUpdatetime(date);
            dealMapper.insertSelective(deal);

            String sendMoney= DecimalUtil.calcNumber(adminfoList.get(0).getPrice(),num,"*",2).toString();
//            String poundage=DecimalUtil.calcNumber(sendMoney,0.08,"*",2).toString();
//            String surMoney=DecimalUtil.calcNumber(sendMoney,poundage,"-",2).toString();
            String description="";
            description="您以单价"+adminfoList.get(0).getPrice()+"卖给"+buyer.getPhone()+"用户"+num+"枚艾豆米";//获得"+sendMoney+"元";扣除手续费"+poundage+"元后，，获得"+sendMoney+"元
            Record record1=new Record();
            record1.setId(UuidUtil.get32UUID());
            record1.setCreatetime(date);
            record1.setDescription(description);
            record1.setType(2);
            record1.setDetailType(8);
            record1.setUserid(uid);
            record1.setBalance(user.getBalance()+"");
            record1.setAmt(sendMoney);
            record1.setNum(num);
            record1.setDealid(deal.getId());
            recordMapper.insertSelective(record1);
            description="您从"+user.getPhone()+"买入"+num+"枚艾豆米";
            record.setId(UuidUtil.get32UUID());
            record.setDealid(deal.getId());
            record.setNum(num);
            record.setCreatetime(date);
            record.setAmt(sendMoney);
            record.setUserid(buyer.getId());
            record.setBalance(buyer.getBalance()+"");
            record.setType(2);
            record.setDetailType(2);
            record.setDescription(description);
            recordMapper.insertSelective(record);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TransferVo> listTransfer(String uid) {
        return otherMapperExt.listTransfer(uid);
    }

    @Override
    public synchronized void grailSell(String uid, Integer num) {
        try {
            User user=userMapper.selectByPrimaryKey(uid);
            if(null==user){
                throw new RuntimeException("用户不存在");
            }
            if(10==user.getNodelever()){
                throw new RuntimeException("会员不能卖出");
            }
            if(user.getAdmnum()<num){
                throw new RuntimeException("数量不足");
            }
                List<Deal> mayDeal=otherMapperExt.selectmonthdeal(uid);
            if(mayDeal.size()>0){
                throw new RuntimeException("30天内最多只能挂卖一次");
            }
                Deal deal1=   otherMapperExt.selectAdmNum(uid);
                int maynum=DecimalUtil.calcNumber(user.getAdmnum(),deal1.getNum(),"-").intValue();
//                if(maynum<0){
//                    throw new RuntimeException("数据错误");
//                }
            if(maynum<0){
                maynum=0;
            }
            if(maynum>10000){
                maynum=DecimalUtil.div4(maynum+"","5").intValue();
            }
                if(num>maynum){
                    throw new RuntimeException("超过最大可允许卖出个数"+maynum);
                }

            AdminfoExample adminfoExample=new AdminfoExample();
            adminfoExample.createCriteria();
            List<Adminfo>  adminfoList= adminfoMapper.selectByExample(adminfoExample);
            if(adminfoList.size()<=0){
                throw new RuntimeException("艾豆米信息不存在");
            }
            DealExample ad=new DealExample();
            ad.createCriteria().andUseridEqualTo(user.getId()).andStatusEqualTo(0).andTypeEqualTo(4);
            List<Deal> l1= dealMapper.selectByExample(ad);
            if(l1.size()>0){
                throw new RuntimeException("有大盘订单未交易成功");
            }
            Deal deal=new Deal();
            deal.setId(UuidUtil.get32UUID());
            deal.setCreatetime(new Date());
            deal.setUserid(uid);
            deal.setPrice(adminfoList.get(0).getPrice());
            deal.setNum(num);
            deal.setType(4);
            dealMapper.insertSelective(deal);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Deal> listMyDeal(String uid, Integer status) {
        User user=userMapper.selectByPrimaryKey(uid);
        if(null==user){
            throw new RuntimeException("用户不存在");
        }
        if(0!=status&&1!=status){
            throw new RuntimeException("status错误");
        }
        DealExample dealExample=new DealExample();
        dealExample.createCriteria().andUseridEqualTo(uid).andStatusEqualTo(status).andTypeEqualTo(4);
        if(0==status){
            dealExample.setOrderByClause("createtime desc");
        }else {
            dealExample.setOrderByClause("updatetime desc");
        }

        return dealMapper.selectByExample(dealExample);
    }

    @Override
    public List<DealVo> liseDaPanDeal() {
        return otherMapperExt.liseDaPanDeal();
    }
    @Override
    public synchronized void seil(String id, Integer num, String phone) {
        try{
            Deal admdeal=dealMapper.selectByPrimaryKey(id);
            if(null==admdeal){
                throw new RuntimeException("记录不存在");
            }
            User user=userMapper.selectByPrimaryKey(admdeal.getUserid());
            if(user==null){
                throw new RuntimeException("卖家不存在");
            }
            UserExample admuserExample=new UserExample();
            admuserExample.createCriteria().andPhoneEqualTo(phone);
            List<User> admuserList=userMapper.selectByExample(admuserExample);
            if(admuserList.size()<=0){
                throw new RuntimeException("买家不存在");
            }
            User admuser=admuserList.get(0);
            AdminfoExample adminfoExample=new AdminfoExample();
            adminfoExample.createCriteria();
            List<Adminfo>  adminfoList= adminfoMapper.selectByExample(adminfoExample);
            if(adminfoList.size()<=0){
                throw new RuntimeException("艾豆米信息不存在");
            }
            String price=adminfoList.get(0).getPrice();
            if(num>user.getAdmnum()){
                throw new RuntimeException("卖家艾豆米数量不足");
            }
            if(num>admdeal.getNum()){
                throw new RuntimeException("num参数错误");
            }
            Date date=new Date();
            String description="";
            // if(num<admdeal.getNum()){
            //买家新增数量 u1
            String buyMoney=DecimalUtil.calcNumber(price,num,"*",2).toString();
            String buyBalance=admuser.getBalance()+"";
            Double newMoney=DecimalUtil.calcNumber(admuser.getBalance(),buyMoney,"-",2).doubleValue();
            if(newMoney<0){
                throw new RuntimeException("买家余额不足");
            }
            //修改买家数量 余额
            admuser.setBalance(newMoney);
            Integer newNum=DecimalUtil.calcNumber(admuser.getAdmnum(),num,"+").intValue();
            admuser.setAdmnum(newNum);
            userMapper.updateByPrimaryKeySelective(admuser);
            Double surl=DecimalUtil.calcNumber(admdeal.getNum(),num,"-").doubleValue();
            if(surl<=0){
                //修改卖家记录
                admdeal.setPhone(admuser.getPhone());
                admdeal.setUpdatetime(date);
                admdeal.setStatus(1);
              dealMapper.updateByPrimaryKeySelective(admdeal);

            }else {
                //修改卖家记录
                admdeal.setNum(admdeal.getNum()-num);
                dealMapper.updateByPrimaryKeySelective(admdeal);
                //生成卖家已交易订单
                Deal seil=new Deal();
                seil.setId(UuidUtil.get32UUID());
                seil.setType(4);
                seil.setCreatetime(date);
                seil.setUpdatetime(date);
                seil.setNum(num);
                seil.setPhone(admuser.getPhone());
                seil.setUserid(user.getId());
                seil.setPrice(price);
                seil.setStatus(1);
                dealMapper.insertSelective(seil);
            }

            //新增买家记录
            Deal dea=new Deal();
            dea.setId(UuidUtil.get32UUID());
            dea.setType(3);
            dea.setCreatetime(date);
            dea.setUpdatetime(date);
            dea.setNum(num);
            dea.setPhone(user.getPhone());
            dea.setUserid(admuser.getId());
            dea.setPrice(price);
            dea.setStatus(1);
            dealMapper.insertSelective(dea);
            //修改卖家账户
            String sendBalance=user.getBalance()+"";
            //add 903
            String poundage=DecimalUtil.calcNumber(buyMoney,0,"*",2).toString();
            String surMoney=DecimalUtil.calcNumber(buyMoney,poundage,"-",2).toString();
            Double newSendMoney=DecimalUtil.calcNumber(user.getBalance(),surMoney,"+",2).doubleValue();
            user.setBalance(newSendMoney);
            Integer   buyNum=  DecimalUtil.calcNumber(user.getAdmnum(),num,"-").intValue();
            user.setAdmnum(buyNum);
            userMapper.updateByPrimaryKeySelective(user);
            description="您从"+user.getPhone()+"买入"+num+"枚艾豆米扣除余额"+buyMoney+"元";
            Record record=new Record();
            record.setId(UuidUtil.get32UUID());
            record.setUserid(admuser.getId());
            record.setNum(num);
            record.setOldbalance(buyBalance);
            record.setType(2);
            record.setDetailType(3);
            record.setAmt(buyMoney);
            record.setBalance(admuser.getBalance()+"");
            record.setDescription(description);
            record.setDealid(admdeal.getId());
            record.setCreatetime(date);
           // record.setRemark(admdeal.getId());
            recordMapper.insertSelective(record);
            description="您以单价"+price+"配出"+num+"枚艾豆米获得"+buyMoney+"元";//卖出     description="您以单价"+price+"卖给"+admuser.getPhone()+"用户"+num+"枚艾豆米，获得"+buyMoney+"元";给"+admuser.getPhone()+"用户  ，扣除手续费"+poundage+"元后，
            Record admrecord=new Record();
            admrecord.setId(UuidUtil.get32UUID());
            admrecord.setCreatetime(date);
            admrecord.setUserid(user.getId());
            admrecord.setAmt(surMoney);
            admrecord.setBalance(newSendMoney+"");
            admrecord.setOldbalance(sendBalance);
            admrecord.setDescription(description);
            admrecord.setNum(num);
            admrecord.setDealid(admdeal.getId());
           // admrecord.setRemark(admdeal.getId());
            admrecord.setType(1);
            admrecord.setDetailType(4);
            recordMapper.insertSelective(admrecord);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void saveCompanyDeal(String uid, Integer num, Moneyrecord moneyrecord) {
        User user=userMapper.selectByPrimaryKey(uid);
        if(null==user){
            throw new RuntimeException("用户不存在");
        }
        if(num<=0){
            throw new RuntimeException("数量错误");
        }
        AdminfoExample adminfoExample=new AdminfoExample();
        adminfoExample.createCriteria();
        List<Adminfo>  adminfoList= adminfoMapper.selectByExample(adminfoExample);
        if(adminfoList.size()<=0){
            throw new RuntimeException("艾豆米信息不存在");
        }
        Date date=new Date();
        Deal deal=new Deal();
        deal.setId(UuidUtil.get32UUID());
        deal.setCreatetime(date);
        deal.setUserid(uid);
        deal.setPrice(adminfoList.get(0).getPrice());
        deal.setPhone("18000000001");
        deal.setNum(num);
        deal.setType(1);
        dealMapper.insertSelective(deal);
        moneyrecord.setId(UuidUtil.get32UUID());
        moneyrecord.setCreatetime(date);
        moneyrecord.setDealid(deal.getId());
        moneyrecordMapper.insertSelective(moneyrecord);
    }

    @Override
    public synchronized void checkDeal(String  id,Integer status, String remark,Double realmoney,Double shouldmoney) {
        try{
            Deal admdeal=dealMapper.selectByPrimaryKey(id);
            if(null==admdeal){
                throw new RuntimeException("记录不存在");
            }
            if(admdeal.getType()!=1){
                throw new RuntimeException("记录不允许审核");
            }
            User admuser= userMapper.selectByPrimaryKey(admdeal.getUserid());
            if(StringUtils.isEmpty(admuser)){
                throw new RuntimeException("用户不存在");
            }
            if(0!=admdeal.getStatus()){
                throw new RuntimeException("记录已审核");
            }
            if(realmoney<=0||shouldmoney<=0){
                throw new RuntimeException("金额错误");
            }
            MoneyrecordExample moneyrecordExample=new MoneyrecordExample();
            moneyrecordExample.createCriteria().andDealidEqualTo(id);
            List<Moneyrecord> moneyrecordList= moneyrecordMapper.selectByExample(moneyrecordExample);
            if(moneyrecordList.size()<=0){
                throw new RuntimeException("数据错误");
            }
            Date date=new Date();
            admdeal.setStatus(status);
            admdeal.setUpdatetime(date);
            admdeal.setRemark(remark);
            dealMapper.updateByPrimaryKeySelective(admdeal);
            if(status!=1){
                return;
            }
            Moneyrecord moneyrecord=moneyrecordList.get(0);
            moneyrecord.setRealmoney(realmoney);
            moneyrecord.setShouldmoney(shouldmoney);
            moneyrecord.setUpdatetime(date);
            moneyrecordMapper.updateByPrimaryKeySelective(moneyrecord);
            Integer sendnum=0;//DecimalUtil.calcNumber(admdeal.getNum(),0.01,"*").intValue();
            Integer newNum=DecimalUtil.calcNumber(admuser.getAdmnum(),admdeal.getNum(),"+").intValue();
            newNum=DecimalUtil.calcNumber(newNum,sendnum,"+").intValue();
            String reduceMoney=DecimalUtil.calcNumber(admdeal.getNum(),admdeal.getPrice(),"*",2).toString();
            String newMoney=DecimalUtil.calcNumber(admuser.getMoney(),reduceMoney,"+",2).toString();
            //add 905
            Integer newTotalnum=DecimalUtil.calcNumber(admuser.getTotalnum(),admdeal.getNum(),"+").intValue();
            newTotalnum=DecimalUtil.calcNumber(newTotalnum,sendnum,"+").intValue();
            String newTotalmoney=DecimalUtil.calcNumber(admuser.getTotalmoney(),reduceMoney,"+",2).toString();
            admuser.setMoney(Double.valueOf(newMoney));
            admuser.setAdmnum(newNum);
            admuser.setTotalmoney(Double.valueOf(newTotalmoney));
            admuser.setTotalnum(newTotalnum);
            String description="";
            description="您从公司买入"+admdeal.getNum()+"枚艾豆米";//,并且公司赠送"+sendnum+"枚";
            Record record=new Record();
            record.setId(UuidUtil.get32UUID());
            record.setUserid(admuser.getId());
            record.setNum(admdeal.getNum());
            record.setType(1);
            record.setDetailType(1);
            record.setAmt(shouldmoney+"");
            record.setBalance(admuser.getMoney()+"");
            record.setSendnum(sendnum);
            record.setDescription(description);
            record.setCreatetime(date);
            record.setDealid(admdeal.getId());
            recordMapper.insertSelective(record);
           userMapper.updateByPrimaryKeySelective(admuser);
            Reward(admuser,realmoney,shouldmoney,admdeal,date);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<DealRecordVo> listDealRecordSuccess() {
        return otherMapperExt.listDealRecordSuccess();
    }

    @Override
    public List<DealVo> listBuyCompanyDeal() {
        return otherMapperExt.listBuyCompanyDeal();
    }

    @Override
    public List<DealVo> listDeal() {
        return otherMapperExt.listDeal();
    }

    @Override
    public void deleteGuamai(String id) {
        Deal admdeal=dealMapper.selectByPrimaryKey(id);
        if(null==admdeal){
            throw new RuntimeException("记录不存在");
        }
        if(admdeal.getType()!=4){
            throw new RuntimeException("撤销失败");
        }
        dealMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer sellMax(String uid) {
        return null;
    }

    @Override
    public PageResult<AdminRecordVo> appListShop(RecordParam recordParam) {
        PageHelper.startPage(recordParam.getCurrentPage(),recordParam.getPageSize());
        List<AdminRecordVo> list=otherMapperExt.listadminrecord(recordParam);
        PageInfo<AdminRecordVo> pageInfo = new PageInfo<AdminRecordVo>(list);
        return new PageResult(pageInfo.getTotal(),list,recordParam.getCurrentPage()) ;
    }

    public void Reward(User user,Double realmoney,Double shouldmoney,Deal deal,Date date) {
        try {
            if(StringUtils.isEmpty(user.getParentid())){
                return;
            }
            if("1".equals(user.getParentid())){
                return;
            }
            User u1=userMapper.selectByPrimaryKey(user.getParentid());
            if(null==u1){
                return;
            }
            String description ="";
            Double addMoney=DecimalUtil.calcNumber(shouldmoney,0.15,"*",2).doubleValue();
            description=  "您的下级"+user.getPhone()+"购买了"+deal.getNum()+"枚艾豆米，您获得一级返利"+addMoney+"元";
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
            record1.setIsDelete(1);
            recordMapper.insertSelective(record1);

            if(StringUtils.isEmpty(u1.getParentid())){
                return;
            }
            if("1".equals(u1.getParentid())){
                return;
            }
            User u2=userMapper.selectByPrimaryKey(u1.getParentid());
            if(null==u2){
                return;
            }

         //   String oldMoney2=u2.getTotalmoney();
        Double addMoney2=DecimalUtil.calcNumber(shouldmoney,0.05,"*",2).doubleValue();
             String u2oldbalance=  u2.getBalance()+"";
            String newBalance2=DecimalUtil.calcNumber(u2.getBalance(),addMoney2,"+").toString();
            String newMoney2="";//DecimalUtil.calcNumber(oldMoney2,addMoney2,"+").toString();
         u2.setBalance(Double.valueOf(newBalance2));
            userMapper.updateByPrimaryKeySelective(u2);
            description=  "您的下下级"+user.getPhone()+"购买了"+deal.getNum()+"枚艾豆米，您获得二级返利"+addMoney2+"元";
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

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
