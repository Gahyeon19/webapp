package com.example.jwbook.ch04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //redirect 방식으로 이동
    //redirect 방식은 응답 시에 클라이언트에게 이동할 url을 지정해서 클라이언트가 view로 새롭게 요청하게 하는 방식
    req.setAttribute("data", "공유되는 값");
    resp.sendRedirect("/redirect.jsp");
  }
}
