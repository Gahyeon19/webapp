package org.example.springsecurityjwttest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springsecurityjwttest.domain.Post;
import org.example.springsecurityjwttest.domain.User;
import org.example.springsecurityjwttest.dto.PostCreateDto;
import org.example.springsecurityjwttest.dto.PostResponseDto;
import org.example.springsecurityjwttest.security.CustomUserDetails;
import org.example.springsecurityjwttest.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    /**
     * 전체 게시물 조회
     * */
    @GetMapping("/posts")
    public ResponseEntity<?> findAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication = {}" , authentication);

        //시큐리티에 저장된 정보 조회
        String name = authentication.getName();//사용자명
        log.info("Authentication getName =  {} " , name);
        log.info("Authentication  authentication.getPrincipal() =  {} " ,  authentication.getPrincipal());
        if(name!=null && !name.equals("anonymousUser")) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = customUserDetails.getUser();
            log.info("customUserDetails =  {} ,{} ,{} ", user.getUserid(), user.getName(), user.getRole());

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            Iterator<? extends GrantedAuthority> iter = authorities.iterator();
            while (iter.hasNext()) {
                GrantedAuthority auth = iter.next();
                String role = auth.getAuthority();
                log.info("Authentication role =  {} ", role);
            }
        }

        return new ResponseEntity<>(postService.findAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/postsTest")
    public List<PostResponseDto> findAllTest(){
        return postService.findAllPosts();
    }

    /**
     * 글번호에 해당하는 게시물 조회
     * */
    @GetMapping("/posts/{postid}")
    public ResponseEntity<?> findById(@PathVariable Long postid){
        Post post = postService.getPost(postid);
        PostResponseDto postDto = new PostResponseDto(post);
        return new ResponseEntity<>(postDto ,HttpStatus.OK);
    }
    /**
     * 게시물 등록
     * */
    @PostMapping("/posts")
    public ResponseEntity<?> save(@RequestBody PostCreateDto postDto){
        return new ResponseEntity<>(postService.addBoard(postDto),HttpStatus.CREATED);//201
    }

    /**
     * 글번호에 해당하는 게시물 수정
     */
    @PutMapping("/posts/{postid}")
    public ResponseEntity<?> update(@PathVariable Long postid,@RequestBody PostResponseDto postDto){
        System.out.println("postid = " + postid);
        Post postUpdated = postService.updatePost(postid, postDto);
        System.out.println("postUpdated = " + postUpdated);

        PostResponseDto pr = PostResponseDto.builder()
                .title(postUpdated.getTitle())
                .regDate(postUpdated.getRegDate().toString())
                .content(postUpdated.getContent())
                .postid(postUpdated.getPostid()).build();
        return new ResponseEntity<>(pr,HttpStatus.OK);
    }
    /**
     * 글번호에 해당하는 게시물 삭제
     * */
    @DeleteMapping("/posts/{postid}")
    public ResponseEntity<?> deletePost(@PathVariable Long postid){
        return new ResponseEntity<>(postService.deletePost(postid),HttpStatus.OK);
    }
}
