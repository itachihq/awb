package com.awb.controller.admin;

import com.awb.entity.param.BalanceParm;
import com.awb.entity.vo.AdminBalanceVo;
import com.awb.entity.vo.PageResult;
import com.awb.entity.vo.Result;
import com.awb.service.OtherService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/11/13.
 */
@RestController
@RequestMapping("/admin/fin")
public class AdminFinancialController {
    @Autowired
    private OtherService otherService;
    @ApiOperation(value = "艾豆米变化列表", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = false, paramType = "form"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "form"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "form")

    })
    @PostMapping("/listadm")
    public Result listAdm(BalanceParm balanceParm){
        PageResult<AdminBalanceVo> pageResult = otherService.listAdminAdm(balanceParm);
        return new Result(pageResult);
    }

    @ApiOperation(value = "余额变化列表", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = false, paramType = "form"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "form"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "form")

    })
    @PostMapping("/listbalance")
    public Result listBalance(BalanceParm balanceParm){
        PageResult<AdminBalanceVo> pageResult = otherService.listAdminBalance(balanceParm);
        return new Result(pageResult);
    }

}
