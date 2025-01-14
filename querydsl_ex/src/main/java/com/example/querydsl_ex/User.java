package com.example.querydsl_ex;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20, nullable = false)
    private String userName;
    @Column(length = 8, nullable = false, unique = true)
    private String userId;
    @Column(length = 8, nullable = false)
    private String password;
}
