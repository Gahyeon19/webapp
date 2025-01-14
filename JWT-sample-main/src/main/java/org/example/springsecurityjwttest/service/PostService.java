package org.example.springsecurityjwttest.service;

import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwttest.domain.Post;
import org.example.springsecurityjwttest.dto.PostCreateDto;
import org.example.springsecurityjwttest.dto.PostResponseDto;
import org.example.springsecurityjwttest.exception.PostNotFoundException;
import org.example.springsecurityjwttest.exception.SQLDMLException;
import org.example.springsecurityjwttest.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post addBoard(PostCreateDto postDto) {
        System.out.println("postDto = " + postDto);

        Post post = postDto.createPost(postDto);
        System.out.println("post = " + post.toString());
        return postRepository.save(post);
    }

    public Post getPost(Long postid) throws PostNotFoundException {
        return postRepository.findById(postid)
                .orElseThrow(()->new PostNotFoundException("글번호를 확인하세요.","Not Found Post SearchById"));
    }

    //public List<Board> findAllBoard() throws BoardSearchNotException {
    public List<PostResponseDto> findAllPosts() throws PostNotFoundException{
        //List<Board> list = boardRepository.findAll();
        List<Post> posts = postRepository.join();

        //Lis<Board>
        System.out.println("--------------------------------------------");
        if(posts ==null || posts.isEmpty())
            throw new PostNotFoundException("전체게시물이 없습니다.", "Not Found Post All");

        return posts.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Post updatePost(Long postid,PostResponseDto postDto)  throws SQLDMLException {
        Post post = postRepository.findById(postid)
                .orElseThrow(()->new SQLDMLException("게시글이 수정되지 않았습니다.", "Not Updated"));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return post;
    }

    @Transactional
    public String deletePost(Long postid) {
        System.out.println("id = " + postid);
        postRepository.findById(postid).orElseThrow(()->
                new SQLDMLException("게시글이 삭제 되지 않았습니다.", "Not Deleted" , HttpStatus.BAD_REQUEST ));
        postRepository.deleteById(postid);
        return "ok";
    }
}