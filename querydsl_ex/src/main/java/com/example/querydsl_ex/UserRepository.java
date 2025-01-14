package com.example.querydsl_ex;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    //쿼리 메서드
    User findByUserId(String userId);
    void deleteByUserId(String userId);

    //동적 쿼리 생성
    @Query(value = "select u from User u where u.userId = :id and u.userName = :name")
    User findUserByUserIdAndUserName(@Param("userId") String userId, @Param("userName") String userName);
}
