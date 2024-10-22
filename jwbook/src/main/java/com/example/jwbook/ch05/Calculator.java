package com.example.jwbook.ch05;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calculator {   //Model
  private int n1;
  private int n2;
  private String op;

  //lombok 으로 getter 와 setter 자동 생성

  public long calculate(){
    long result = 0;
    switch (op){
      case "+": result = n1 + n2; break;
      case "-": result = n1 - n2; break;
      case "*": result = n1 * n2; break;
      case "/": result = n1 / n2; break;
    }

    return result;
  }
}
