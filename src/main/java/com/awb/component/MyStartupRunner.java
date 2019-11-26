//package com.awb.component;
//
//import com.awb.model.other.MealPrice;
//import com.awb.service.other.MealPriceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
///**
// * Created by Administrator on 2019/1/23.
// * springboot 启动加载数据到redis缓存
// */
//@Component
//public class MyStartupRunner implements CommandLineRunner {
//    @Autowired
//    private ICache iCache ;
//    @Autowired
//    private MealPriceService mealPriceService;
//    @Override
//    public void run(String... strings) throws Exception {
//      MealPrice mealPrice= mealPriceService.selectByNodeLever(50,"套餐1");
//      if(mealPrice!=null){
//        iCache.put("king_ingoods",  mealPrice.getInPrice());
//      }
//        System.out.println("ss"+iCache.getString("king_ingoods"));
//    }
//}
