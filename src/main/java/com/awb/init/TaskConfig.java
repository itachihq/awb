package com.awb.init;

import com.awb.component.AsyncTest;


import com.awb.entity.vo.TreeVO;
import com.awb.mapper.OtherMapperExt;
import com.awb.mapper.UserMapper;
import com.awb.model.User;
import com.awb.model.UserExample;
import com.awb.service.UserService;
import com.awb.task.TestTask;
import com.awb.util.DateNewUtil;
import com.awb.util.DecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by asus on 2018/8/24.
 */


@Configuration
@Transactional
public class TaskConfig {
    @Autowired
    private OtherMapperExt otherMapperExt;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private TestTask testTask ;
    @Autowired
    private AsyncTest asyncTest ;
    // @Scheduled(cron="0 55 23 * * ?" )
//@Scheduled(cron="0 0 1 1 * ?" )

    public void pushSMSRunTime1() throws Exception{

    System.out.println("xiaoqing");
    }
//    public  double select(String id) {
//    Double teamMoney = 0.00;
//        try {
//            List<TreeVO> treeVOList = otherMapperExt.selectByParentId(id);
//            for(TreeVO tree:treeVOList){
//                teamMoney=DecimalUtil.calcNumber(teamMoney,tree.getMoney(),"+").doubleValue();
//            }
//            return teamMoney;
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }

    public  String  select(String id,String teamMoney) {
        String reMoney=teamMoney;
        try {
            List<TreeVO> treeVOList = otherMapperExt.selectByParentId(id);
            for(TreeVO tree:treeVOList){
                reMoney=DecimalUtil.calcNumber(reMoney,tree.getMoney(),"+").toString();
            }
            return reMoney;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    //计算团队业绩
    public String  coumtTeammoney(List<User> li,String money){
        String s=money;
        try {
                for(User user:li){
                  s=DecimalUtil.calcNumber(s,user.getMoney(),"+",2).toString();
                }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return s;
    }

    private void fuck2(User sysUser, List<User> li) throws Exception {
    UserExample userExample=new UserExample();
    userExample.createCriteria().andParentidEqualTo(sysUser.getId());

        List<User> sons =userMapper.selectByExample(userExample);
        if (sons == null) {
            return;
        } else {
            for (User n : sons) {
                if(n.getNodelever()==50){
                    li.add(n);
                    continue;
                }
                fuck2(n, li);
            }
        }
    }

    private void fuck1(User sysUser, List<User> li) throws Exception {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andParentidEqualTo(sysUser.getId());
        List<User> sons =userMapper.selectByExample(userExample);
        if (sons == null) {
            return;
        } else {
            for (User n : sons) {
                li.add(n);
                fuck1(n, li);
            }
        }
    }
    //@Scheduled(cron="0 25 16 10 * ?" )
//@Scheduled(initialDelay=5000,fixedDelay=1000000000)
 public void pushSMSRunTime() throws Exception {
        try {
            UserExample userExample=new UserExample();
                userExample.createCriteria().andNodeleverEqualTo(50);
            List<User> list=userMapper.selectByExample(userExample);
            Date date=new Date();
            String mon=DateNewUtil.stampToDate(date);
            for (User user:list){
                List<User> userList = new ArrayList<>();
                fuck1(user,userList);
               String umoney= coumtTeammoney(userList,user.getMoney()+"");//团队总额
               Double usermoney= countMoney(Double.valueOf(umoney));
                List<User> li = new ArrayList<>();
                fuck2(user, li);
                String   money="0";
                double midmoney=0;
                for(User u1:li){
                    List<User> li2 = new ArrayList<>();
                    fuck1(u1,li2);
                    String u1money=coumtTeammoney(li2,u1.getMoney()+"");
                    midmoney=  countMoney(Double.valueOf(u1money));
                    money=DecimalUtil.calcNumber(midmoney,money,"+",2).toString();
                }
                String teammoney=DecimalUtil.calcNumber(usermoney,money,"-",2).toString();
                userService.updateAll(user,Double.valueOf(umoney),date,mon,teammoney);
            }
//                for(User node:list){
//                    List<User> li = new ArrayList<>();
//                    fuck2(node, li);
//                    double  money=0;
//                    for(User n:li){
//                        if(n.getMoney()<=0){
//                            continue;
//                        }
//                        money+=countMoney(n.getMoney());
//                    }
//
//                    String   amt="0";
//                    double price=0;
//                    String amt1="0";
//                    amt1=select(node.getId(),node.getMoney()+"");
//                    price=countMoney(Double.valueOf(amt1));
//                    amt=DecimalUtil.calcNumber(price,money,"-").toString();
//                    userService.updateAll(node,Double.valueOf(amt1),date,mon,amt);
//                }
//                for (User ss:list){
//                    ss.setMoney(0.00);
//                    userMapper.updateByPrimaryKeySelective(ss);
//                }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    private double    countMoney(double money) throws Exception {
        double price=0;
        if(money<1000000){
            price=0;
        }else if(money<2000000){
            price=DecimalUtil.calcNumber(money,0.01,"*",2).doubleValue();
        }else if(money<4000000){
            price=DecimalUtil.calcNumber(money,0.02,"*",2).doubleValue();
        }else if(money<6000000){
            price=DecimalUtil.calcNumber(money,0.04,"*",2).doubleValue();
        }else if(money<8000000){
            price=DecimalUtil.calcNumber(money,0.06,"*",2).doubleValue();
        }else if(money<10000000){
            price=DecimalUtil.calcNumber(money,0.08,"*",2).doubleValue();
        }else {
            price=DecimalUtil.calcNumber(money,0.1,"*",2).doubleValue();
        }
//        double price=0;
//        if(money<1000000){
//            price=0;
//        }else if(money<2000000){
//            price=DecimalUtil.calcNumber(money,0.01,"*",2).doubleValue();
//        }else if(money<4000000){
//            price=DecimalUtil.calcNumber(money,0.02,"*",2).doubleValue();
//        }else if(money<6000000){
//            price=DecimalUtil.calcNumber(money,0.04,"*",2).doubleValue();
//        }else if(money<8000000){
//            price=DecimalUtil.calcNumber(money,0.06,"*",2).doubleValue();
//        }else if(money<10000000){
//            price=DecimalUtil.calcNumber(money,0.08,"*",2).doubleValue();
//        }else {
//            price=DecimalUtil.calcNumber(money,0.1,"*",2).doubleValue();
//        }
        return  Double.valueOf( String.format("%.2f", price));
    }
}
