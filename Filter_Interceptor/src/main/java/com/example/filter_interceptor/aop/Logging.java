package com.example.filter_interceptor.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//@Aspect
@Slf4j
//@Component
public class Logging {

//    @Before("execution(* com.example.filter_interceptor.controller.*(..))")     //controller 폴더 안의 모든 class, method 에 대해
//    @Before("execution(* com.example.filter_interceptor.controller.LoginController.*(..))")     //패키지 안에 들어있는 모든 class 와 method 들에 대해
    public void leavingLog(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.info("===== className : {}", className);
        log.info("===== methodName : {}", methodName);
        log.info("실행되기 전");
    }
}
