package com.example.filter_interceptor.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {        //login 여부를 확인하는 필터
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);    //기존 세션이 있으면 있는 세션을 반환. 없으면 null 반환
        if (session != null) {
            log.info("===로그인 되었습니다.===");
            chain.doFilter(request, response);
        } else {
            log.info("======로그인 실패======");
            chain.doFilter(request, response);
        }
    }
}
