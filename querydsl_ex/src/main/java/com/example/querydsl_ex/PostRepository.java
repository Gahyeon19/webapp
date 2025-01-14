package com.example.querydsl_ex;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Integer> {
    //쿼리 메서드
    Post findByPostId(int postId);

    //동적 쿼리 생성
    @Query("select p from Post p left join fetch p.writer where p.postId = :postId")    //:postId ==> ?와 같은 역할
    Post findByPostWithUserFetchjoin(@Param("postId") int postId);
}

