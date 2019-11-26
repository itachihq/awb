package com.awb.controller.admin;

import com.awb.entity.param.UserParam;
import com.awb.entity.vo.AdminUserVo;
import com.awb.entity.vo.PageResult;
import com.awb.entity.vo.Result;
import com.awb.entity.vo.UserVo;
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

/**
 * Created by Administrator on 2019/8/28.
 */
@ApiModel(value = "用户")
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserService userService;
    @ApiOperation(value = "用户列表", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form")

    })
    @PostMapping("/listuser")
    public Result list(UserParam userParam){
        PageResult<AdminUserVo> pageResult = userService.listAllSysuser(userParam);
        return new Result(pageResult);
    }
    @ApiOperation(value = "修改等级", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "nodelever", value = "级别", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "form"),

    })
    @PostMapping("/modifylever")
    public Result updateNodelever(HttpServletRequest request, Integer nodelever,String id) throws Exception {
        if(StringUtils.isEmpty(nodelever)||StringUtils.isEmpty(id)){
            throw new RuntimeException("参数错误");
        }
        if(nodelever!=10&&nodelever!=20&&nodelever!=30&&nodelever!=40&&nodelever!=50&&nodelever!=60){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        userService.updateNodelever(id,nodelever);
        result.setMsg("修改成功");
        return result;
    }
    @ApiOperation(value = "设置余额", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "balance", value = "余额", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "remark", value = "备注", required = true, paramType = "form"),
            @ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "form"),

    })
    @PostMapping("/modifybalance")
    public Result updateBalance(HttpServletRequest request, Double balance,String id,String remark,String name) throws Exception {
        if(StringUtils.isEmpty(remark)||StringUtils.isEmpty(id)||StringUtils.isEmpty(balance)){
            throw new RuntimeException("参数错误");
        }
        if(balance<0){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        userService.updateBalance(id,remark,name,balance);
        result.setMsg("修改成功");
        return result;
    }


    @ApiOperation(value = "设置艾豆米数量", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "remark", value = "备注", required = true, paramType = "form"),
            @ApiImplicitParam(name = "num", value = "数量", required = true, paramType = "form"),
            @ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "form"),

    })
    @PostMapping("/modifynum")
    public Result updateNum(HttpServletRequest request,String id,String remark,Integer num,String name) throws Exception {
        if(StringUtils.isEmpty(remark)||StringUtils.isEmpty(id)||StringUtils.isEmpty(num)){
            throw new RuntimeException("参数错误");
        }
        if(num<0){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        userService.update(id,remark,name,num);
        result.setMsg("修改成功");
        return result;
    }
    @ApiOperation(value = "修改总金额", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "money", value = "余额", required = true, paramType = "form"),


    })
    @PostMapping("/modifytotalmoney")
    public Result updateTotalmoney(HttpServletRequest request, Double money,String id) throws Exception {
        if(StringUtils.isEmpty(id)||StringUtils.isEmpty(money)){
            throw new RuntimeException("参数错误");
        }

        Result result=new Result();
        userService.modifyTotalmoney(id,money);
        result.setMsg("修改成功");
        return result;
    }

    @ApiOperation(value = "修改总数量", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "num", value = "余额", required = true, paramType = "form"),


    })
    @PostMapping("/modifytotalnum")
    public Result updateTotalnum(HttpServletRequest request, Integer num,String id) throws Exception {
        if(StringUtils.isEmpty(id)||StringUtils.isEmpty(num)){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        userService.modifyTotalnum(id,num);
        result.setMsg("修改成功");
        return result;
    }
}
