package com.example.hello.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/response")
public class ResponseServlet extends HttpServlet {
  //public service 를 쓰면 다운캐스팅 해서 써줘야하므로 protected 추천
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    resp.setCharacterEncoding("UTF-8");
    resp.setStatus(HttpServletResponse.SC_OK);  //200
    resp.getWriter().write("<h1>Hello World! </h1>");
    resp.getWriter().write("<p>Response 입니다.</p>");

  }
}
