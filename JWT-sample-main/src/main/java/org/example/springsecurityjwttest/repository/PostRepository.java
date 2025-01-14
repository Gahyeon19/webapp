package org.example.springsecurityjwttest.repository;

import org.example.springsecurityjwttest.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{
        //, QuerydslPredicateExecutor<Post> {
    //@Query("select p from Post p join fetch p.user") 오류가 나서 distinct 추가 ???
    @Query("select  distinct p from Post p join fetch p.user")
    List<Post> join();
}
