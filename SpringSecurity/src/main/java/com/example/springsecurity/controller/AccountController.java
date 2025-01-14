package com.example.springsecurity.controller;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {
    public final AccountService accountService;

    @GetMapping("/accounts/new")
    public String newAccount() {
        return "accounts/new";
    }

    @PostMapping("/accounts/new")
    public String newAccount(@ModelAttribute Account account) {
        Account saveAccount = accountService.addAccount(account);
        return "redirect:/login";
    }
}
