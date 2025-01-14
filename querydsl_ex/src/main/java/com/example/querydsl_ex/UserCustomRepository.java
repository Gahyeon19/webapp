package com.example.querydsl_ex;

public interface UserCustomRepository {
    User findAllLeftFetchJoin(String userId);
}
