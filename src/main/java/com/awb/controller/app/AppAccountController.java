package com.awb.controller.app;

import com.awb.component.ICache;
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
 * Created by Administrator on 2019/8/26.
 */
@ApiModel(value = "账户管理")
@RestController
@RequestMapping("/app")
public class AppAccountController {
    @Autowired
    private AccountsService accountsService;
    @Autowired
    private UserMapper admuserMapper;
    @Autowired
    private ICache iCache;
    @ApiOperation(value = "首次进入账户列表", httpMethod = "POST", response = Result.class,notes = "msg:状态 0:待审核,1:已通过,2:未通过 3:从未提交,bankName:银行名称,bankCard:银行卡号,accountType:账户类型,accountName:账户名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),

    })
    @PostMapping("/account")
    public Result selectFirst(HttpServletRequest request,String uid) throws Exception {
      //  String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        User sysUser=admuserMapper.selectByPrimaryKey(uid);
        if(org.springframework.util.StringUtils.isEmpty(sysUser)){
            throw  new  RuntimeException("用户不存在");
        }
        AccountDetail accountDetail=accountsService.selectLastDetail(uid);
        if(null==accountDetail){
            result.setMsg("3");
        }else {
            result.setMsg(accountDetail.getStatus()+"");
        }
        result.setData(accountsService.selectMyAccount(uid));
        return result;
    }

    @ApiOperation(value = "待审核和拒绝账户列表", httpMethod = "POST", response = Result.class,notes = "bankName:银行名称,bankCard:银行卡号,accountType:账户类型,accountName:账户名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),

    })
    @PostMapping("/accountdetail")
    public Result NoAccount(HttpServletRequest request,String uid) throws Exception {
       // String uid = (String) request.getAttribute("uid");
        String status=request.getParameter("status");
        User sysUser=admuserMapper.selectByPrimaryKey(uid);
        if(org.springframework.util.StringUtils.isEmpty(sysUser)){
            throw  new  RuntimeException("用户不存在");
        }
        Result result=new Result();
        result.setData(accountsService.selectLastDetail(uid));
        return result;
    }

    @ApiOperation(value = "点击提交保存账户信息", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "bankName", value = "银行名称", required = true, paramType = "form"),
            @ApiImplicitParam(name = "accountNumber", value = "银行卡号", required = true, paramType = "form"),
            @ApiImplicitParam(name = "accountType", value = "账户类型", required = true, paramType = "form"),
            @ApiImplicitParam(name = "accountName", value = "账户名", required = true, paramType = "form"),

    })
    @PostMapping("/save")
    public Result savePreAccount(HttpServletRequest request, AccountDetail accountDetail,String uid) throws Exception {
        //String uid = (String) request.getAttribute("uid");
        if(StringUtils.isEmpty(accountDetail.getAccountName())||StringUtils.isEmpty(accountDetail.getAccountType())||StringUtils.isEmpty(accountDetail.getAccountNumber())){//||StringUtils.isEmpty(preAccount.getBankName())
            throw new RuntimeException("参数错误");
        }
        if (iCache.exists("savepreaccount_"+uid)){
            throw new RuntimeException("请勿重复提交");
        }
        iCache.putString("adminOrderExpress_"+uid,"",3);
        accountsService.addPreAccount(accountDetail,uid);
        return new Result("提交成功");
    }
}
