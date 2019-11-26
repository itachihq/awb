package com.awb.controller.app;

import com.awb.entity.param.InformationParam;
import com.awb.entity.vo.PageResult;
import com.awb.entity.vo.Result;
import com.awb.service.InformationService;
import com.awb.service.OtherService;
import com.awb.service.UserService;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/22.
 */
@ApiModel(value = "我的")
@RestController
@RequestMapping("/app")
public class AppUserController {
    @Autowired
    private OtherService otherService;

    @Autowired
    private UserService userService;
    @ApiOperation(value = "修改密码", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),
            @ApiImplicitParam(name = "pw", value = "新密码", required = true, paramType = "form"),
            @ApiImplicitParam(name = "password", value = "旧密码", required = true, paramType = "form")
    })
    @PostMapping("/updatepassword")
    public Result updatePassword(HttpServletRequest request, String pw, String password,String uid) throws Exception {
        //String uid = (String) request.getAttribute("uid");
        if(StringUtils.isEmpty(password)||StringUtils.isEmpty(pw)){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        otherService.updatePassword(uid,pw,password);
        result.setMsg("修改成功");
        return result;
    }

    @ApiOperation(value = "忘记密码", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "form"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "form")
    })
    @PostMapping("/forgetpassword")
    public Result forgetPassword(HttpServletRequest request,  String password,String code,String phone) throws Exception {
       // String uid = (String) request.getAttribute("uid");
        if(StringUtils.isEmpty(password)){
            throw new RuntimeException("参数错误");
        }
        if (StringUtils.isEmpty(code)){
            throw new RuntimeException("验证码为空");
        }
        if (StringUtils.isEmpty(phone)){
            throw new RuntimeException("手机号为空");
        }
        Result result=new Result();
        userService.updatePassword(phone,password,code);
        result.setMsg("修改成功");
        return result;
    }



    @ApiOperation(value = "修改交易密码", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),
            @ApiImplicitParam(name = "tradePassword", value = "新密码", required = true, paramType = "form"),
            @ApiImplicitParam(name = "oldTradePassword", value = "旧密码", required = true, paramType = "form")
    })
    @PostMapping("/updatetradepassword")
    public Result updateTradePassword(HttpServletRequest request,String oldTradePassword, String tradePassword,String uid) throws Exception {
      // String uid = (String) request.getAttribute("uid");
        if(StringUtils.isEmpty(oldTradePassword)||StringUtils.isEmpty(tradePassword)){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        userService.updateTradePassword(uid,oldTradePassword,tradePassword);
        result.setMsg("修改成功");
        return result;
    }


    @ApiOperation(value = "修改姓名", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "form"),

    })
    @PostMapping("/updatename")
    public Result updateName(HttpServletRequest request,String name,String uid) throws Exception {
        // String uid = (String) request.getAttribute("uid");
        if(StringUtils.isEmpty(uid)||StringUtils.isEmpty(name)){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        userService.updateNmae(uid,name);
        result.setMsg("修改成功");
        return result;
    }

    @ApiOperation(value = "艾豆米", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),

    })
    @PostMapping("/selectmyadm")
    public Result myXijing(HttpServletRequest request,String uid) throws Exception {
        //String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        result.setData(otherService.selectMyZijin(uid));
        return result;
    }
    @ApiOperation(value = "我的钱包", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),

    })
    @PostMapping("/selectmywallet")
    public Result myBalance(HttpServletRequest request,String uid) throws Exception {
        //String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        result.setData(otherService.selectMyBalance(uid));
        return result;
    }
    @ApiOperation(value = "我的资料", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),

    })
    @PostMapping("/selectmyinfo")
    public Result myInformation(HttpServletRequest request,String uid) throws Exception {
        //String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        Map map=new HashMap();
        map.put("times",otherService.selectAdminfo().getTimes());
        map.put("info",otherService.selectMyInfo(uid));
        result.setData(map);//otherService.selectMyInfo(uid)
        return result;
    }

    @ApiOperation(value = "我的理疗卡", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),

    })
    @PostMapping("/selectmytiyanka")
    public Result select(HttpServletRequest request,String uid) throws Exception {
        //String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        result.setData(otherService.selectMyTiyan(uid));
        return result;
    }
}
