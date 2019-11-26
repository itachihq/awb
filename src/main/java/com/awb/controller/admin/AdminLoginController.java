package com.awb.controller.admin;

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
 * Created by Administrator on 2019/1/14.
 */
@ApiModel(value = "后端用户")
@RestController
@RequestMapping("/admin")
public class AdminLoginController {
    @Autowired
    private LoginService loginService ;
    @ApiOperation(value = "后台登录", httpMethod = "POST", response = Result.class,notes = "token:登录令牌,userLevel:用户等级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form")
    })
    @PostMapping("/login")
    public Result login(String username , String password){
        if (StringUtils.isEmpty(username)){
            throw new RuntimeException("用户名为空");
        }
        if (StringUtils.isEmpty(password)){
            throw new RuntimeException("密码为空");
        }
        Map map = loginService.adminLoginIn(username,password);
        return new Result("登录成功",map);
    }
}
