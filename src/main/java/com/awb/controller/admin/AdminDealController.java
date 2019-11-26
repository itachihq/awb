package com.awb.controller.admin;

import com.awb.entity.param.RecordParam;
import com.awb.entity.vo.Result;
import com.awb.service.DealService;
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
 * Created by Administrator on 2019/6/25.
 */
@ApiModel(value = "买卖")
@RestController
@RequestMapping("/admin")
public class AdminDealController {
    @Autowired
    private DealService dealService;
    @ApiOperation(value = "艾豆米审核", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),
            @ApiImplicitParam(name = "remark", value = "拒绝原因", required = false, paramType = "form"),
            @ApiImplicitParam(name = "status", value = "1代表同意 2拒绝", required = true, paramType = "form"),
            @ApiImplicitParam(name = "realmoney", value = "实际打款金额", required = false, paramType = "form"),
            @ApiImplicitParam(name = "shouldmoney", value = "理应打金额", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "列表id", required = true, paramType = "form")
    })
    @PostMapping("/chckdeal")
    public Result updateDeal(HttpServletRequest request, Integer status, String remark, String id,Double realmoney,Double shouldmoney) throws Exception {
  String uid = (String) request.getAttribute("uid");
        if(StringUtils.isEmpty(realmoney)||StringUtils.isEmpty(id)||StringUtils.isEmpty(shouldmoney)){
            throw new RuntimeException("参数错误");
        }
        if(null==status||0==status){
            throw new RuntimeException("参数错误");
        }
        dealService.checkDeal(id,status,remark,realmoney,shouldmoney);
        Result result=new Result();
        result.setMsg("审核成功");
        return result;
    }

    @ApiOperation(value = "买入列表", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),

    })
    @PostMapping("/listcompanydeal")
    public Result listCompanyDeal() throws Exception {
        Result result=new Result();
        result.setData(dealService.listBuyCompanyDeal());
        return result;
    }


    @ApiOperation(value = "交易列表", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),

    })
    @PostMapping("/listalldeal")
    public Result listDeal() throws Exception {
        Result result=new Result();
        result.setData(dealService.listDeal());
        return result;
    }
    @ApiOperation(value = "艾豆米大盘待卖出列表", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),

    })
    @PostMapping("/listdapandeal")
    public Result listDaPanDeal() throws Exception {
        Result result=new Result();
        result.setData(dealService.liseDaPanDeal());
        return result;
    }

    @ApiOperation(value = "大盘交易", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),
            @ApiImplicitParam(name = "num", value = "数量", required = true, paramType = "form"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "form"),


    })
    @PostMapping("/handdeal")
    public Result deal(String id, Integer num, String phone) throws Exception {
        if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(id)){
            throw new RuntimeException("参数错误");
        }
        if(null==num||0>=num){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
      dealService.seil(id,num,phone);
        result.setMsg("交易成功");
        return result;
    }

    @ApiOperation(value = "撤销交易", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "id", required = false, paramType = "form"),
    })
    @PostMapping("/deletedapandeal")
    public Result deleteDeal(HttpServletRequest request, String id) throws Exception {
        String uid = (String) request.getAttribute("uid");
        if(StringUtils.isEmpty(id)){
            throw new RuntimeException("参数错误");
        }
        dealService.deleteGuamai(id);
        Result result=new Result();
        result.setMsg("审核成功");
        return result;
    }

    @ApiOperation(value = "收支记录", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),

    })
    @PostMapping("/listallrecord")
    public Result listRecord(RecordParam recordParam) throws Exception {
        Result result=new Result();
        result.setData(dealService.appListShop(recordParam));
        return result;
    }
}
