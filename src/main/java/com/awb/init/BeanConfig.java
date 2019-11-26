package com.awb.init;

import com.awb.component.DateConvert;
import com.awb.component.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

/**
 * Created by asus on 2018/8/22.
 */


@Configuration
public class BeanConfig {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Value("${redis_ip}")
    private String ip ;
    @Value("${redis_password}")
    private String  password ;
    @Value("${redis_port}")
    private String port ;
    @Value("${redis_preKey}")
    private String  preKey;

    @Bean( name = "redisCache" )
    public RedisCache redisCache() throws Exception {
        return new RedisCache(ip,Integer.valueOf(port),password,preKey);
    }

    /**
     * 增加字符串转日期的功能
     */

    @PostConstruct
    public void initEditableAvlidation() {

        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer)handlerAdapter.getWebBindingInitializer();
        if(initializer.getConversionService()!=null) {
            GenericConversionService genericConversionService = (GenericConversionService)initializer.getConversionService();

            genericConversionService.addConverter(new DateConvert());

        }

    }



}
