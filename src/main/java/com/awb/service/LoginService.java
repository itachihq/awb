package com.awb.service;

import java.util.Map;

/**
 * Created by Administrator on 2018/12/28.
 */
public interface LoginService {
     Map loginIn(String userName, String password,Integer type) ;
     Map loginIn(String userName, String password,Integer type,String token) ;
     Map adminLoginIn(String userName, String password) ;
     Map login(String userName) ;
     void insertAdmuser(String phone,String name,String password,String code,String tgm);
     String getCode(String phone);
     String forgetCode(String phone);
     void loginOut(String token);
     void shareRegist(String phone,String name,String tgm,String code);
     void registTest();
}
