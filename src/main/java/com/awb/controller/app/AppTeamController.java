package com.awb.controller.app;

import com.awb.entity.vo.Result;
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

/**
 * Created by Administrator on 2019/8/28.
 */
@ApiModel(value = "团队列表")
@RestController
@RequestMapping("/app")
public class AppTeamController {
    @Autowired
    private UserService userService;

    @Autowired
    private OtherService otherService;
    @ApiOperation(value = "团队列表", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),

    })
    @PostMapping("/list")
    public Result list(HttpServletRequest request, String id,String uid) throws Exception {
      //  String uid = (String) request.getAttribute("uid");
        Result result=new Result();

        if(StringUtils.isEmpty(id)){
            result.setData(userService.listTeam(uid));
        }else {
            result.setData(userService.listTeam(id));
        }

        return result;
    }
    @ApiOperation(value = "组织架构", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),

    })
    @PostMapping("/detail")
    public Result info(HttpServletRequest request,String uid) throws Exception {
        //  String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        result.setData(userService.organization(uid));
        return result;
    }


    @ApiOperation(value = "一级返利", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),

    })
    @PostMapping("/onerebait")
    public Result onerebait(HttpServletRequest request,String uid) throws Exception {
        Result result=new Result();
        result.setData(otherService.selectOnerebait(uid));
        return result;
    }
    @ApiOperation(value = "二级返利", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),

    })
    @PostMapping("/tworebait")
    public Result tworebait(HttpServletRequest request,String uid) throws Exception {
        Result result=new Result();
        result.setData(otherService.selectTworebait(uid));
        return result;
    }

//    @ApiOperation(value = "三级返利", httpMethod = "POST", response = Result.class,notes = "")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
//
//    })
//    @PostMapping("/threerebait")
//    public Result threerebait(HttpServletRequest request,String uid) throws Exception {
//        Result result=new Result();
//        result.setData(otherService.selectThreerebait(uid));
//        return result;
//    }
}
