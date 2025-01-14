package com.example.springsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class SimpleController {

    @GetMapping("/simple")
    public String simple(Model model, Principal principal) {    //인증된 사용자가 있다면 사용자의 정보를 principal
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        Principal principal1 = (Principal) authentication.getPrincipal();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (principal != null) {   //인증된 사용자가 있다면 사용자의 이름을 model에 담음
            model.addAttribute("username", principal.getName());
        } else {                   //인증된 사용자가 없다면 문자열을 model에 담음
            model.addAttribute("username", "Hello SECURITY");
        }

        return "basic/simple";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("info", "this is a information for everyone");
        return "basic/info";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("info", "this is a home");
        return "basic/info";
    }
}
