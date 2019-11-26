package com.awb.controller.app;

import com.awb.entity.param.InformationParam;
import com.awb.entity.vo.Result;
import com.awb.service.HzbService;
import com.awb.service.InformationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/8/28.
 */
@ApiModel(value = "合作货币")
@RestController
@RequestMapping("/app")
public class AppHzubController {
    @Autowired
    private HzbService hzbService;
    @Autowired
    private InformationService informationService;
    @ApiOperation(value = "资讯列表", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "currentPage", value = "当前页(后端默认1)", required = true, paramType = "form"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量(后端默认10)", required = true, paramType = "form"),
            @ApiImplicitParam(name = "type", value = "1资讯2新闻资讯)", required = true, paramType = "form"),
    })
    @PostMapping("/listinformation")
    public Result list(InformationParam informationParam){

        return new Result(informationService.listInformation(informationParam));
    }
    @ApiOperation(value = "合作货币", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),

    })
    @PostMapping("/listhzhb")
    public Result listHzhb(InformationParam informationParam){

        return new Result(hzbService.listHzhb());
    }

//    @ApiOperation(value = "新闻商家商城列表", httpMethod = "POST", response = Result.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
//
//    })
//    @PostMapping("/listinformation")
//    public Result listInformation(){
//        return new Result(informationService.listInformation());
//    }
}
