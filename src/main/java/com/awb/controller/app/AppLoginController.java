package com.awb.controller.app;

import com.awb.entity.vo.Result;
import com.awb.service.LoginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Administrator on 2019/8/22.
 */
@ApiModel(value = "app登录注册退出")
@RestController
@RequestMapping("/app")
public class AppLoginController {
    @Autowired
    private LoginService loginService ;
    @ApiOperation(value = "登录", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户名", required = true, paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form"),
            @ApiImplicitParam(name = "type", value = "密码", required = true, paramType = "form")
    })
    @PostMapping("/login")
    public Result login(String phone , String password,Integer type,String token){
        if (StringUtils.isEmpty(phone)){
            throw new RuntimeException("用户名为空");
        }
        if (StringUtils.isEmpty(password)){
            throw new RuntimeException("密码为空");
        }
        if (StringUtils.isEmpty(type)){
            throw new RuntimeException("type为空");
        }
        Map map = loginService.loginIn(phone,password,type,token);
        return new Result("登录成功",map);
    }

    @ApiOperation(value = "注册", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户名", required = true, paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form"),

            @ApiImplicitParam(name = "tgm", value = "推广码", required = true, paramType = "form"),
            @ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "form")
    })
    //    @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "form"),
    @PostMapping("/regist")
    public Result regist(String phone , String password,String name,String code,String tgm){

        if (StringUtils.isEmpty(phone)){
            throw new RuntimeException("用户名为空");
        }
        if (StringUtils.isEmpty(password)){
            throw new RuntimeException("密码为空");
        }
//        if (StringUtils.isEmpty(code)){
//            throw new RuntimeException("验证码为空");
//        }
        if (StringUtils.isEmpty(tgm)){
            throw new RuntimeException("推广码为空");
        }
        loginService.insertAdmuser(phone,name,password,code,tgm);
        return new Result("注册成功");
    }

//    @ApiOperation(value = "退出", httpMethod = "POST", response = Result.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form")
//    })
//    @PostMapping("/logout")
//    public Result logout(String token,String uid){
//        loginService.loginOut(token);
//        return new Result("退出成功");
//    }


}
