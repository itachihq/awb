package com.awb.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
/**
 * Created by asus on 2018/8/22.
 */


@Aspect
@Component
public class AspectService {

    private static final int maxRetries = 5;


    @Pointcut("@annotation(com.awb.anotations.OptimisticLocking)")
    public void retryOnOptFailure() {}

    @Around("retryOnOptFailure()")
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        int numAttempts = 0;
        do {
            numAttempts++;
            try {
                return pjp.proceed();
            } catch (OptimisticLockingFailureException exception) {
                if (numAttempts == maxRetries){
                    throw exception;
                }
            }
        }while (numAttempts < this.maxRetries);
        return null;
    }


}
