package com.awb.controller.app;

import com.awb.entity.param.ShopParam;
import com.awb.entity.vo.Result;
import com.awb.model.Shoporder;
import com.awb.service.ShopOrderService;
import com.awb.service.ShopService;
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
 * Created by Administrator on 2019/9/8.
 */
@ApiModel(value = "商城")
@RestController
@RequestMapping("/app")
public class AppShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopOrderService shopOrderService;
    @ApiOperation(value = "商品列表", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "userid", value = "商家id", required = false, paramType = "form"),
            @ApiImplicitParam(name = "isselling", value = "是否热卖0否 1是", required = false, paramType = "form"),
            @ApiImplicitParam(name = "isdirectly", value = "是否直营0否 1是", required = false, paramType = "form"),

    })
    @PostMapping("/listshop")
    public Result listShop(HttpServletRequest request, ShopParam shopParam,String uid) throws Exception {
     //   String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        result.setData(shopService.appListShop(shopParam,uid));
        return result;
    }


    @ApiOperation(value = "399商品列表", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "userid", value = "商家id", required = false, paramType = "form"),
            @ApiImplicitParam(name = "isselling", value = "是否热卖0否 1是", required = false, paramType = "form"),
            @ApiImplicitParam(name = "isdirectly", value = "是否直营0否 1是", required = false, paramType = "form"),

    })
    @PostMapping("/listnewshop")
    public Result listNewShop(HttpServletRequest request, ShopParam shopParam) throws Exception {
        Result result=new Result();
        result.setData(shopService.appListNewShop1(shopParam));
        return result;
    }
    @ApiOperation(value = "点击首页商品进入店家", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "商家id", required = true, paramType = "form"),
    })
    @PostMapping("/selectmerchant")
    public Result listMerchant(HttpServletRequest request, String userid) throws Exception {
        Result result=new Result();
        result.setData(shopService.selectMerchant(userid));
        return result;
    }

    @ApiOperation(value = "399点击首页商品进入店家", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "商家id", required = true, paramType = "form"),
    })
    @PostMapping("/newselectmerchant")
    public Result list399Merchant(HttpServletRequest request, String userid) throws Exception {
        Result result=new Result();
        result.setData(shopService.selectNewMerchant(userid));
        return result;
    }

    @ApiOperation(value = "新增商品订单", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "form"),
            @ApiImplicitParam(name = "address", value = "收货地址", required = true, paramType = "form"),
            @ApiImplicitParam(name = "phone", value = "收货人联系方式", required = true, paramType = "form"),
            @ApiImplicitParam(name = "num", value = "购买数量", required = true, paramType = "form"),
            @ApiImplicitParam(name = "shopid", value = "商品id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "totalmoney", value = "艾豆米总价", required = true, paramType = "form"),

    })
    @PostMapping("/insertshoporder")
    public Result insert(Shoporder shop, HttpServletRequest request,String uid){

        if (StringUtils.isEmpty(shop.getAddress())||StringUtils.isEmpty(shop.getName())||StringUtils.isEmpty(shop.getPhone())||StringUtils.isEmpty(shop.getNum())||StringUtils.isEmpty(shop.getShopid())||StringUtils.isEmpty(shop.getTotalmoney())){
            throw new RuntimeException("参数错误");
        }
        if(shop.getNum()<=0){
            throw new RuntimeException("数量必须大于0");
        }
       // String uid = (String) request.getAttribute("uid");
        Result result=new Result();


        shopOrderService.insertShoporder(shop,uid);
        result.setMsg("提交成功");

        return result;
    }

    @ApiOperation(value = "我的订单", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "status", value = "状态", required = true, paramType = "form"),
            @ApiImplicitParam(name = "type", value = "0  399套餐  1 艾豆米商城", required = true, paramType = "form"),

    })
    @PostMapping("/selectmyorder")
    public Result listMyOrder(HttpServletRequest request, Integer  status,String uid,Integer type) throws Exception {
        //String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        result.setData(shopOrderService.selectmyorder(uid,status,type));
        return result;
    }

    @ApiOperation(value = "399详情", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "", required = false, paramType = "form"),
            @ApiImplicitParam(name = "ids", value = "姓名", required = true, paramType = "form"),


    })
    @PostMapping("/newshopdetail")
    public Result detail(HttpServletRequest request, String ids  ,String uid) throws Exception {
        if (StringUtils.isEmpty(ids)||StringUtils.isEmpty(uid)){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        result.setData(shopService.selectShopDetail(ids,uid));
        return result;
    }

}
