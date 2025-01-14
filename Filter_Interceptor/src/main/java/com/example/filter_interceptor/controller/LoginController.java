package com.example.filter_interceptor.controller;

import com.example.filter_interceptor.domain.User;
import com.example.filter_interceptor.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor    //
public class LoginController {

//    @GetMapping("/register")
//    public String register(HttpServletRequest req) {
//        log.info("register ==> {}", req.getRequestURI());
//        return "회원 가입 완료";
//    }
//
//    @GetMapping("/login")
//    public String login(HttpServletRequest req) {
//        log.info("login ==> {}", req.getRequestURI());
//        HttpSession session = req.getSession(true);
//        session.setAttribute("userId", "John");
//        return session.getAttribute("userId") + " : 로그인 완료";
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpServletRequest req) {
//        log.info("logout ==> {}", req.getRequestURI());
//        HttpSession session = req.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//
//        return "로그아웃 완료";
//    }

    //user login 쿠키 예제
    private final LoginService loginService;

    @GetMapping("/register")
    public String register(HttpServletRequest req) {
        log.info("register ==> {}", req.getRequestURI());
        return "회원 가입 완료";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse resp, @RequestBody User user) {
        boolean isLogin = loginService.isLoginSuccess(user);
        if (isLogin) {
            HttpSession session = req.getSession(true);
            String uuid = UUID.randomUUID().toString();
            session.setAttribute("session_id", UUID.randomUUID());
            session.setAttribute("userid", user.getUserId());
            session.setMaxInactiveInterval(60*2);   //2분 뒤 자동 세션 만료
            Cookie cookie = new Cookie("session_id", uuid);
            resp.addCookie(cookie);
            return "로그인 성공";
        }
        return "로그인 실패";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
            Cookie cookie = new Cookie("session_id", null);
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
        return "로그아웃 완료";
    }
}
