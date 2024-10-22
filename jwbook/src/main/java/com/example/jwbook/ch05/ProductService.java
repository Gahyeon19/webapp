package com.example.jwbook.ch05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
  Map<Long, Product> products = new HashMap<>();
  private static long seq = 0L;
  public ProductService() {
    Product p = new Product(++seq, "Galaxy", 1000, "Samsung", "2024-10-21");
    products.put(p.getId(), p);
    p = new Product(++seq, "IPhone", 1500, "Apple", "2024-10-21");
    products.put(p.getId(), p);
    p = new Product(++seq, "V30", 800, "LG", "2024-10-20");
    products.put(p.getId(), p);
  }

  //목록 전체를 가져옴
  public List<Product> findAll() {
    return new ArrayList<>(products.values());
  }

  //특정 제품만 조회함
  public Product findById(long id) {
    return products.get(id);
  }
}
