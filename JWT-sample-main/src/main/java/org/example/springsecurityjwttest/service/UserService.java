package org.example.springsecurityjwttest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springsecurityjwttest.domain.Role;
import org.example.springsecurityjwttest.domain.User;
import org.example.springsecurityjwttest.dto.UserSignupDto;
import org.example.springsecurityjwttest.exception.UserAuthenticationException;
import org.example.springsecurityjwttest.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public String duplicateCheck(String userid) {
        User user = userRepository.duplicateCheck(userid);
        System.out.println("user = " + user);
        if(user==null) return "사용가능합니다.";
        else return "중복입니다.";
    }

    public void signUp(UserSignupDto userDto) {
        if(userRepository.existsByUserid(userDto.getUserid())){
            throw new UserAuthenticationException("아이디중복입니다.","Duplicate Userid");
        }
        User user = new User();
        user.setUserid(userDto.getUserid());
        user.setName(userDto.getName());
        user.setAddress(userDto.getAddress());

        //비번 암호화
        String encPwd =  passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encPwd);
        //ROLE설정
        user.setRole(Role.USER);
        user.setRegisterDate(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        log.info("savedUser = {}" , savedUser);

    }
}
