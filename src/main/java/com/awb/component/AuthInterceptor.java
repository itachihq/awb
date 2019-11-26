package com.awb.component;


import com.awb.state.RedisPre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ICache iCache;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long begin_nao_time = System.nanoTime();
request.setAttribute("begin_nao_time", begin_nao_time);
        //允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        String uri = request.getRequestURI();
        if(uri.equals("/app/forgetpassword") ||uri.contains("/other") ||uri.equals("/app/login") ||uri.equals("/admin/login")||uri.equals("/app/regist") ||uri.equals("/other/list")||uri.equals("/excel/test2")||uri.equals("/web/shop/listshareshop")||uri.equals("/web/shop/getopenid")||uri.equals("/web/shop/getpreid")||uri.equals("/web/shop/insertshareshop")||uri.equals("/web/shopdetail")||uri.equals("/web/shop/myshop")||uri.equals("/mobile/wx/success")){//|| uri.equals("/admin/balance/seldetail")
            return true ;
        }
        String token = request.getParameter("token");//userid
        String userid=request.getParameter("uid");
        if (StringUtils.isEmpty(token)&&StringUtils.isEmpty(userid)){
            throw new RuntimeException("请登录");
        }
//        if (iCache.exists(RedisPre.TOKEN_LOCK.getPre()+uri+token)){
//            iCache.putString(RedisPre.TOKEN_LOCK.getPre()+uri+token,"",5);
//            throw new RuntimeException("操作过于频繁,稍后再试");
//        }
//        iCache.putString(RedisPre.TOKEN_LOCK.getPre()+uri+token,"",1);
        String uid = iCache.getString(RedisPre.UID.getPre() + token);
        if (StringUtils.isEmpty(uid)&&StringUtils.isEmpty(userid)){
            throw new RuntimeException("请登录");
        }

//        String roleId = iCache.getString(RedisPre.ROLE.getPre() + uid);
//        if (StringUtils.isEmpty(roleId)){
//            throw new RuntimeException("用户角色身份异常");
//        }
//        List<String> urls = iCache.get(RedisPre.URL_AUTH.getPre()+roleId,List.class);
//        if (CollectionUtils.isEmpty(urls) || ! urls.contains(uri)){
//            throw new RuntimeException("您无权访问");
//        }
//        String userType = iCache.getString(RedisPre.U_LEVEL.getPre() + uid);
//        if (StringUtils.isEmpty(userType)){
//            throw new RuntimeException("用户身份异常");
//        }
        request.setAttribute("uid",uid);
        iCache.putString(RedisPre.UID.getPre()+token,uid,3600);
        return true ;
    }

//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
//            throws Exception {
//        long begin_nao_time = (Long) request.getAttribute("begin_nao_time");
//        long interval = System.nanoTime() - begin_nao_time;
//        System.out.println("interval="+interval);
//
//    }

}