package com.awb.controller;

import com.awb.entity.vo.Result;
import com.awb.mapper.AdmversionMapper;
import com.awb.mapper.UserMapper;
import com.awb.model.Admversion;
import com.awb.model.AdmversionExample;
import com.awb.model.User;
import com.awb.model.UserExample;
import com.awb.service.InformationService;
import com.awb.service.LoginService;
import com.awb.service.OtherService;
import com.awb.service.ShopService;
import com.awb.util.poi.ExcelData;
import com.awb.util.poi.ExcelUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/7/19.
 */
@RestController
@RequestMapping("/other")
public class OtherController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private InformationService informationService;
    @Autowired
    private AdmversionMapper admversionMapper;
    @Autowired
    private OtherService otherService;
    @Autowired
    private ShopService shopService;

    @Value("${outerPath}")
    private String outerParh;

    @ApiOperation(value = "获取是否隐藏标记", httpMethod = "POST", response = Result.class, notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户名", required = true, paramType = "form"),

    })
    @PostMapping("/getadminfo")
    public Result getAdminfo(String phone) throws Exception {
        Result result = new Result();
        result.setData(otherService.selectAdminfo().getTimes());
        return result;
    }
    @ApiOperation(value = "注册验证码", httpMethod = "POST", response = Result.class, notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户名", required = true, paramType = "form"),

    })
    @PostMapping("/getregistcode")
    public Result getCode(String phone) throws Exception {
        Result result = new Result();
        result.setData(loginService.getCode(phone));
        return result;
    }

    @ApiOperation(value = "忘记密码验证码", httpMethod = "POST", response = Result.class, notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户名", required = true, paramType = "form"),

    })
    @PostMapping("/forgetcode")
    public Result forgrtCode(String phone) throws Exception {
        Result result = new Result();
        result.setData(loginService.forgetCode(phone));
        return result;
    }
    @ApiOperation(value = "商品详情", httpMethod = "POST", response = Result.class, notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品id", required = true, paramType = "form"),

    })
    @RequestMapping("/shopdetail")
    public Result selectShopDetail(String id) throws Exception {
        Result result = new Result();
        result.setData(shopService.selectShop(id));
        return result;
    }



    @PostMapping("/seleciinformation")
    public Result selectByIdInformation(Integer id) throws Exception {
        Result result = new Result();
        if (org.springframework.util.StringUtils.isEmpty(id)) {
            throw new RuntimeException("参数错误");
        }
        result.setData(informationService.selectById(id));
        return result;
    }
    @ApiOperation(value = "app版本", httpMethod = "POST", response = Result.class, notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "1 android 2 ios", required = true, paramType = "form"),

    })
    @PostMapping("/version")
    public Result admversion(Integer type) throws Exception {
        Result result = new Result();
        if (org.springframework.util.StringUtils.isEmpty(type)) {
            throw new RuntimeException("参数错误");
        }
        AdmversionExample admversionExample=new AdmversionExample();
        admversionExample.createCriteria().andTypeEqualTo(type);
        result.setData(admversionMapper.selectByExample(admversionExample));
        return result;
    }

    @ApiOperation(value = "app商品详情", httpMethod = "POST", response = Result.class, notes = "token:登录令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopid", value = "商品id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "uid", value = "商品id", required = true, paramType = "form")

    })
    @PostMapping("/appshopdetail")
    public Result selectAppShopDetail(String shopid,String uid) throws Exception {
        Result result = new Result();
        if (StringUtils.isEmpty(shopid)||StringUtils.isEmpty(uid)) {
            throw new RuntimeException("参数错误");
        }
        result.setData(shopService.selectShop(uid,shopid));
        return result;
    }
//    @ApiOperation(value = "新闻商家商城列表", httpMethod = "POST", response = Result.class)
//    @ApiImplicitParams({
//
//
//    })
//    @PostMapping("/listinformation")
//    public Result listInformation(){
//        return new Result(informationService.listInformation());
//    }

    @RequestMapping("/download")
    public void download(HttpServletRequest req, HttpServletResponse resp, String filename)  {
       // String filename = req.getParameter("filename");
        if(StringUtils.isEmpty(filename)){
            throw new RuntimeException("参数错误");
        }
        try {
            resp.reset();// 清空输出流
            String resultFileName = "adm.apk";
            resultFileName = URLEncoder.encode(resultFileName, "UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Content-disposition", "attachment; filename=" + resultFileName);// 设定输出文件头
           // resp.setContentType("application/msexcel");// 定义输出类型
            resp.setContentType("application/vnd.android.package-archive");
            String name=outerParh+"/upload/awbapp/"+filename+"/aimeba-release.apk";
            //输入流：本地文件路径
            DataInputStream in = new DataInputStream(
                    new FileInputStream(new File(name)));
            //输出流
            OutputStream out = resp.getOutputStream();
            //输出文件
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());

        }
    }
//    @ApiOperation(value = "php接口", httpMethod = "POST", response = Result.class, notes = "")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "form"),
//
//    })
//    @PostMapping("/personinformation")
//    public Result php(String  phone) throws Exception {
//        Result result = new Result();
//        if (org.springframework.util.StringUtils.isEmpty(phone)) {
//            throw new RuntimeException("参数错误");
//        }
//        result.setData(otherService.selectphpnews(phone));
//        return result;
//    }
//
//    @ApiOperation(value = "php下单成功回调接口", httpMethod = "POST", response = Result.class, notes = "")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "form"),
//
//    })
//    @PostMapping("/phpcallback")
//    public Result callback(String  phone,String orderno,Integer admnum,String shopname,Integer shopnum) throws Exception {
//        Result result = new Result();
//        if (StringUtils.isEmpty(phone)||StringUtils.isEmpty(orderno)||StringUtils.isEmpty(shopname)||StringUtils.isEmpty(admnum)||StringUtils.isEmpty(shopnum)) {
//            throw new RuntimeException("参数错误");
//        }
//        result.setData(otherService.selectphpnews(phone));
//        return result;
//    }

//    @ApiOperation(value = "更新数据", httpMethod = "POST", response = Result.class, notes = "")
//    @ApiImplicitParams({
//
//
//    })
//    @PostMapping("/updatebalance")
//    public Result update() throws Exception {
//        Result result = new Result();
//
//        otherService.updatebalance();
//        return result;
//    }
//
//    @ApiOperation(value = "更新数据", httpMethod = "POST", response = Result.class, notes = "")
//    @ApiImplicitParams({
//
//
//    })
//    @PostMapping("/updatenum")
//    public Result updatenum() throws Exception {
//        Result result = new Result();
//        otherService.updatenum();
//        return result;
//    }

    @Resource
    private UserMapper userMapper;


    @ApiOperation(value = "导出", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form")

    })
    @RequestMapping("/test2")
    public void test2(HttpServletResponse response){
        int rowIndex = 0;
        UserExample userExample=new UserExample();
        userExample.createCriteria().andTypeNotEqualTo(1);
        List<User> list = userMapper.selectByExample(userExample);
        ExcelData data = new ExcelData();
        data.setName("hello");
        List<String> titles = new ArrayList();
        titles.add("手机号");
        titles.add("姓名");
//        titles.add("数量");
//        titles.add("提交时间");
//        titles.add("价格");
//        titles.add("级别");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
            User userInfo = list.get(i);
            List<Object> row = new ArrayList();
            row.add(userInfo.getPhone());
            row.add(userInfo.getName());
           // row.add(userInfo.getNodelever());
            rows.add(row);
        }
        data.setRows(rows);
        try{
            ExcelUtils.exportExcel(response,"用户列表",data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @RequestMapping("/exportuservo")
    public Result test5(HttpServletResponse response,@RequestParam("file") MultipartFile file){
        Result result=new Result();
        otherService.importFile(file);
        result.setMsg("ok");
        return  result;
    }
}
