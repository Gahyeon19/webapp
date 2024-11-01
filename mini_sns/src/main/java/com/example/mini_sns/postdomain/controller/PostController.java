package com.example.mini_sns.postdomain.controller;

import com.example.mini_sns.postdomain.domain.Post;
import com.example.mini_sns.postdomain.domain.PostCreateRequestDto;
import com.example.mini_sns.postdomain.domain.PostDetailResponseDto;
import com.example.mini_sns.postdomain.domain.PostUpdateRequestDto;
import com.example.mini_sns.postdomain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @PostMapping("/users/{useId}")
    public PostDetailResponseDto createNewPostWithUser(@PathVariable("useId") String useId,
                                                       @RequestBody PostCreateRequestDto postDto) {    // 유저의 게시글 저장
        return postService.createPostWithUser(useId, postDto);
    }

    @GetMapping
    public List<Post> getPostList() {           // 모든 게시글 조회
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public Optional<Post> getPostById(@PathVariable(name = "postId") int postId) {   // 해당 id의 게시글 조회
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updatePostBody(@PathVariable(name = "postId") int postId,
                               @RequestBody Post post) {
        return postService.updatePost(post);
    }

    @DeleteMapping("/{postId}")
    public void deletePostWithUser(@PathVariable(name = "postId") int postId,
                                   @PathVariable(name = "useId") String useId) {    // 유저의 게시글 삭제
        postService.removePost(postId, useId);
    }

    @PatchMapping("/{postId}/users/{useId}")                            // 유저의 게시글 수정
    public PostDetailResponseDto updatePostWithUser(@PathVariable("postId") int postId,
                                                    @PathVariable("useId") String useId,
                                                    @RequestBody PostUpdateRequestDto postDto) {
        return postService.updateBodyWithUser(postId, useId, postDto);
    }
}
