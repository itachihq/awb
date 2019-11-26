package com.awb.controller.admin;

import com.awb.entity.param.ShoporderParam;
import com.awb.entity.vo.Result;
import com.awb.mapper.ShoporderMapper;
import com.awb.model.Shoporder;
import com.awb.service.OtherService;
import com.awb.service.ShopOrderService;
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
import java.util.Date;

/**
 * Created by Administrator on 2019/9/12.
 */
@ApiModel(value = "商城订单")
@RestController
@RequestMapping("/admin")
public class AdminShopOrderController {
    @Autowired
    private ShopOrderService shopOrderService;
    @Autowired
    private ShoporderMapper shoporderMapper;
    @Autowired
    private OtherService otherService;

    @ApiOperation(value = "商品订单列表", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),
            @ApiImplicitParam(name = "status", value = "token令牌", required = false, paramType = "form")

    })
    @PostMapping("/listshoporder")
    public Result listShopOrder(Integer status,Integer sign,ShoporderParam shoporderParam) throws Exception {
        Result result=new Result();
        //result.setData(shopOrderService.selectAllShopOrder(status));
        result.setData(shopOrderService.selectAllShopOrder(shoporderParam));
        return result;
    }

    @ApiOperation(value = "发货", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "id", required = false, paramType = "form"),
            @ApiImplicitParam(name = "expressType", value = "类型", required = false, paramType = "form"),
            @ApiImplicitParam(name = "expressNumber", value = "单号", required = false, paramType = "form")

    })
    @PostMapping("/sendgoods")
    public Result sendGoods(String id, String expressType, String expressNumber ){
        if (StringUtils.isEmpty(id)||StringUtils.isEmpty(expressType)||StringUtils.isEmpty(expressNumber)){
            throw new RuntimeException("参数错误");
        }
        Shoporder shopOrder= shoporderMapper.selectByPrimaryKey(id);
        if(null==shopOrder){
            throw new RuntimeException("订单不存在");
        }
        if(1==shopOrder.getStatus()){
            shopOrder.setExepressnumber(expressNumber);
            shopOrder.setExepresstype(expressType);
           shopOrder.setStatus(2);
            shopOrder.setUpdatetime(new Date());
            shopOrderService.shoprder(shopOrder);
        }else {
            throw new RuntimeException("该状态不允许发货");
        }
        Result result=new Result();
        result.setMsg("发货成功");
        return result;
    }
    @ApiOperation(value = "省市区订单", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),
            @ApiImplicitParam(name = "address", value = "省市区", required = false, paramType = "form"),

    })
    @RequestMapping("/listneworder")
    public Result listNewOrder( ShoporderParam shoporderParam,HttpServletRequest request){
        Result result=new Result();
        String uid = (String) request.getAttribute("uid");
        result.setData(otherService.listNewShoporder(uid,shoporderParam));
        return result;
    }
}
