package com.example.jwbook.ch04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/forward")
public class ForwardServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //forward 방식으로 이동
    //forward 방식은 기존 request 와 response 가 유지된 상태로 이동된다.
    String path = "/ch04/";
    req.setAttribute("data", "공유되는 값");
    req.getRequestDispatcher(path + "/forward.jsp").forward(req, resp);
  }
}
