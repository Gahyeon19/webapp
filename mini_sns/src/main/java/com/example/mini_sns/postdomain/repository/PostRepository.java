package com.example.mini_sns.postdomain.repository;

import com.example.mini_sns.postdomain.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("select p from Post p left join fetch p.writer where p.postId = :postId")    //:postId ==> ?와 같은 역할
    Post findByPostWithUserFetchjoin(@Param("postId") int postId);
}
