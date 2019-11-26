package com.awb.controller;

import com.awb.component.ICache;

import com.awb.entity.param.AccountParam;
import com.awb.entity.vo.AccountVO;
import com.awb.entity.vo.PageResult;
import com.awb.entity.vo.Result;

import com.awb.mapper.UserMapper;
import com.awb.model.AccountDetail;

import com.awb.model.User;
import com.awb.service.AccountsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/1/9.
 */
@ApiModel(value = "账户管理")
@RestController
@RequestMapping("/all/account")
public class AccountController {
    @Autowired
    private AccountsService accountsService;
    @Autowired
    private UserMapper admuserMapper;
        @Autowired
    private ICache iCache;

    @ApiOperation(value = "账户列表", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "currentPage", value = "当前页(后端默认1)", required = true, paramType = "form"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量(后端默认10)", required = true, paramType = "form")
    })
    @PostMapping("/accounts")
    public Result list(AccountParam accountParam){
        PageResult<AccountVO> pageResult = accountsService.accounts(accountParam);
        return new Result(pageResult);
    }


    @ApiOperation(value = "账户审核", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "账户id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "message", value = "拒绝理由", required = true, paramType = "form")
    })
    @PostMapping("/check")
    public Result check(String id, Integer state, String message){
        if (StringUtils.isEmpty(id)){
            throw new RuntimeException("账户ID为空");
        }
        if (iCache.exists("adminAccountCheck_"+id)){
            throw new RuntimeException("请勿重复提交");
        }
        iCache.putString("adminAccountCheck_"+id,"",5);
        if (state == null){
            throw new RuntimeException("状态为空");
        }
        if (state !=1&&state!=2 ){
            throw new RuntimeException("审核状态错误");
        }
        if (state ==2){
            if (StringUtils.isEmpty(message)){
                throw new RuntimeException("请填写拒绝理由");
            }
        }
      accountsService.check(id,message,state);
        return new Result("审核成功");
    }


}
