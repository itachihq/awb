package com.awb.controller;

import com.awb.entity.param.BalanceParm2;
import com.awb.entity.param.ChargeOrderParm2;
import com.awb.entity.vo.*;
import com.awb.mapper.UserMapper;
import com.awb.model.User;
import com.awb.model.UserExample;
import com.awb.service.OtherService;
import com.awb.util.poi.ExcelData;
import com.awb.util.poi.ExcelUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/7/29.
 */
@RestController

@RequestMapping("other")
public class ExcelController {
    @Autowired
 private OtherService otherService;
//
@ApiOperation(value = "余额变化导出", httpMethod = "POST", response = Result.class)
@ApiImplicitParams({
        @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "form"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "form"),
        @ApiImplicitParam(name = "phone", value = "手机号", required = false, paramType = "form")
})
    @RequestMapping("/exportbalance")
    public void exportBalance(HttpServletResponse response,BalanceParm2 balanceParm2){
        int rowIndex = 0;
        try{
        List<AdminBalanceVo2> list = otherService.exportBalance(balanceParm2);
        ExcelData data = new ExcelData();
        data.setName("charge");
        List<String> titles = new ArrayList();
        titles.add("姓名");
        titles.add("手机号");
        titles.add("金额");
       // titles.add("类型");
        titles.add("描述");
        titles.add("备注");
        titles.add("时间");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
            AdminBalanceVo2 userInfo = list.get(i);
            List<Object> row = new ArrayList();
            row.add(userInfo.getName());
            row.add(userInfo.getPhone());
            row.add(userInfo.getAmt());
          ///  row.add(userInfo.getDetailType());
            row.add(userInfo.getDescription());
            row.add(userInfo.getRemark());
            row.add(userInfo.getCreatetime());
            rows.add(row);
        }
        data.setRows(rows);
            ExcelUtils.exportExcel(response,"余额变化表",data);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
    @ApiOperation(value = "艾豆米变化导出", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "form"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "form"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = false, paramType = "form")
    })
    @RequestMapping("/exportadm")
    public void exportAdm(HttpServletResponse response,BalanceParm2 balanceParm2){
        int rowIndex = 0;
        try{
            List<AdminBalanceVo2> list = otherService.exportAdm(balanceParm2);
            ExcelData data = new ExcelData();
            data.setName("charge");
            List<String> titles = new ArrayList();
            titles.add("姓名");
            titles.add("手机号");
            titles.add("数量");
          //  titles.add("类型");
            titles.add("描述");
            titles.add("备注");
            titles.add("时间");
            data.setTitles(titles);
            List<List<Object>> rows = new ArrayList();
            for(int i = 0, length = list.size();i<length;i++){
                AdminBalanceVo2 userInfo = list.get(i);
                List<Object> row = new ArrayList();
                row.add(userInfo.getName());
                row.add(userInfo.getPhone());
                row.add(userInfo.getNum());
           //     row.add(userInfo.getDetailType());
                row.add(userInfo.getDescription());
                row.add(userInfo.getRemark());
                row.add(userInfo.getCreatetime());
                rows.add(row);
            }
            data.setRows(rows);
            ExcelUtils.exportExcel(response,"艾豆米数量变化表",data);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @ApiOperation(value = "提现列表导出", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "form"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "form"),
            @ApiImplicitParam(name = "status", value = "0未审核1审核通过2拒绝", required = false, paramType = "form"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = false, paramType = "form")
    })
    @RequestMapping("/exporttakemoney")
    public void exportTakemoney(HttpServletResponse response,ChargeOrderParm2 chargeOrderParm2){
        int rowIndex = 0;
        try{
            List<ChargeOrderVo2> list = otherService.exportTakemoney(chargeOrderParm2);
            ExcelData data = new ExcelData();
            data.setName("charge");
            List<String> titles = new ArrayList();
            titles.add("姓名");
            titles.add("手机号");
            titles.add("金额");
            titles.add("状态");
            titles.add("申请时间");
            titles.add("审核时间");
            data.setTitles(titles);
            List<List<Object>> rows = new ArrayList();
            for(int i = 0, length = list.size();i<length;i++){
                ChargeOrderVo2 userInfo = list.get(i);
                List<Object> row = new ArrayList();
                row.add(userInfo.getName());
                row.add(userInfo.getPhone());
                row.add(userInfo.getMoney());
                if("0".equals(userInfo.getStatus())){
                    row.add("待审核");
                }else if("1".equals(userInfo.getStatus())){
                    row.add("已通过");
                }else if("2".equals(userInfo.getStatus())){
                    row.add("已拒绝");
                }
                row.add(userInfo.getCreateTime());
                row.add(userInfo.getUpdateTime());
                rows.add(row);
            }
            data.setRows(rows);
            ExcelUtils.exportExcel(response,"提现列表",data);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
//
//    @RequestMapping("/exportshoporder")
//    public void test4(HttpServletResponse response){
//        int rowIndex = 0;
//        List<ShopOrderVo2> list = userInfoService.exportShopOrder();
//        ExcelData data = new ExcelData();
//        data.setName("charge");
//        List<String> titles = new ArrayList();
//        titles.add("扣款人姓名");
//        titles.add("扣款人手机号");
//       // titles.add("扣款人级别");
//        titles.add("收件人姓名");
//        titles.add("收件人手机号");
//        titles.add("购买数量");
//        titles.add("扣款总数量");
//        titles.add("商品名称");
//        titles.add("收件地址");
//        titles.add("备注");
//        titles.add("时间");
//        data.setTitles(titles);
//        List<List<Object>> rows = new ArrayList();
//        for(int i = 0, length = list.size();i<length;i++){
//            ShopOrderVo2 userInfo = list.get(i);
//            List<Object> row = new ArrayList();
//            row.add(userInfo.getName());
//            row.add(userInfo.getPhone());
//            row.add(userInfo.getBuyname());
//            row.add(userInfo.getBuyphone());
//            row.add(userInfo.getNum());
//            row.add(userInfo.getTotalPrice());
//            row.add(userInfo.getShopName());
//            row.add(userInfo.getAddress());
//            row.add(userInfo.getRemark());
//            row.add(userInfo.getCreateTime());
//            rows.add(row);
//        }
//        data.setRows(rows);
//        try{
//            ExcelUtils.exportExcel(response,"商城订单列表",data);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @RequestMapping("/exportuservo")
//    public void test5(HttpServletResponse response,@RequestParam("file") MultipartFile file){
//      try{
//List<User> demoList = new ArrayList<User>();
//
//Workbook book = null;
////判断是xls还是xlsx
//
//try {
//book = new XSSFWorkbook(file.getInputStream());
//} catch (Exception ex) {
//book = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
//}
//
//
//
////获取一共有多少sheet,遍历
//int numberOfSheets = book.getNumberOfSheets();
//for (int i=0; i<numberOfSheets; i++) {
//Sheet sheet = book.getSheetAt(i);
////获取sheet中有多少行，遍历每一行
//int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
//for (int j=0;j<physicalNumberOfRows;j++){
//if (j == 0) {
//continue;//标题行
//}
//   // User demo = new User();
//Row row = sheet.getRow(j);//获得当前行数据
//String phone=  row.getCell(0).getStringCellValue();
//String name = row.getCell(1).getStringCellValue();
//UserExample userExample=new UserExample();
//userExample.createCriteria().andPhoneEqualTo(phone);
//
//
//}
//}
//}catch (Exception e){
//
//e.printStackTrace();
//}
//
//    }
}
//if("男".equals(sex)){
//        demo .setSex(1); //性别
//        }else if("女".equals(sex)){
//        demo .setSex(0); //性别
//        }