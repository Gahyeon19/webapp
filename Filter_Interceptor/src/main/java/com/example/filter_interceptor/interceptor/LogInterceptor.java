package com.example.filter_interceptor.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("Inter, preHandle : request ==> {}", request.getRequestURI());

        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            log.info("Inter, preHandle : handler class ==> {}", method.getDeclaringClass().getSimpleName());
            log.info("Inter, preHandle : handler method ==> {}", method.getName());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("Inter, postHandle : request ==> {}", request.getRequestURI());
        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            log.info("Inter, postHandle : handler class ==> {}", method.getDeclaringClass().getSimpleName());
            log.info("Inter, postHandle : handler method ==> {}", method.getName());
        }
//        log.info("Inter, postHandle : modelAndView ==> {}", modelAndView.getViewName());
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
