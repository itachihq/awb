package com.awb.controller.mobile;

import com.awb.component.ICache;
import com.awb.entity.vo.Result;

import com.awb.model.ChargeOrder;
import com.awb.service.ChargeService;
import com.awb.util.CommUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/4.
 */
@ApiModel(value = "充值提现")
@RestController
@RequestMapping("/web/charge")
public class WebChargeOrderController {
    @Autowired
    private ChargeService chargeService;
    @Autowired
    private ICache iCache;

    @ApiOperation(value = "提现保存", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "accountNumber", value = "打款账号", required = false, paramType = "form"),
            @ApiImplicitParam(name = "accountType", value = "打款方式", required = false, paramType = "form"),
            @ApiImplicitParam(name = "branchName", value = "支行名称", required = false, paramType = "form"),
            @ApiImplicitParam(name = "money", value = "充值提现金额", required = false, paramType = "form"),
            @ApiImplicitParam(name = "name", value = "开户姓名", required = false, paramType = "form"),
            @ApiImplicitParam(name = "bank", value = "银行名称", required = false, paramType = "form"),
    })
    @PostMapping("/save")
    public Result info(HttpServletRequest request, ChargeOrder chargeOrder) throws Exception {
        if(StringUtils.isEmpty(chargeOrder.getAccountNumber())||StringUtils.isEmpty(chargeOrder.getAccountType())||StringUtils.isEmpty(chargeOrder.getMoney())){
            throw new RuntimeException("参数错误");
        }

        String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        chargeService.addChargeOrder(chargeOrder,uid);
        result.setMsg("保存成功");
        return result;
    }



}
