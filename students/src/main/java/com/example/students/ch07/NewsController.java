package com.example.students.ch07;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@WebServlet("/news")
public class NewsController extends HttpServlet {
  private NewsDAO newsDAO;
  private ServletContext context;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    newsDAO = new NewsDAO();
    context = getServletContext();

  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getMethod();
    String action = req.getParameter("action");
    String path = "/ch07/";
    String view = "";

    log.info("action : {}", action);
    log.info("method : {}", method);

    if (action == null) {
      resp.sendRedirect("/news?action=list");
    } else {
      switch (action) {
        case "list":
          view = list(req, resp);
          context.getRequestDispatcher(path + view).forward(req, resp);
          break;
        case "info":
          view = info(req, resp);
          context.getRequestDispatcher(path + view).forward(req, resp);
          break;
        case "create":
          view = create(req, resp);
          if (view.startsWith("redirect:/")){   //"redirect:/news?action=list"
            view = view.substring(10);
            resp.sendRedirect(view);
          }
          break;
      }
    }
  }

  private String list(HttpServletRequest req, HttpServletResponse resp) {
    List<News> news = newsDAO.findAll();
    req.setAttribute("news", news);
    return "newsList.jsp";
  }

  private String info(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
    News news = newsDAO.findById(Integer.parseInt(req.getParameter("id")));
    req.setAttribute("news", news);
    return "newsInfo.jsp";
  }

  private String create(HttpServletRequest req, HttpServletResponse resp) {
    News news = new News();
    news.setTitle(req.getParameter("title"));
    news.setImg(req.getParameter("img"));
    news.setContent(req.getParameter("content"));
    try {
      newsDAO.addNews(news);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/news?action=list";
  }

}
