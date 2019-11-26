package com.awb.controller.app;

import com.awb.component.ICache;
import com.awb.entity.vo.Result;
import com.awb.model.ChargeOrder;
import com.awb.service.ChargeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/1/4.
 */
@ApiModel(value = "充值提现")
@RestController
@RequestMapping("/app")
public class AppChargeOrderController {
    @Autowired
    private ChargeService chargeService;
    @ApiOperation(value = "充值提现保存", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "accountNumber", value = "打款账号", required = false, paramType = "form"),
            @ApiImplicitParam(name = "accountType", value = "打款方式", required = false, paramType = "form"),
            @ApiImplicitParam(name = "branchName", value = "支行名称", required = false, paramType = "form"),
            @ApiImplicitParam(name = "money", value = "充值提现金额", required = false, paramType = "form"),
            @ApiImplicitParam(name = "name", value = "开户姓名", required = false, paramType = "form"),
            @ApiImplicitParam(name = "bank", value = "银行名称", required = false, paramType = "form"),
            @ApiImplicitParam(name = "picture", value = "充值图片", required = false, paramType = "form"),
            @ApiImplicitParam(name = "type", value = "1充值2提现", required = true, paramType = "form"),
    })
    @PostMapping("/savechargeorder")
    public Result save(HttpServletRequest request, ChargeOrder chargeOrder,String uid) throws Exception {
        if(StringUtils.isEmpty(chargeOrder.getAccountNumber())|| StringUtils.isEmpty(chargeOrder.getAccountType())|| StringUtils.isEmpty(chargeOrder.getMoney())){
            throw new RuntimeException("参数错误");
        }
       // String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        chargeService.addChargeOrder(chargeOrder,uid);
        result.setMsg("提交成功，请等待后台审核");
        return result;
    }

    @ApiOperation(value = "提现列表", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "status", value = "0未审核1已审核", required = false, paramType = "form"),

    })
    @PostMapping("/listmychargeorder")
    public Result list(HttpServletRequest request, Integer status,String uid) throws Exception {
       // String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        result.setData( chargeService.listMyChargeOrder(uid,status));
        return result;
    }

}
