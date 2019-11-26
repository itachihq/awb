package com.awb.controller.admin;

import com.awb.entity.vo.Result;
import com.awb.model.Shop;
import com.awb.service.ShopOrderService;
import com.awb.service.ShopService;
import com.awb.service.UserService;
import com.awb.state.ImageFormatEnum;
import com.awb.util.CommUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Created by Administrator on 2019/9/7.
 */
@ApiModel(value = "商家")
@RestController
@RequestMapping("/admin")
public class AdminShopController {
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopOrderService shopOrderService;
    @Value("${env}")
    private String env;
    @Value("${outerPath}")
    private String outerParh;
    @ApiOperation(value = "设置商家", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "storename", value = "店铺名称", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "form"),

    })
    @PostMapping("/modifyuser")
    public Result updateNodelever(HttpServletRequest request,String id,String storename) throws Exception {
        if(StringUtils.isEmpty(storename)||StringUtils.isEmpty(id)){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        userService.modifyUser(id,storename);
        result.setMsg("设置成功");
        return result;
    }

    @ApiOperation(value = "商家列表", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form")

    })
    @PostMapping("/listusershop")
    public Result listUser(HttpServletRequest request) throws Exception {
        Result result=new Result();
        result.setData(shopService.listmerchantvo());
        return result;
    }
    @ApiOperation(value = "新增商品", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),
            @ApiImplicitParam(name = "shopName", value = "商品名称", required = true, paramType = "form"),
            @ApiImplicitParam(name = "information", value = "商品介绍", required = true, paramType = "form"),
            @ApiImplicitParam(name = "shopPicture", value = "商品图片", required = true, paramType = "form"),
            @ApiImplicitParam(name = "detai", value = "商品详情", required = true, paramType = "form"),
            @ApiImplicitParam(name = "userid", value = "商家id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "leverLength", value = "不同级别用逗号隔开 级别价格用冒号隔开", required = true, paramType = "form"),
            @ApiImplicitParam(name = "standard", value = "商品规格", required = true, paramType = "form")
    })
    @PostMapping("/insertshop")
    public Result insert(Shop shop, String leverLength){
        if (StringUtils.isEmpty(shop.getShopPicture())||StringUtils.isEmpty(shop.getUserid())||StringUtils.isEmpty(shop.getInformation())||StringUtils.isEmpty(shop.getStandard())||StringUtils.isEmpty(shop.getDetai())){
            throw new RuntimeException("参数错误");
        }
        if(StringUtils.isEmpty(leverLength)){
            throw new RuntimeException("leverLength参数错误");
        }
        Result result=new Result();
     shopService.insertShop(shop, leverLength);
        result.setMsg("新增成功");
        return result;
    }

    @ApiOperation(value = "更新商品", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "商品名称", required = true, paramType = "form"),
            @ApiImplicitParam(name = "shopName", value = "商品名称", required = true, paramType = "form"),
            @ApiImplicitParam(name = "information", value = "商品介绍", required = true, paramType = "form"),
            @ApiImplicitParam(name = "detai", value = "商品详情", required = true, paramType = "form"),
            @ApiImplicitParam(name = "shopPicture", value = "商品图片", required = true, paramType = "form"),
            @ApiImplicitParam(name = "userid", value = "商家id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "standard", value = "商品规格", required = true, paramType = "form"),
            @ApiImplicitParam(name = "shopPrice", value = "商品价格", required = false, paramType = "form"),
    })
    @PostMapping("/updateshop")
    public Result update(Shop shop){
        if (StringUtils.isEmpty(shop.getId())){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        shopService.updateByPrimaryKeySelective(shop);
        result.setMsg("修改成功");
        return result;
    }
    @ApiOperation(value = "设置商品属性", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "商品id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "type", value = "1设置直营  2设置热卖", required = true, paramType = "form"),
            @ApiImplicitParam(name = "sign", value = "0 否  1是", required = true, paramType = "form"),

    })
    @PostMapping("/modifyshop")
    public Result modify(String id,Integer type,Integer sign){
        if (StringUtils.isEmpty(type)||StringUtils.isEmpty(id)||StringUtils.isEmpty(sign)){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        shopService.modifyShopAttribute(id, type,sign);
        result.setMsg("设置成功");
        return result;
    }

    @ApiOperation(value = "商品列表", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form")

    })
    @PostMapping("/listshop")
    public Result listShop(HttpServletRequest request,String id) throws Exception {
        Result result=new Result();
        result.setData(shopService.adminListShop(id));
        return result;
    }

    @ApiOperation(value = "查询商品价格", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "商品id", required = true, paramType = "form")

    })
    @PostMapping("/selectshopprice")
    public Result selectPrice(String  id) throws Exception {
        if (StringUtils.isEmpty(id)){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        result.setData(shopOrderService.selectShopPrice(id));
        return result;
    }
    @ApiOperation(value = "更新商品价格", httpMethod = "POST", response = Result.class,notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "商品id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "nodelever", value = "级别", required = true, paramType = "form"),

            @ApiImplicitParam(name = "price", value = "商品价格", required = true, paramType = "form")

    })
    @PostMapping("/updateshopprice")
    public Result updatePrice(String  id,Integer nodelever,Integer admnum,String price) throws Exception {
        if (StringUtils.isEmpty(id)){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        shopService.updateShopPrice(id,nodelever,price);
        result.setMsg("更新成功");
        return result;
    }
    @ApiOperation(value = "上传图片", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),

    })
    @PostMapping(value = "/saveshoppic")
    public Result addNoticePic(@RequestParam( "files") MultipartFile file, HttpServletRequest request, String type){
        if (file == null  ){
            throw new RuntimeException("图片为空");
        }

//        if (StringUtils.isEmpty(type)){
//            throw new RuntimeException("type");
//        }
        String oldFileName = file.getOriginalFilename();

        if (file.getSize() > 5000000){
            throw new RuntimeException("文件不能超过5M");
        }
        String suffix = oldFileName.substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if (!ImageFormatEnum.vaildateImage(suffix)){
            throw new RuntimeException("图片格式只能是bmp,gif,jep,jpeg,png,ico");
        }
        String fileName = CommUtil.getUniqueNumberByRandom() + "." + suffix;
//        if("1".equals(type)){
        String urls = uploadVoucher2(fileName,file,env,"shop/");
        return new Result("上传成功",urls);
//        }else {
//            String urls = uploadVoucher2(fileName,file,env,"rule/");
//            return new Result("上传成功",urls);
//        }

    }
    public String uploadVoucher2(String fileName, MultipartFile file, String type, String name) {
        try {
            String uri="";
            if(type.equals("dev")){
                uri = "/upload/awbapp/pro/"+name;
            }else{
                uri =   "/upload/awbapp/test/"+name;
            }
            String diskPath =   outerParh+uri;
            File filePath = new File(diskPath);
            if (!filePath.exists()){
                filePath.mkdirs();
            }
            File tempFile = new File(diskPath+fileName);
            tempFile.createNewFile();
            file.transferTo(tempFile);
            return uri+fileName ;
        } catch (IOException e) {
            System.out.println(   e.getMessage());
            final Writer result = new StringWriter();
            final PrintWriter print = new PrintWriter(result);
            e.printStackTrace(print);
            System.out.println("==============================="+result.toString());

            throw new RuntimeException("上传失败");
        }
    }
}
