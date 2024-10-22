package com.example.students.ch06;
//2장 22p~
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
// lombok로 getter, setter, toString, hashCode, equals 메소드 자동 생성
public class Student {
  private int id;
  private String name;
  private String univ;
  private Date birth;
  private String email;

  public Student(int id, String name, String univ, Date birth, String email) {
    this.id = id;
    this.name = name;
    this.univ = univ;
    this.birth = birth;
    this.email = email;
  }
}
