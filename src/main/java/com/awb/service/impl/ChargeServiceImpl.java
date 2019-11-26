package com.awb.service.impl;

import com.awb.entity.param.ChargeOrderParm;
import com.awb.entity.vo.ChargeOrderVo;
import com.awb.entity.vo.PageResult;
import com.awb.mapper.ChargeOrderMapper;
import com.awb.mapper.OtherMapperExt;
import com.awb.mapper.RecordMapper;
import com.awb.mapper.UserMapper;
import com.awb.model.*;
import com.awb.service.ChargeService;
import com.awb.util.DecimalUtil;
import com.awb.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/7/2.
 */
@Service
@Transactional
public class ChargeServiceImpl implements ChargeService {
    @Autowired
    private ChargeOrderMapper chargeOrderMapper;
    @Autowired
    private UserMapper admuserMapper;

    @Autowired
    private OtherMapperExt otherMapperExt;
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public void addChargeOrder(ChargeOrder chargeOrder, String uid) {
        try {
            User sysUser=admuserMapper.selectByPrimaryKey(uid);
            if(StringUtils.isEmpty(sysUser)){
                throw  new  RuntimeException("用户不存在");
            }
            Integer price= DecimalUtil.calcNumber(chargeOrder.getMoney(),100,"*").intValue();
            if(price<=0){
                throw  new  RuntimeException("金额错误");
            }
            Double sulMoney=DecimalUtil.calcNumber(sysUser.getBalance(),chargeOrder.getMoney(),"-").doubleValue();
                if(sulMoney<0){
                    throw  new  RuntimeException("可提现余额不足");
                }
                ChargeOrderExample chargeOrderExample=new ChargeOrderExample();
                chargeOrderExample.createCriteria().andUseridEqualTo(sysUser.getId()).andStatusEqualTo(0);
                List<ChargeOrder> chargeOrderList= chargeOrderMapper.selectByExample(chargeOrderExample);
                if(chargeOrderList.size()>0){
                    throw  new  RuntimeException("请等待上一个提现订单审核结束再提交新的订单!");
                }

            chargeOrder.setCreateTime(new Date());
            chargeOrder.setId(UuidUtil.get32UUID());
            chargeOrder.setUserid(uid);
            chargeOrderMapper.insertSelective(chargeOrder);
        } catch (Exception e) {
            throw  new  RuntimeException(e.getMessage());
        }
    }

    @Override
    public PageResult<ChargeOrderVo> listChargeOrder(ChargeOrderParm chargeOrderParm) {
        PageHelper.startPage(chargeOrderParm.getCurrentPage(),chargeOrderParm.getPageSize());
        List<ChargeOrderVo> list=otherMapperExt.selectAllChargeOrder(chargeOrderParm);
        PageInfo<ChargeOrderVo> pageInfo = new PageInfo<ChargeOrderVo>(list);
        return new PageResult(pageInfo.getTotal(),list,chargeOrderParm.getCurrentPage()) ;
    }

    @Override
    public synchronized void check(String id, Integer status, String message) {
        ChargeOrder chargeOrder=chargeOrderMapper.selectByPrimaryKey(id);
        if(null==chargeOrder){
            throw  new  RuntimeException("订单不存在");
        }
        User sysUser=admuserMapper.selectByPrimaryKey(chargeOrder.getUserid());
        if(StringUtils.isEmpty(sysUser)){
            throw  new  RuntimeException("用户不存在");
        }
        Date date=new Date();
        chargeOrder.setStatus(status);
        String description= "";
        try {
            if(2==status){
                chargeOrder.setFailMessage(message);
                chargeOrder.setUpdateTime(date);
                chargeOrderMapper.updateByPrimaryKeySelective(chargeOrder);

            }else if(1==status){
                Double newBalance=0.0;
                    newBalance=DecimalUtil.calcNumber(sysUser.getBalance(),chargeOrder.getMoney(),"-").doubleValue();
                    if(newBalance<0){
                        throw  new  RuntimeException("余额不足");// throw  new  RuntimeException("余额不足");
                    }

                sysUser.setBalance(newBalance);
                admuserMapper.updateByPrimaryKeySelective(sysUser);
                chargeOrder.setUpdateTime(date);
                chargeOrderMapper.updateByPrimaryKeySelective(chargeOrder);
                description="您成功提现"+chargeOrder.getMoney()+"元";
                Record admrecord=new Record();
                admrecord.setId(UuidUtil.get32UUID());
                admrecord.setCreatetime(date);
                admrecord.setAmt(chargeOrder.getMoney());
                admrecord.setUserid(sysUser.getId());
                admrecord.setDescription(description);
                admrecord.setType(2);
                admrecord.setDetailType(5);
                admrecord.setBalance(sysUser.getBalance()+"");
               recordMapper.insertSelective(admrecord);

            }else{
                throw  new  RuntimeException("参数错误");
            }
        }catch (Exception e){
            throw  new  RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ChargeOrder> listMyChargeOrder(String uid, Integer status) {
        User sysUser=admuserMapper.selectByPrimaryKey(uid);
        if(StringUtils.isEmpty(sysUser)){
            throw  new  RuntimeException("用户不存在");
        }
        ChargeOrderExample chargeOrderExample=new ChargeOrderExample();
        if(null==status){
            chargeOrderExample.createCriteria().andUseridEqualTo(uid);
        }else {
            chargeOrderExample.createCriteria().andUseridEqualTo(uid).andStatusEqualTo(status);
        }
       chargeOrderExample.setOrderByClause("create_time desc");
        return chargeOrderMapper.selectByExample(chargeOrderExample);
    }
}
