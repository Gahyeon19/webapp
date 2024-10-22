package com.example.jwbook.ch05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
@Slf4j
public class ProductController extends HttpServlet {
  private ProductService ps = new ProductService();
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");
    String view = "";
    String path = "/ch05/";
    log.info("action : {}", action);

    if (action == null) {
      resp.sendRedirect("/products?action=list");
//      getServletContext().getRequestDispatcher().forward(req, resp);
    } else {
      switch (action) {
        case "list":
          view = list(req, resp, path);
          break;
        case "info":
          view = info(req, resp, path);
          break;
        default:
          resp.sendError(HttpServletResponse.SC_NOT_FOUND);
          break;
      }
      getServletContext().getRequestDispatcher(path + view).forward(req, resp);
    }


  }

  private String info(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
    long id = Long.parseLong(req.getParameter("id"));
    Product product = ps.findById(id);
    req.setAttribute("product", product);
    req.getRequestDispatcher(path + "productInfo.jsp").forward(req, resp);
    return "/productInfo.jsp";
  }

  private String list(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
    List<Product> products = ps.findAll();
    req.setAttribute("products", products);
    req.getRequestDispatcher(path + "productList.jsp").forward(req, resp);
    return "/productList.jsp";
  }
}
