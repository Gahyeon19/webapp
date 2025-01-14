package com.example.filter_interceptor.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
@Slf4j
public class PerformanceMeasure {

//    @Around("execution(* com.example.filter_interceptor..*(..))")
    public void performanceMeasure(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("####### Aspect performanceMeasure: {} ms", end - start);
    }
}
