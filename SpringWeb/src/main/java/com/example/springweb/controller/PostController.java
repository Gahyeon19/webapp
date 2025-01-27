package com.example.springweb.controller;

import com.example.springweb.model.*;
import com.example.springweb.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

//    @Autowired        //@RequiredArgsConstructor 로 대체 가능
//    public PostController(PostService postService) {
//        this.postService = postService;
//    }

    @GetMapping("/posts")   // 게시판 글 전체 목록 조회
    public List<PostAllResponseDto> viewAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/posts/likes")   // 좋아요 수 likes 값 이상인 게시판 글 전체 목록 조회 (정적쿼리)
    public List<PostAllResponseDto> viewAllPostsWithLikes(@RequestParam(name="likes", required = false) Integer likes,
                                                          @RequestParam(name="title", required = false) String title) {
        return postService.getAllPostsWithLikes(likes, title);
    }

    @GetMapping("/posts/{postId}")      // 게시판 글 상세 조회
    public PostDetailResponseDto viewPostDetail(@PathVariable("postId") int postId) {
        return postService.getPostById(postId);
    }

    @PostMapping("/posts")      // 게시판 글 등록
    public PostDetailResponseDto createNewPost(@RequestBody PostCreateRequestDto postDto) {
        return postService.createNewPost(postDto);
    }

    @DeleteMapping("/posts/{postId}")   // 게시판 글 삭제
    public String deletePost(@PathVariable("postId") int postId) {
        postService.deletePost(postId);
        return "삭제 완료";
    }

    @PatchMapping("/posts/{postId}")    // 게시판 글 수정
    public PostDetailResponseDto updateContentPost(@PathVariable("postId") int postId,@RequestBody PostUpdateRequestDto postDto) {
        return postService.updateContentPost(postDto);
    }

    @PutMapping("/posts/{postId}")      // 게시판 글의 좋아요 수 증가
    public String updateLikesPost(@PathVariable("postId") int postId) {
        return "좋아요 수 " + postService.updateLikesPost(postId);
    }

}
