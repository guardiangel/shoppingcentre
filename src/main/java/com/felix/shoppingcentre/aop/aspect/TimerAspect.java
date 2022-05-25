package com.felix.shoppingcentre.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Instant;

@Component
@Aspect
@Slf4j
public class TimerAspect {

    @Around("execution(* com.felix.shoppingcentre.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        long beginTime = Instant.now().toEpochMilli();
        Object result = point.proceed();
        long throughTime = Instant.now().toEpochMilli() - beginTime;
        log.info("{}.{} spend {} ms", point.getTarget().getClass().getName(),
                ((MethodSignature) point.getSignature()).getMethod().getName(),
                throughTime);
        return result;
    }

}
