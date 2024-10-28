package com.example.students.ch07;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter("/news")
public class EncodingFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;  //다운캐스팅
//    if (true) {   //login 하지 않은 상황
//      log.info(" ####### 로그인 하지 않음. 종료됨");
//
//    } else {    //login 함
      req.setCharacterEncoding("UTF-8");
      log.info(" ####### Character encoding is {}", req.getCharacterEncoding());
      chain.doFilter(request, response);
//    }


  }
}
