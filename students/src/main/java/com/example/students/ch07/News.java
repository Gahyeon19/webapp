package com.example.students.ch07;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data //getter, setter, tostring 등등 포함
public class News {
  private int id;
  private String title;
  private String img;
  private String date;
  private String content;


}
