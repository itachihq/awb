package com.awb.controller;

import com.awb.entity.param.ShoporderParam;
import com.awb.entity.vo.Result;
import com.awb.service.LoginService;
import com.awb.service.OtherService;
import com.awb.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/10/21.
 */
@RestController
@RequestMapping("/other")
public class ShareConttroller {
    @Autowired
    private LoginService loginService ;
    @Autowired
    private UserService userService;
    @Autowired
    private OtherService otherService;
    @ApiOperation(value = "分享注册", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户名", required = true, paramType = "form"),
            @ApiImplicitParam(name = "tgm", value = "推广码", required = true, paramType = "form"),
           // @ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "form")
    })
    //    @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "form"),
    @PostMapping("/sharegist")
    public Result regist(String phone ,String name,String code,String tgm){

        if (StringUtils.isEmpty(phone)){
            throw new RuntimeException("用户名为空");
        }
        if (StringUtils.isEmpty(tgm)){
            throw new RuntimeException("推广码为空");
        }
        if (StringUtils.isEmpty(code)){
            throw new RuntimeException("验证码为空");
        }
        loginService.shareRegist(phone,name,tgm,code);
        return new Result("注册成功");
    }

    @RequestMapping("/selectuser")
    public Result selectByPhone(String phone){
        Result result=new Result();
        if (StringUtils.isEmpty(phone)){
            throw new RuntimeException("手机号为空");
        }
      result.setData(userService.selectOneUser(phone));
        return result;
    }

    @RequestMapping("/updateuser")
    public Result updateUser(String id){
        if (StringUtils.isEmpty(id)){
            throw new RuntimeException("参数错误");
        }
        userService.update(id);
        return new Result("更新成功");
    }
    @RequestMapping("/insertorder")
    public Result insertorder(String phone, Integer num, String address, String handPhone, String shopid,String name){
        if (StringUtils.isEmpty(phone)||StringUtils.isEmpty(address)||StringUtils.isEmpty(handPhone)||StringUtils.isEmpty(shopid)){
            throw new RuntimeException("参数错误");
        }
        if(null==num||num<=0){
            throw new RuntimeException("参数错误");
        }
        otherService.insertShoporderHand(phone,num,address,handPhone,shopid,name);
        return new Result("下单成功");
    }



//    @RequestMapping("/registtest")
//    public Result list(String id){
//
//        loginService.registTest();
//        return new Result("更新成功");
//    }
}
