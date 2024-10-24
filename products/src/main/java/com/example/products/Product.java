package com.example.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
  private int id;
  private String name;
  private int price;
  private String maker;
  private String date;

  public Product(int id, String name, int price, String maker, String date) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.maker = maker;
    this.date = date;
  }
}
