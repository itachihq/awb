package com.awb.service.impl;

import com.awb.component.ICache;
import com.awb.entity.param.InformationParam;
import com.awb.entity.param.UserParam;
import com.awb.entity.vo.AdminUserVo;
import com.awb.entity.vo.PageResult;
import com.awb.entity.vo.TreeVO;
import com.awb.mapper.*;
import com.awb.model.*;
import com.awb.service.UserService;
import com.awb.util.DESEncrypt;
import com.awb.util.DecimalUtil;
import com.awb.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/8/22.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ICache iCache ;
    @Autowired
    private OtherMapperExt otherMapperExt;
    @Autowired
    private MonthrecordMapper monthrecordMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Override
    public void update(String id, String remark) {

    }

    @Override
    public PageResult<AdminUserVo> listAllSysuser(UserParam userParam) {
        PageHelper.startPage(userParam.getCurrentPage(),userParam.getPageSize());
        List<AdminUserVo> list=otherMapperExt.listAllSysuser(userParam);
        PageInfo<AdminUserVo> pageInfo = new PageInfo<AdminUserVo>(list);
        return new PageResult(pageInfo.getTotal(),list,userParam.getCurrentPage()) ;
    }

    @Override
    public void updateTradePassword(String uid, String oldTradePassword, String tradePassword) {
        User admuser= userMapper.selectByPrimaryKey(uid);
        if(StringUtils.isEmpty(admuser)){
            throw new RuntimeException("用户不存在");
        }
        if(!admuser.getPassword().equals(DESEncrypt.EncodePassword(oldTradePassword))){
            throw new RuntimeException("原密码错误");
        }
        if(tradePassword.equals(oldTradePassword)){
            throw new RuntimeException("新密码和原密码不能一致");
        }
        admuser.setTradePassword(DESEncrypt.EncodePassword(tradePassword));
        userMapper.updateByPrimaryKeySelective(admuser);
    }

    @Override
    public void updateIdentitycard(String uid, String identitycard) {
        User user= userMapper.selectByPrimaryKey(uid);
        if(StringUtils.isEmpty(user)){
            throw new RuntimeException("用户不存在");
        }
        user.setIdentitycard(identitycard);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updatePassword(String phone, String password,String code) {
        String co=    iCache.getString("forcode_"+phone);
        if(!code.equals(co)){
            throw new RuntimeException("验证码错误");
        }
        UserExample userExample=new UserExample();
        userExample.createCriteria().andPhoneEqualTo(phone);
        List<User> userList=userMapper.selectByExample(userExample);
        if(userList.size()<=0){
            throw new RuntimeException("用户不存在");
        }
        User user= userList.get(0);

        if(user.getPassword().equals(DESEncrypt.EncodePassword(password))){
            throw new RuntimeException("新密码和原密码不能一致");
        }
        user.setPassword(DESEncrypt.EncodePassword(password));
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updateNodelever(String id, Integer nodelever) {
        User user= userMapper.selectByPrimaryKey(id);
        if(StringUtils.isEmpty(user)){
            throw new RuntimeException("用户不存在");
        }
        user.setNodelever(nodelever);
        user.setStatus(1);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void update(String id, String remmark,String name,  Integer num) {
         try {
             User user= userMapper.selectByPrimaryKey(id);
             if(StringUtils.isEmpty(user)){
                 throw new RuntimeException("用户不存在");
             }
             Integer oldnum=user.getAdmnum();
             Integer newnum= DecimalUtil.calcNumber(oldnum,num,"-").intValue();
            user.setAdmnum(num);
             user.setName(name);
             userMapper.updateByPrimaryKeySelective(user);
             String description="";
             description=remmark;
             Record record=new Record();
             record.setId(UuidUtil.get32UUID());
             record.setUserid(id);
             record.setNum(num);
             if(newnum<=0){
                 record.setType(1);
                 record.setDetailType(12);
             }else {
                 record.setType(2);
                 record.setDetailType(13);
             }
             record.setNum(Math.abs(newnum));
             record.setDescription(description);
             record.setCreatetime(new Date());
             recordMapper.insertSelective(record);
            }catch (Exception e){
             throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateBalance(String id, String remmark, String name, Double balance) {
        try {
        User user= userMapper.selectByPrimaryKey(id);
        if(StringUtils.isEmpty(user)){
            throw new RuntimeException("用户不存在");
        }
        Double oldBalance=user.getBalance();
        Double newBalance= DecimalUtil.calcNumber(oldBalance,balance,"-").doubleValue();
        user.setBalance(balance);
    //    user.setName(name);
        userMapper.updateByPrimaryKeySelective(user);
        String description="";
        description=remmark;
        Record record=new Record();
        record.setId(UuidUtil.get32UUID());
        record.setUserid(id);
        record.setAmt(Math.abs(newBalance)+"");
        if(newBalance<=0){
            record.setAmt(Math.abs(newBalance)+"");
            record.setType(1);
            record.setDetailType(10);
        }else {
            record.setAmt(newBalance+"");
            record.setType(2);
            record.setDetailType(11);
        }
        record.setBalance(balance+"");
        record.setDescription(description);
        record.setCreatetime(new Date());
        recordMapper.insertSelective(record);
    }catch (Exception e){
        throw new RuntimeException(e.getMessage());
    }
    }

    @Override
    public List<TreeVO> listTeam(String uid) {
        if(StringUtils.isEmpty(uid)){
            throw new RuntimeException("参数错误");
        }
        User user=userMapper.selectByPrimaryKey(uid);
        if(null==user){
            throw new RuntimeException("用户不存在");
        }
        return otherMapperExt.selectMyChildren(uid);
    }

    @Override
    public List<TreeVO> organization(String uid) {
        List<TreeVO> list = new ArrayList<>();
        try {
            if(StringUtils.isEmpty(uid)){
                throw  new  RuntimeException("参数错误");
            }
            User user=userMapper.selectByPrimaryKey(uid);
            if(null==user){
                throw new RuntimeException("用户不存在");
            }
            TreeVO node = new TreeVO();
            node.setName(user.getName());
            node.setPhone(user.getPhone());
            node.setMoney(user.getMoney()+"");
            node.setNodelever(user.getNodelever());
            node.setId(user.getId());
            fuck(node);
            list.add(node);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    @Override
    public void updateAll(User user, Double amt, Date date, String month,String price) {
    try{
        double oldBalance=user.getBalance();
        String description = "您"+month+"获得分红奖"+price+"元";

        String  newBalance=DecimalUtil.calcNumber(oldBalance,price,"+").toString();
        user.setBalance(Double.valueOf(newBalance));
        userMapper.updateByPrimaryKeySelective(user);
        Record record1=new Record();
        record1.setId(UuidUtil.get32UUID());
        record1.setCreatetime(date);
        record1.setDescription(description);
        record1.setType(1);
        record1.setDetailType(9);
        record1.setUserid(user.getId());
        record1.setBalance(user.getBalance()+"");
        record1.setOldbalance(oldBalance+"");
        record1.setAmt(price+"");
        recordMapper.insertSelective(record1);
        Monthrecord monthrecord=new Monthrecord();
        monthrecord.setId(UuidUtil.get32UUID());
        monthrecord.setUserid(user.getId());
        monthrecord.setAmt(price);
        monthrecord.setTeammoney(amt+"");
        monthrecord.setPersonmoney(user.getMoney()+"");
        monthrecord.setCreatetime(date);
        monthrecordMapper.insertSelective(monthrecord);
    }catch (Exception e){
        throw new RuntimeException(e.getMessage());
    }
    }

    @Override
    public void modifyTotalmoney(String id, Double money) {
        try {
            User user= userMapper.selectByPrimaryKey(id);
            if(StringUtils.isEmpty(user)){
                throw new RuntimeException("用户不存在");
            }
            if(money<=0){
                throw new RuntimeException("money参数错误");
            }
            String newTotalmoney=DecimalUtil.calcNumber(user.getTotalmoney(),money,"+").toString();
            user.setTotalmoney(Double.valueOf(newTotalmoney));
            userMapper.updateByPrimaryKeySelective(user);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void modifyTotalnum(String id, Integer num) {
   try {
       User user= userMapper.selectByPrimaryKey(id);
       if(StringUtils.isEmpty(user)){
           throw new RuntimeException("用户不存在");
       }
       if(num<=0){
           throw new RuntimeException("num参数错误");
       }
       int newTotalnum=DecimalUtil.calcNumber(user.getTotalnum(),num,"+").intValue();
       user.setTotalnum(newTotalnum);
       userMapper.updateByPrimaryKeySelective(user);
   }catch (Exception e){
       throw new RuntimeException(e.getMessage());
   }
    }

    @Override
    public void modifyUser(String id, String storename) {
        User user= userMapper.selectByPrimaryKey(id);
        if(StringUtils.isEmpty(user)){
            throw new RuntimeException("用户不存在");
        }
        user.setType(2);
        user.setStorename(storename);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectOneUser(String phone) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andPhoneEqualTo(phone);
        List<User> userList=userMapper.selectByExample(userExample);
        if(userList.size()<=0){
            throw new RuntimeException("用户不存在");
        }
        return userList.get(0);
    }

    @Override
    public void update(String id) {
        User user= userMapper.selectByPrimaryKey(id);
        if(StringUtils.isEmpty(user)){
            throw new RuntimeException("用户不存在");
        }
        user.setCoupontimes(user.getCoupontimes()-1);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updateNmae(String uid, String name) {
        User user= userMapper.selectByPrimaryKey(uid);
        if(StringUtils.isEmpty(user)){
            throw new RuntimeException("用户不存在");
        }
        user.setName(name);
        userMapper.updateByPrimaryKeySelective(user);
    }

    private void fuck(TreeVO node) {
        List<TreeVO> sons =  otherMapperExt.selectByParentId(node.getId());
        if (sons.size() == 0) {
            return;
        } else {
            node.setChildren(sons);
            for (TreeVO n : sons) {
                fuck(n);
            }
        }
    }
}
