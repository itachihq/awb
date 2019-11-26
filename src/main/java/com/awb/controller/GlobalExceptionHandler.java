package com.awb.controller;

import com.awb.entity.vo.Result;
import com.awb.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常处理
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value=Exception.class)
    public Result allExceptionHandler(Exception exception) throws Exception
    {
        exception.printStackTrace();
        String message = exception.getMessage();
        logger.error(exception.getClass().getSimpleName()+"-"+message);
        logger.error("reson="+exception.getMessage(), exception);
        if (StringUtils.isEmpty(message) || message.length() > 50 || message.startsWith(Constant.NOT_NOTICE_MARK)){
            message =exception.getMessage().substring(0,50);// "服务出错" ;
        }
        return new Result(false,message);
    }

}
