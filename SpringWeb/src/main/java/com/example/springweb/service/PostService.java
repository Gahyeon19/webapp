package com.example.springweb.service;

import com.example.springweb.model.*;
import com.example.springweb.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostAllResponseDto> getAllPosts() {
        List<Post> allPosts = postRepository.findAll();
        List<PostAllResponseDto> postDtos = allPosts.stream()
                .map(PostAllResponseDto::of)
                .collect(Collectors.toList());
        return postDtos;
    }

    public PostDetailResponseDto getPostById(int postId) {
        Post post =  postRepository.findById(postId);
        if (post == null) {
            return null;
        }
        PostDetailResponseDto retPost = new PostDetailResponseDto(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getLikes());

        return retPost;
    }

    public PostDetailResponseDto createNewPost(PostCreateRequestDto postDto) {
        Post post = new Post(
                0,
                postDto.getTitle(),
                postDto.getContent(),
                0
        );
        int postId = postRepository.insertPost(post);
        return getPostById(postId);
    }

    public void deletePost(int postId) {
        postRepository.deletePost(postId);
    }

    public PostDetailResponseDto updateContentPost(PostUpdateRequestDto postDto) {
        Post post = postRepository.findById(postDto.getPostId());
        // content를 빈 내용을 허용하지 않는다.
        if (post != null && !postDto.getContent().equals(" ")) {
            post.setContent(postDto.getContent());
            postRepository.updatePost(post);
        }

        return getPostById(postDto.getPostId());
    }

    public int updateLikesPost(int postId) {
        Post post = postRepository.findById(postId);
        int likes = 0;
        if (post != null) {
            likes = post.getLikes() + 1;
            post.setLikes(likes);
        }
        return likes;
    }


}
