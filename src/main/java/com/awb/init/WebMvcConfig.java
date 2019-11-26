package com.awb.init;

import com.awb.component.AuthInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/8/24.
 */


@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Value("${outerPath}")
    private String outerPath;



    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/awb/**")
                .excludePathPatterns("/upload/**")
                .excludePathPatterns("/index.html")
              //  .excludePathPatterns("/shares/**")   //add 3 12 部署测试服务器
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        super.addInterceptors(registry);
    }

    /**
     * 配置静态资源
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/awb/**")
                .addResourceLocations("/awb/");
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/index.html").addResourceLocations("/");
        registry.addResourceHandler("/upload/voucher/**").addResourceLocations("file:"+outerPath+"/upload/voucher/");
        registry.addResourceHandler("/upload/bao/**").addResourceLocations("file:"+outerPath+"/upload/bao/");
        registry.addResourceHandler("/upload/adm/**").addResourceLocations("file:"+outerPath+"/upload/adm/");
        registry.addResourceHandler("/upload/awbapp/**").addResourceLocations("file:"+outerPath+"/upload/awbapp/");
      //  registry.addResourceHandler("/shares/**").addResourceLocations("file:"+outerPath+"/baodantest/");   //add 3 12 部署测试服务器
        super.addResourceHandlers(registry);
    }
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/awb/**")
//                .addResourceLocations("/awb/");
//        registry.addResourceHandler("/swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//        registry.addResourceHandler("/index.html").addResourceLocations("/");
//        registry.addResourceHandler("/upload/voucher/**").addResourceLocations("file:"+outerPath+"/upload/voucher/");
//        super.addResourceHandlers(registry);
//    }

    /**
     * 解决ie responsebody返回json的时候提示下载问题
     *
     * @return
     */
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList();
        MediaType media = new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8"));
        supportedMediaTypes.add(media);
        jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
        return jsonConverter;
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(customJackson2HttpMessageConverter());
//    }

}
