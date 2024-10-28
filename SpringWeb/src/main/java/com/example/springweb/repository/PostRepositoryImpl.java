package com.example.springweb.repository;

import com.example.springweb.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepositoryImpl implements PostRepository{
    private static Map<Integer, Post> posts = new HashMap<>();
    private static int seq = 1;

    public PostRepositoryImpl() {
        Post post = new Post(1, "Title1", "Content1", 0);
        posts.put(1, post);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public Post findById(int postId) {
        return posts.get(postId);
    }

    @Override
    public void deletePost(int postId) {
        posts.remove(postId);
    }

    @Override
    public void updatePost(Post post) {
        posts.put(post.getPostId(), post);
    }

    @Override
    public int insertPost(Post post) {
        seq++;
        post.setPostId(seq);
        posts.put(post.getPostId(), post);
        return post.getPostId();
    }
}
