package com.awb.controller.admin;

import com.awb.entity.vo.Result;
import com.awb.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/9/16.
 */
@ApiModel(value = "团队列表")
@RestController
@RequestMapping("/admin")
public class AdminTeamController {
    @Autowired
    private UserService userService;
    @ApiOperation(value = "组织架构", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),

    })
    @PostMapping("/detail")
    public Result info(HttpServletRequest request) throws Exception {
       String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        result.setData(userService.organization(uid));
        return result;
    }
}
