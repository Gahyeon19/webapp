package com.example.querydsl_ex;

import java.util.List;

public interface PostCustomRepository2 {
    List<Post> findAllLeftFetchJoin(User writer);
}



