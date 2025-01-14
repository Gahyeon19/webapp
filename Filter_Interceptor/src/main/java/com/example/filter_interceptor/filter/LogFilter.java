package com.example.filter_interceptor.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //사용자의 요청에 대해 로그 남기기 (어떤 핸들러가 처리하고 있는지, 어떤 화면으로 들어온 요청인지)
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        log.info("Request == > {}", requestURI);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
