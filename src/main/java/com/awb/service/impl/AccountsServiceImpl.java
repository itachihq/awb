package com.awb.service.impl;


import com.awb.entity.param.AccountParam;
import com.awb.entity.vo.AccountVO;
import com.awb.entity.vo.PageResult;
import com.awb.mapper.AccountDetailMapper;
import com.awb.mapper.AccountMapper;
import com.awb.mapper.OtherMapperExt;
import com.awb.mapper.UserMapper;
import com.awb.model.*;
import com.awb.service.AccountsService;
import com.awb.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/1/9.
 */
@Service
@Transactional
public class AccountsServiceImpl implements AccountsService {
    @Autowired
    private UserMapper admuserMapper;
    @Autowired
    private OtherMapperExt otherMapperExt;
    @Autowired
    private AccountDetailMapper accountDetailMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public void addPreAccount(AccountDetail accountDetail, String uid) {
        User sysUser=admuserMapper.selectByPrimaryKey(uid);
        if(StringUtils.isEmpty(sysUser)){
            throw  new  RuntimeException("用户不存在");
        }
       AccountDetail ad= otherMapperExt.selectLastDetail(uid);
        if(null!=ad&&"0".equals(ad.getStatus())){
            throw  new  RuntimeException("有待审核订单不允许再次修改");
        }
        accountDetail.setId(UuidUtil.get32UUID());
        accountDetail.setCreateTime(new Date());
        accountDetail.setUserId(uid);
        accountDetail.setStatus(0);
        accountDetailMapper.insertSelective(accountDetail);
    }

    @Override
    public PageResult<AccountVO> accounts(AccountParam accountParam) {
        PageHelper.startPage(accountParam.getCurrentPage(),accountParam.getPageSize());
        List<AccountVO> list=otherMapperExt.selectAllAccountDetail(accountParam);
        PageInfo<AccountVO> pageInfo = new PageInfo<AccountVO>(list);
        return new PageResult(pageInfo.getTotal(),list,accountParam.getCurrentPage()) ;
    }

    @Override
    public synchronized void check(String id, String message, Integer status) {
        AccountDetail accountDetail=  accountDetailMapper.selectByPrimaryKey(id);
        if(null==accountDetail){
            throw  new  RuntimeException("订单不存在");
        }
        if(0!=accountDetail.getStatus()){
            throw new RuntimeException("该账户已经审核,请勿重复审核");
        }
        User sysUser=admuserMapper.selectByPrimaryKey(accountDetail.getUserId());
        if(StringUtils.isEmpty(sysUser)){
            throw  new  RuntimeException("用户不存在");
        }
        Date date=new Date();
        accountDetail.setStatus(status);
        accountDetail.setUpdateTime(date);
        if(1==status){
            AccountExample accountExample=new AccountExample();
            accountExample.createCriteria().andUserIdEqualTo(sysUser.getId());
            List<Account> list=accountMapper.selectByExample(accountExample);
            Account account;
            if (CollectionUtils.isEmpty(list)){
                 account=new Account();
                account.setId(UuidUtil.get32UUID());
                account.setCreateTime(date);
                account.setAccountName(accountDetail.getAccountName());
                account.setAccountNumber(accountDetail.getAccountNumber());
                account.setAccountType(accountDetail.getAccountType());
                account.setUserId(accountDetail.getUserId());
                account.setBankName(accountDetail.getBankName());
                account.setBranchName(accountDetail.getBranchName());
                accountMapper.insertSelective(account);
            }else {
            account=list.get(0);
                account.setAccountName(accountDetail.getAccountName());
                account.setAccountNumber(accountDetail.getAccountNumber());
                account.setAccountType(accountDetail.getAccountType());
                account.setBankName(accountDetail.getBankName());
                account.setBranchName(accountDetail.getBranchName());
                account.setUpdateTime(date);
                accountMapper.updateByPrimaryKeySelective(account);
            }
        }else if(2==status){
            accountDetail.setRemark(message);
        }else {
            throw  new  RuntimeException("参数错误");
        }
        accountDetailMapper.updateByPrimaryKeySelective(accountDetail);
    }

    @Override
    public Account selectMyAccount(String userId) {
       return otherMapperExt.selectMyAccount(userId);
    }

    @Override
    public AccountDetail selectLastDetail(String userId) {
        return otherMapperExt.selectLastDetail(userId);
    }
}
