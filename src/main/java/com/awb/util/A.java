package com.awb.util;



import com.awb.state.RedisPre;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/21.
 */
public class A {
    public static boolean comparedate(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {

            Date data2 = new Date();
            if(date.compareTo(df.format(data2))>=0||df.format(new Date()).contains(date.substring(0,11))){
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(RedisPre.UID.getPre());
        System.out.println(comparedate("2019-02-18 00:00:00"));
        System.out.println( CommUtil.getUniqueNumberByRandom());
    }
}
