package com.felix.shoppingcentre.aop.aspect;

import com.alibaba.fastjson2.JSON;
import com.felix.shoppingcentre.aop.anotation.UserLoginAnnotation;
import com.felix.shoppingcentre.utils.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;

@Component
@Aspect
@Slf4j
public class UserLogAspect {

    @Pointcut("@annotation(com.felix.shoppingcentre.aop.anotation.UserLoginAnnotation)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = Instant.now().toEpochMilli();
        Object result = point.proceed();
        long throughTime = Instant.now().toEpochMilli() - beginTime;
        saveLog(point, throughTime);

        return result;
    }

    /**
     * save log
     * @param point
     * @param throughTime
     */
    @Async
    private void saveLog(ProceedingJoinPoint point, long throughTime) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        UserLoginAnnotation userLoginAnnotation = method.getAnnotation(UserLoginAnnotation.class);
        String action = userLoginAnnotation.action();
        String className = point.getTarget().getClass().getName();
        String methodName = methodSignature.getName();
        log.info("request {}.{} takes {} millisecond", className, methodName, throughTime);

        Object[] args = point.getArgs();
        String parameters = null;
        if (!ObjectUtils.isEmpty(args)) {
            parameters = JSON.toJSONString(args);
        }

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.info("request {}.{} has parameters{} ", className, methodName, parameters);
        //to-do save it to database
    }
}
