package com.example.products;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet("/products")
public class ProductController extends HttpServlet {
  ProductDAO service;

  @Override
  public void init(ServletConfig config) throws ServletException {
    service = new ProductDAO();
    service.open();
  }

  @Override
  public void destroy() {
    service.close();
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");
    String method = req.getMethod();
    String view = "";


    if (action == null) {
      resp.sendRedirect("/products?action=list");
    } else {
      switch (action) {
        case "list":
          view = list(req, resp);
          req.getRequestDispatcher(view).forward(req, resp);
          break;
        case "info":
          view = info(req, resp);
          req.getRequestDispatcher(view).forward(req, resp);
          break;
        case "insert":
          view = insert(req, resp);
          if (method.equals("GET")) {
            req.getRequestDispatcher(view).forward(req, resp);
          } else if (method.equals("POST")) {
            resp.sendRedirect(view);
          }
          break;
        case "update":
          view = update(req, resp);
          if (method.equals("GET")) {
            req.getRequestDispatcher(view).forward(req, resp);
          } else if (method.equals("POST")) {
            resp.sendRedirect(view);
          }
          break;
        case "delete":
          view = delete(req, resp);
          resp.sendRedirect(view);
          break;
        default:
          resp.sendError(HttpServletResponse.SC_NOT_FOUND);
          break;
      }
    }
  }

  private String list(HttpServletRequest req, HttpServletResponse resp) {
    List<Product> products = service.findAll();
    req.setAttribute("products", products);
    return "productList.jsp";
  }

  private String info(HttpServletRequest req, HttpServletResponse resp) {
    Product product = service.findById(Integer.parseInt(req.getParameter("id")));
    req.setAttribute("product", product);
    return "productInfo.jsp";
  }

  private String insert(HttpServletRequest req, HttpServletResponse resp) {
    String method = req.getMethod();
    String view = "";

    if (method.equals("POST")) {
      Product product = new Product(
          0,
          req.getParameter("name"),
          Integer.parseInt(req.getParameter("price")),
          req.getParameter("maker"),
          req.getParameter("date")
      );
      service.insert(product);
      view = "/products?action=list";
    } else if (method.equals("GET")) {
      view = "productInsert.jsp";
    }
    return view;
  }

  private String update(HttpServletRequest req, HttpServletResponse resp) {
    String method = req.getMethod();
    String view = "";

    if (method.equals("POST")) {
      Product product = new Product(
          Integer.parseInt(req.getParameter("id")),
          req.getParameter("name"),
          Integer.parseInt(req.getParameter("price")),
          req.getParameter("maker"),
          req.getParameter("date")
      );
      service.update(product);
      view = "/products?action=info&id=" + product.getId();
    } else if (method.equals("GET")) {
      Product product = service.findById(Integer.parseInt(req.getParameter("id")));
      req.setAttribute("product", product);
      view = "productUpdate.jsp";
    }
    return view;
  }

  private String delete(HttpServletRequest req, HttpServletResponse resp) {
    service.delete(Integer.parseInt(req.getParameter("id")));
    return "/products?action=list";
  }
}
