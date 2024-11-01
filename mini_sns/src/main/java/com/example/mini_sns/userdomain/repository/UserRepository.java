package com.example.mini_sns.userdomain.repository;

import com.example.mini_sns.userdomain.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUseId(String useId);
    void deleteByUseId(String useId);

}
