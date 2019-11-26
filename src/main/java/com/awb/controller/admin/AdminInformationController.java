package com.awb.controller.admin;

import com.awb.entity.vo.Result;
import com.awb.model.Hzhb;
import com.awb.model.Information;
import com.awb.service.HzbService;
import com.awb.service.InformationService;
import com.awb.state.ImageFormatEnum;
import com.awb.util.CommUtil;
import com.awb.util.UuidUtil;
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
 * Created by Administrator on 2019/8/22.
 */
@ApiModel(value = "资讯合作币管理")
@RestController
@RequestMapping("/admin")
public class AdminInformationController {
    @Autowired
    private HzbService hzbService;
    @Autowired
    private InformationService informationService;
    @Value("${outerPath}")
    private String outerParh;
    @Value("${env}")
    private String env;
    @ApiOperation(value = "合作币保存", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "name", value = "货币名称", required = true, paramType = "form"),
            @ApiImplicitParam(name = "rate", value = "增长率", required = true, paramType = "form"),
            @ApiImplicitParam(name = "price", value = "价格", required = true, paramType = "form"),

    })
    @PostMapping("/savehzhb")
    public Result saveHzb(HttpServletRequest request, Hzhb hzhb) throws Exception {
        if(StringUtils.isEmpty(hzhb.getName())||StringUtils.isEmpty(hzhb.getPrice())||StringUtils.isEmpty(hzhb.getRate())){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        hzbService.insertHzb(hzhb);
        result.setMsg("保存成功");
        return result;
    }

    @ApiOperation(value = "合作币删除", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "form"),

    })
    @PostMapping("/deletehzhb")
    public Result deleteHzhb( Integer id ) throws Exception {
        if(StringUtils.isEmpty(id)){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        hzbService.deleteHzhb(id);
        result.setMsg("删除成功");
        return result;
    }

    @ApiOperation(value = "合作币更新", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "price", value = "价格", required = true, paramType = "form"),
            @ApiImplicitParam(name = "rate", value = "增长率", required = true, paramType = "form"),

    })
    @PostMapping("/updatehzhb")
    public Result updateHzhb( Integer id,String price,String rate ) throws Exception {
        if(StringUtils.isEmpty(id)||StringUtils.isEmpty(price)||StringUtils.isEmpty(rate)){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        hzbService.updateHzhb(id,price,rate);
        result.setMsg("修改成功");
        return result;
    }
    @ApiOperation(value = "资讯新闻资讯保存", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "form"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, paramType = "form"),
            @ApiImplicitParam(name = "type", value = "1资讯2 新闻资讯3新手指引4分享推广5商城活动6商家活动", required = true, paramType = "form"),
            @ApiImplicitParam(name = "picture", value = "图片", required = false, paramType = "form"),
            @ApiImplicitParam(name = "url", value = "链接", required = true, paramType = "form"),

    })
    @PostMapping("/saveinformation")
    public Result saveInfo( Information information) throws Exception {
        if(StringUtils.isEmpty(information.getTitle())||StringUtils.isEmpty(information.getContent())||StringUtils.isEmpty(information.getType())){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        hzbService.insertInformation(information);
        result.setMsg("保存成功");
        return result;
    }
    @ApiOperation(value = "资讯新闻资讯删除", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "form"),

    })
    @PostMapping("/deleteinformation")
    public Result saveInfo( Integer id ) throws Exception {
        if(StringUtils.isEmpty(id)){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        informationService.deleteById(id);
        result.setMsg("删除成功");
        return result;
    }

    @ApiOperation(value = "资讯更新", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, paramType = "form"),
            @ApiImplicitParam(name = "picture", value = "图片", required = false, paramType = "form"),
            @ApiImplicitParam(name = "url", value = "链接", required = true, paramType = "form"),

    })
    @PostMapping("/updateinformation")
    public Result updateInformation( Integer id,String content,String picture,String url) throws Exception {
        if(StringUtils.isEmpty(id)||StringUtils.isEmpty(content)||StringUtils.isEmpty(picture)||StringUtils.isEmpty(url)){
            throw new RuntimeException("参数错误");
        }
        Result result=new Result();
        informationService.updateInformation(id,content,picture,url);
        result.setMsg("修改成功");
        return result;
    }
//  @ApiImplicitParam(name = "type", value = "1资讯2新闻资讯3新手指引4分享推广5商城活动6商家活动", required = true, paramType = "form")
    @ApiOperation(value = "上传图片", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),

    })
    @PostMapping(value = "/savepic")
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
            String urls = uploadVoucher2(fileName,file,env,"information/");
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
