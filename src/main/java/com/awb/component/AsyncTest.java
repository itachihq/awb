package com.awb.component;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by asus on 2018/8/24.
 */


@Component
public class AsyncTest {

    @Async
    public void asyncTest(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试异步任务");
    }
}
