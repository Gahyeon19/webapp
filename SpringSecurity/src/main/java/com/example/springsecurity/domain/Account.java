package com.example.springsecurity.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 10, nullable = false, unique = true)
    private String username;    //=userId
    private String password;
    @Column(length = 10)
    private String role;

    public void changePassword(String password, PasswordEncoder encoder) {
        this.password = encoder.encode(password);
    }

    public boolean checkPassword(String password, PasswordEncoder encoder) {
        return encoder.matches(this.password, password);
    }
}
