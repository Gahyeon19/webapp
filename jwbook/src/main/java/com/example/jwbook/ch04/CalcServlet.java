package com.example.jwbook.ch04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet("/calc")
public class CalcServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("number1 = {}", req.getParameter("n1"));
    log.info("number2 = {}", req.getParameter("n2"));
    log.info("operator = {}", req.getParameter("op"));
    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html");     //한글 안 깨지게

    int n1 = Integer.parseInt(req.getParameter("n1"));
    int n2 = Integer.parseInt(req.getParameter("n2"));
    String op = req.getParameter("op");
    int result = 0;

    switch (op){
      case "+": result = n1 + n2; break;
      case "-": result = n1 - n2; break;
      case "*": result = n1 * n2; break;
      case "/": result = n1 / n2; break;
    }

    resp.getWriter().println(n1 + " " + op + " " + n2 + " = " + result);

    req.setAttribute("n1", n1);
    req.setAttribute("n2", n2);
    req.setAttribute("op", op);
    req.setAttribute("result", result);
    String path = "/ch04/";
    req.getRequestDispatcher(path + "/calcResult.jsp").forward(req, resp);
  }
}
