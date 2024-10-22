package com.example.jwbook.ch04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("number1 = {}", req.getParameter("n1"));
    log.info("number2 = {}", req.getParameter("n2"));
    log.info("operator = {}", req.getParameter("op"));
    resp.getWriter().append("Served at : ").append(req.getContextPath());
  }
}
