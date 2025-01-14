package com.example.springsecurity.controller;

import com.example.springsecurity.dto.AccountLoginDto;
import com.example.springsecurity.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private final AccountService accountService;

    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String login() {
        return "basic/login";
    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute AccountLoginDto loginDto) {
//        Boolean isLogin = accountService.isValidUser(loginDto);
//
//        if (isLogin) {
//            return "redirect:/";
//        }
//        return "redirect:/login";
//    }
}
