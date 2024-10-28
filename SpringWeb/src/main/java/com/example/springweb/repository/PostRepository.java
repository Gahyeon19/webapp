package com.example.springweb.repository;

import com.example.springweb.model.Post;

import java.util.List;

public interface PostRepository {
    List<Post> findAll();
    Post findById(int postId);
    void deletePost(int postId);
    void updatePost(Post post);
    int insertPost(Post post);
}
