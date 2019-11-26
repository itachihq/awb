package com.awb.component;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * 全局日期处理类
 * Convert<T,S>
 *         泛型T:代表客户端提交的参数 String
 *         泛型S:通过convert转换的类型

 */
public class DateConvert implements Converter<String, Date> {



    @Override
    public Date convert(String stringDate) {
        return new Date(Long.valueOf(stringDate)*1000);
    }


}