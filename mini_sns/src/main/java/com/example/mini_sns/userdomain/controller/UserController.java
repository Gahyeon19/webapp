package com.example.mini_sns.userdomain.controller;

import com.example.mini_sns.userdomain.domain.User;
import com.example.mini_sns.userdomain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllusers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @GetMapping("/{useId}")
    public User getUser(@PathVariable("useId") String useId) {
        return userService.getOneUser(useId);
    }

    @DeleteMapping("/{useId}")
    public void deleteUser(@PathVariable("useId") String useId) {
        userService.removeUser(useId);
    }

    @PutMapping("/{useId}")
    public User updateUser(@PathVariable("useId") String useId, @RequestBody User user) {
        user.setUseId(useId);
        return userService.updateUserPassword(user);
    }
}
