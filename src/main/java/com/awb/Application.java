package com.awb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring Boot 应用启动类
 *
 * Created by bysocket on 16/4/26.
 */
@ComponentScan(basePackages = {"com.awb.*"})
@SpringBootApplication
@MapperScan("com.awb.mapper")
@EnableFeignClients
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
