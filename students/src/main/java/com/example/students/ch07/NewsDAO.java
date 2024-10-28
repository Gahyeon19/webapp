package com.example.students.ch07;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class NewsDAO {
  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  final String JDBC_URL = "jdbc:mysql://localhost:3306/backend?serverTimezone=Asia/Seoul";

  // DB connection 열기
  public Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(JDBC_URL, "root", "1111");
    } catch (ClassNotFoundException | SQLException e) {
      throw new RuntimeException(e);
    }
    return connection;
  }

  // DB connection 닫기

  // CRUD
  // [뉴스 목록 가져오기]
  public List<News> findAll() {
    Connection connection = getConnection();
    List<News> newsList =  new ArrayList<>();

    // 1. sql query
    String sql = "select id, title, date_format(date, '%Y-%m-%d %h:%m:%s') as cdate from news";

    // 2. prepared statement(pstmt)
    PreparedStatement pstmt = null;
    try {
      pstmt = connection.prepareStatement(sql);
      // 3. pstmt 를 이용하여 쿼리 실행
      ResultSet rs = pstmt.executeQuery();

      // 4. 결과를 리스트에 담는다.
      while (rs.next()) {
        News news = new News();
        news.setId(rs.getInt("id"));
        news.setTitle(rs.getString("title"));
        news.setDate(rs.getString("cdate"));

        newsList.add(news);
      }
      // 5. 사용한 리소스를 닫아준다.
      pstmt.close();
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    // 5. 반환한다.
    return newsList;
  }

  // [뉴스 id로 뉴스 가져오기]
  public News findById(int id) {
    Connection connection = getConnection();
    News news = new News();

    String sql = "select id, title, img, date_format(date, '%Y-%m-%d %h:%m:%s') as cdate, content from news where id=?";

    PreparedStatement pstmt = null;

    try {
      pstmt = connection.prepareStatement(sql);
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();


      if (rs == null) {
        log.info("result: {}", rs);
      } else {
        rs.next();
        news.setId(rs.getInt("id"));
        news.setTitle(rs.getString("title"));
        news.setImg(rs.getString("img"));
        news.setDate(rs.getString("cdate"));
        news.setContent(rs.getString("content"));

      }

      rs.close();
      pstmt.close();
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return news;
  }

  // [새로운 뉴스 등록하기]
  public void addNews(News news) {
    Connection conn = getConnection();
    String sql = "insert into news (title, date, content, img) values(?,CURRENT_TIMESTAMP(),?,?)";
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, news.getTitle());
      pstmt.setString(2, news.getContent());
      pstmt.setString(3, news.getImg());
      int count = pstmt.executeUpdate();
      log.info("count: {}", count);
      if (count == 1) {
        throw new Exception();
      }

      pstmt.close();
      conn.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
