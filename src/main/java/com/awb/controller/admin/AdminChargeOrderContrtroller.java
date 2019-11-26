package com.awb.controller.admin;

import com.awb.component.ICache;

import com.awb.entity.param.ChargeOrderParm;
import com.awb.entity.vo.Result;

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
 * Created by Administrator on 2019/1/8.
 */
@ApiModel(value = "充值提现管理")
@RestController
@RequestMapping("/admin/charge")
public class AdminChargeOrderContrtroller {
    @Autowired
    private ICache iCache;
    @Autowired
    private ChargeService chargeService;
    @ApiOperation(value = "查询所有充值提现", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
    })
    @PostMapping(value = "/list")
    public Result list(HttpServletRequest request, ChargeOrderParm chargeOrderParm){
        Result result=new Result();
        result.setData(   chargeService.listChargeOrder(chargeOrderParm));
        return result;
    }
    @ApiOperation(value = "充值提现审核", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "订单id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "message", value = "拒绝理由", required = false, paramType = "form"),
    })
    @PostMapping("/check")
    public Result check(HttpServletRequest request, String message, Integer status, String id) throws Exception {
        if(null==status|| StringUtils.isEmpty(id)){
            throw new RuntimeException("status或id参数错误");
        }
        if (iCache.exists("adminchargeCheck_"+id)){
            throw new RuntimeException("请勿重复提交");
        }
        iCache.putString("adminchargeCheck_"+id,"",5);
        if(1==status){

        }else if(2==status){
            if(StringUtils.isEmpty(message)){
                throw new RuntimeException("message参数错误");
            }
        }else {
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        chargeService.check(id,status,message);
        result.setMsg("审核成功");
        return result;
    }
}
