package com.example.students.ch06;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO { //Data Excess Object
  // driver
  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  final String JDBC_URL = "jdbc:mysql://localhost:3306/backend?serverTimezone=Asia/Seoul";

  // db connection
  Connection connection = null;

  // Statement
  PreparedStatement preparedStatement = null;

  // sql
  public void open() {      // open db connection
    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(JDBC_URL, "root", "1111");
    } catch (ClassNotFoundException | SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void close() {     // close the connection
    try {
      preparedStatement.close();
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void insert(Student st) {
    String sql = "INSERT INTO students(name, univ, birth, email) VALUES(?,?,?,?)";

    try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, st.getName());
      preparedStatement.setString(2, st.getUniv());
      preparedStatement.setDate(3, st.getBirth());
      preparedStatement.setString(4, st.getEmail());

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void update(Student st) {
    //univ 와 email 만 변경
    String sql = "UPDATE students SET univ=?, email=? WHERE id=?";

    try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, st.getUniv());
      preparedStatement.setString(2, st.getEmail());
      preparedStatement.setInt(3, st.getId());

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(int id) {
    String sql = "DELETE FROM students WHERE id=?";

    try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Student findById(int id) {
    Student student = null;
    String sql = "select * from students where id = ?";

    try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        student = new Student(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("univ"),
            resultSet.getDate("birth"),
            resultSet.getString("email"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return student;
  }

  public List<Student> findAll() {
    List<Student> students = new ArrayList<Student>();
    String sql = "SELECT * FROM students";

    try {
      preparedStatement = connection.prepareStatement(sql);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Student student = new Student(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("univ"),
            resultSet.getDate("birth"),
            resultSet.getString("email"));
        students.add(student);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return students;
  }
}
