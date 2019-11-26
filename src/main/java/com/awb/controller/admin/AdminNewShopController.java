package com.awb.controller.admin;

import com.awb.entity.vo.Result;
import com.awb.model.Shop;
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
 * Created by Administrator on 2019/10/11.
 */
@ApiModel(value = "后端用户")
@RestController
@RequestMapping("/admin/newshop")
public class AdminNewShopController {
    @Autowired
    private ShopService shopService;
    @ApiOperation(value = "新增商品", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),
            @ApiImplicitParam(name = "shopName", value = "商品名称", required = true, paramType = "form"),
            @ApiImplicitParam(name = "information", value = "商品介绍", required = true, paramType = "form"),
            @ApiImplicitParam(name = "shopPicture", value = "商品图片", required = true, paramType = "form"),
            @ApiImplicitParam(name = "detai", value = "商品详情", required = true, paramType = "form"),
           // @ApiImplicitParam(name = "userid", value = "商家id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "shopPrice", value = "商品价格", required = true, paramType = "form"),
            @ApiImplicitParam(name = "standard", value = "商品规格", required = true, paramType = "form")
    })
    @PostMapping("/insertnewshop")
    public Result insert(Shop shop, String leverLength){
        if (StringUtils.isEmpty(shop.getShopPicture())||StringUtils.isEmpty(shop.getShopPrice())||StringUtils.isEmpty(shop.getInformation())||StringUtils.isEmpty(shop.getStandard())||StringUtils.isEmpty(shop.getDetai())){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        shopService.insertNewShop(shop);
        result.setMsg("新增成功");
        return result;
    }

    @ApiOperation(value = "商品列表", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form")

    })
    @PostMapping("/listnewshop")
    public Result listShop(HttpServletRequest request,String id) throws Exception {
        Result result=new Result();
        result.setData(shopService.adminListNewShop(id));
        return result;
    }

}
