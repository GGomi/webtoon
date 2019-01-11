package com.essri.webtoon.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;


// aop 꿀팁
// http://heekim0719.tistory.com/141
@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class AspectLogger {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.essri.webtoon.web.controller.WebRestController.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        logger.info("########################################################");
        logger.info("### REQUEST_CLASS: {} ",  args.getClass().toString());
        logger.info("### REQUEST_SIGNATURE: {} ",  joinPoint.getSignature().toString());
        if(Arrays.toString(args).length() != 0) {
            logger.info("# REQUEST_PARAMS: {}",Arrays.toString(args));
        }
        

        // 기준
        Object res = joinPoint.proceed();

        logger.info("########################################################");
        logger.info("### RESPONSE_CLASS_TYPE: {} ",  res.getClass().toString());
        logger.info("### RESPONSE_PARAMS: {}",res.toString());
        logger.info("########################################################");
        return res;
    }
}


