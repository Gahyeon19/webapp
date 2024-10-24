package com.example.products;

import org.apache.logging.log4j.util.Strings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
  // driver
  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  final String JDBC_URL = "jdbc:mysql://localhost:3306/backend?serverTimezone=Asia/Seoul";  //backend는 스키마 이름

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

  public void insert(Product product) {
    String sql = "INSERT INTO products (name, price, maker, date) VALUES (?,?,?,?)";

    try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, product.getName());
      preparedStatement.setInt(2, product.getPrice());
      preparedStatement.setString(3, product.getMaker());
      preparedStatement.setString(4, product.getDate());

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void update(Product product) {
    //price 와 date 만 변경
    String sql = "UPDATE products SET price=?, date=? WHERE id=?";

    try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, product.getPrice());
      preparedStatement.setString(2, product.getDate());
      preparedStatement.setInt(3, product.getId());

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(int id) {
    String sql = "DELETE FROM products WHERE id=?";

    try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Product findById(int id) {
    Product product = null;
    String sql = "select * from products where id = ?";

    try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        product = new Product(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("maker"),
            resultSet.getString("date"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return product;
  }

  public List<Product> findAll() {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM products";

    try {
      preparedStatement = connection.prepareStatement(sql);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Product product = new Product(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("maker"),
            resultSet.getString("date"));
        products.add(product);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return products;
  }
}
