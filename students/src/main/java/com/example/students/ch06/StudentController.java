package com.example.students.ch06;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Slf4j
@WebServlet("/students")
public class StudentController extends HttpServlet {
  StudentDAO service;

  @Override
  public void init(ServletConfig config) throws ServletException {
    service = new StudentDAO();
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
    String path = "/ch06/";
    log.info("action: {}", action);
    log.info("method: {}", method);

    if (action == null) {
      resp.sendRedirect("/students?action=list");
    } else {
      switch (action) {
        case "list":
          view = list(req, resp);
          req.getRequestDispatcher(path + view).forward(req, resp);
          break;
        case "insert":
          //method : get
          //method : post
          view = insert(req, resp);
          if (method.equals("GET")) {
            req.getRequestDispatcher(path + view).forward(req, resp);
          } else if (method.equals("POST")) {
            resp.sendRedirect(view);
          }
          break;
        case "info":
          view = info(req, resp);
          req.getRequestDispatcher(path + view).forward(req, resp);
          break;
        case "update":
          view = update(req, resp);
          if (method.equals("GET")) {
            req.getRequestDispatcher(path + view).forward(req, resp);
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
      }
    }
    //    Insert ==> service.insert(); ==> PRG 패턴
    //    service.findAll();
  }

  private String list(HttpServletRequest req, HttpServletResponse resp) {
    List<Student> students = service.findAll();
    req.setAttribute("students", students);
    return "studentList.jsp";
  }

  private String info(HttpServletRequest req, HttpServletResponse resp) {
    Student student = service.findById(Integer.parseInt(req.getParameter("id")));
    req.setAttribute("student", student);
    return "studentInfo.jsp";
  }

  private String insert(HttpServletRequest req, HttpServletResponse resp) {
    String method = req.getMethod();
    String view = "";
    if (method.equals("POST")) {    // insert 페이지에서 등록 버튼 누르면
      Student student = new Student(
          0,
              req.getParameter("name"),
              req.getParameter("univ"),
              Date.valueOf(req.getParameter("birth")),
              req.getParameter("email")
      );
      service.insert(student);
      view = "/students?action=list";
    } else if (method.equals("GET")) {
      view = "studentInsert.jsp";
    }
    return view;
  }

  private String update(HttpServletRequest req, HttpServletResponse resp) {
    String method = req.getMethod();
    String view = "";

    if (method.equals("POST")) {
      Student student = new Student(
          Integer.parseInt(req.getParameter("id")),
          req.getParameter("name"),
          req.getParameter("univ"),
          Date.valueOf(req.getParameter("birth")),
          req.getParameter("email")
      );
      service.update(student);
      view = "/students?action=info&id=" + student.getId();
    } else if (method.equals("GET")) {
      Student student = service.findById(Integer.parseInt(req.getParameter("id")));
      req.setAttribute("student", student);
      view = "studentUpdate.jsp";
    }
    return view;
  }

  private String delete(HttpServletRequest req, HttpServletResponse resp) {
    service.delete(Integer.parseInt(req.getParameter("id")));
    return "/students?action=list";
  }
}
