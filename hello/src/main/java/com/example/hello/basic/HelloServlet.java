package com.example.hello.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j  //log 남기기
@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println(req.getMethod());      //GET
    System.out.println(req.getProtocol());    //HTTP/1.1

    //warning, error, info 만 출력됨
    log.debug("debug : get method = {}", req.getMethod());  //계산 먼저 한 다음 출력할 게 없으면 출력 안함
    log.debug("debug : get method = " + req.getMethod());   //출력할 게 없어도 일단 출력한 다음 계산
    log.trace("trace : get method = {}", req.getMethod());

    log.warn("warning : get method = {}", req.getMethod());
    log.error("error : get method = {}", req.getMethod());
    log.info("info : get method = {} , protocol = {}", req.getMethod(), req.getProtocol());
    log.info("info : get method = {} , protocol = {}", req.getMethod(), req.getProtocol());

  }
}
