package com.example.filter_interceptor.service;

import com.example.filter_interceptor.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LoginService {

    public boolean isLoginSuccess(User user) {
        if (user != null && StringUtils.hasText(user.getUserId()) && StringUtils.hasText(user.getPassword())) {
            if (user.getUserId().equals("aaa") && user.getPassword().equals("1111")) {
                return true;
            }
            return false;
        }
        return false;
    }
}
