package org.example.springsecurityjwttest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springsecurityjwttest.domain.User;
import org.example.springsecurityjwttest.dto.UserSignupDto;
import org.example.springsecurityjwttest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    /**
     * 가입하기
     * */
    @PostMapping("/users")
    public ResponseEntity<?> signUp(@RequestBody UserSignupDto userDto){ //json전달
        //log.info("user = {}", user);
        userService.signUp(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }

    //중복체크
    @GetMapping("/users/{userid}")
    public String duplicateUseridCheck(@PathVariable String userid){
        System.out.println("userid = " + userid);
        return userService.duplicateCheck(userid);
    }
}
