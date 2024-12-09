package com.viktoria.spring.aop;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("@within(org.springframework.stereotype.Service)")
    public Object addLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        log.info(">>>BEFORE<<< Method: {}, accepts parameters as input: {}", methodName, Arrays.toString(args));
        try {
            Object result = joinPoint.proceed();
            log.info(">>>AFTER RETURNING<<< Method: {}, return value: {}", methodName, result);
            return result;
        } catch (Throwable ex) {
            log.info(">>>AFTER THROWING<<< Method: {}, returned an exception: {}", methodName, ex.getMessage());
            throw ex;
        }
    }
}
