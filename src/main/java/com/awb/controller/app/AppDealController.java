package com.awb.controller.app;

import com.awb.entity.vo.Result;
import com.awb.model.Moneyrecord;
import com.awb.model.User;
import com.awb.service.DealService;
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
 * Created by Administrator on 2019/8/27.
 */
@ApiModel(value = "艾豆米交易")
@RestController
@RequestMapping("/app")
public class AppDealController {
    @Autowired
    private DealService dealService;
    @Value("${outerPath}")
    private String outerParh;
    @Value("${env}")
    private String env;
    @ApiOperation(value = "公司买入", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "num", value = "数量", required = true, paramType = "form"),
            @ApiImplicitParam(name = "type", value = "1银行账户2支付宝3微信", required = true, paramType = "form"),
            @ApiImplicitParam(name = "accountnum", value = "账号", required = true, paramType = "form"),
            @ApiImplicitParam(name = "arrivetime", value = "打款时间", required = false, paramType = "form"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, paramType = "form"),
            @ApiImplicitParam(name = "money", value = "金额", required = false, paramType = "form"),
            @ApiImplicitParam(name = "picture", value = "打款截图", required = false, paramType = "form"),
            @ApiImplicitParam(name = "picture2", value = "打款截图", required = false, paramType = "form"),
            @ApiImplicitParam(name = "picture3", value = "打款截图", required = false, paramType = "form"),

    })
    @PostMapping("/savecompanydeal")
    public Result companyDeal(HttpServletRequest request, Integer num, Moneyrecord moneyrecord,String uid) throws Exception {
        if(StringUtils.isEmpty(num)|| StringUtils.isEmpty(moneyrecord.getType())|| StringUtils.isEmpty(moneyrecord.getAccountnum())){
            throw new RuntimeException("参数错误");
        }
     //   String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        dealService.saveCompanyDeal(uid,num,moneyrecord);
        result.setMsg("提交成功,请耐心等待后台审核");
        return result;
    }
    @ApiOperation(value = "划转", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "num", value = "数量", required = true, paramType = "form"),
            @ApiImplicitParam(name = "phone", value = "划转对象", required = true, paramType = "form"),

    })
    @PostMapping("/saveagentdeal")
    public Result agentDeal(HttpServletRequest request,Integer num,String phone,String uid) throws Exception {
        if(StringUtils.isEmpty(num)|| StringUtils.isEmpty(phone)){
            throw new RuntimeException("参数错误");
        }
      //  String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        dealService.agentBuy(uid,num,phone);
        result.setMsg("划转成功");
        return result;
    }

    @ApiOperation(value = "划转记录", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
    })
    @PostMapping("/listagentdeal")
    public Result listAgentDeal(HttpServletRequest request,String uid) throws Exception {
      //  String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        result.setData( dealService.listTransfer(uid));
        return result;
    }

    @ApiOperation(value = "挂卖", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "num", value = "数量", required = true, paramType = "form"),

    })
    @PostMapping("/saveselldeal")
    public Result sellDeal(HttpServletRequest request,Integer num,String uid) throws Exception {
        if(StringUtils.isEmpty(num)){
            throw new RuntimeException("参数错误");
        }
       // String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        dealService.grailSell(uid,num);
        result.setMsg("提交成功");
        return result;
    }

    @ApiOperation(value = "成交记录", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
    })
    @PostMapping("/listselldealsuccess")
    public Result listSellDealSuccess(HttpServletRequest request) throws Exception {
       // String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        result.setData( dealService.listDealRecordSuccess());
        return result;
    }

    @ApiOperation(value = "待卖出已卖出列表", httpMethod = "POST", response = Result.class,notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "status", value = "0待卖出1已卖出", required = true, paramType = "form"),
    })
    @PostMapping("/listmysell")
    public Result listMySell(HttpServletRequest request,Integer status,String uid) throws Exception {
      // String uid = (String) request.getAttribute("uid");
        Result result=new Result();
        result.setData( dealService.listMyDeal(uid,status));
        return result;
    }
    @ApiOperation(value = "上传图片", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "凭证", required = true, paramType = "form"),
            @ApiImplicitParam(name = "files", value = "图片参数", required = true, paramType = "form"),

    })
    @PostMapping(value = "/savepic")
    public Result addNoticePic(@RequestParam( "files") MultipartFile file, HttpServletRequest request){
        if (file == null  ){
            throw new RuntimeException("图片为空");
        }

        String oldFileName = file.getOriginalFilename();

        if (file.getSize() > 5000000){
            throw new RuntimeException("文件不能超过5M");
        }
        String suffix = oldFileName.substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if (!ImageFormatEnum.vaildateImage(suffix)){
            throw new RuntimeException("图片格式只能是bmp,gif,jep,jpeg,png,ico");
        }
        String fileName = CommUtil.getUniqueNumberByRandom() + "." + suffix;
        String urls = uploadVoucher2(fileName,file,env);
        return new Result("上传成功",urls);
    }

    public String uploadVoucher2(String fileName, MultipartFile file, String type) {
        try {
            String uri="";
            if(type.equals("dev")){
                uri = "/upload/awbapp/pro/"+"company";
            }else{
                uri =   "/upload/awbapp/test/"+"company";
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
            throw new RuntimeException("上传失败");
        }
    }

}
