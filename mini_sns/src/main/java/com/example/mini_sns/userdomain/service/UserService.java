package com.example.mini_sns.userdomain.service;

import com.example.mini_sns.userdomain.domain.User;
import com.example.mini_sns.userdomain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getAllusers() {       // 모든 유저 조회
        return userRepository.findAll();
    }

    public User createNewUser(User user) {      // 유저 저장
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getOneUser(String useId) {
        User user = userRepository.findByUseId(useId);
        return user;
    }

    public void removeUser(String useId) {       // 유저 삭제
//        User user = userRepository.findByUseId(useId);
//        if (user != null) {
//            userRepository.delete(user);
//        }
        userRepository.deleteByUseId(useId);
    }

    //password 만 수정
    public User updateUserPassword(User user) {
        log.info(user.toString());
        User oldUser = getOneUser(user.getUseId());
        if (oldUser != null && !oldUser.getPassword().equals(user.getPassword()) && !user.getPassword().isBlank()) {
            oldUser.setPassword(user.getPassword());
            userRepository.save(oldUser);
            log.info(oldUser.toString());
        }
        return oldUser;
    }
}
