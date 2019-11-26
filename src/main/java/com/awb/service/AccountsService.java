package com.awb.service;


import com.awb.entity.param.AccountParam;
import com.awb.entity.vo.AccountVO;
import com.awb.entity.vo.PageResult;
import com.awb.model.Account;
import com.awb.model.AccountDetail;

/**
 * Created by Administrator on 2019/1/9.
 */
public interface AccountsService {
    void addPreAccount(AccountDetail accountDetail, String uid);
    PageResult<AccountVO> accounts(AccountParam accountParam);
    void check(String id, String message, Integer status);
    Account selectMyAccount(String userId);
    AccountDetail selectLastDetail(String userId);
}
