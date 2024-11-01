package com.example.mini_sns.postdomain.service;

import com.example.mini_sns.postdomain.domain.Post;
import com.example.mini_sns.postdomain.domain.PostCreateRequestDto;
import com.example.mini_sns.postdomain.domain.PostDetailResponseDto;
import com.example.mini_sns.postdomain.domain.PostUpdateRequestDto;
import com.example.mini_sns.postdomain.repository.PostRepository;
import com.example.mini_sns.userdomain.domain.User;
import com.example.mini_sns.userdomain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post savePost(Post post) {     // 게시글 저장
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {       // 모든 게시글 조회
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Post> getPostById(int postId){      // 해당 id의 게시글 조회
        return postRepository.findById(postId);
    }

    public Post updatePost(Post post) {     // 게시글 수정
        postRepository.findById(post.getPostId());
        if (post != null && post.getBody() != null) {
            post.setBody(post.getBody());
        }
        return postRepository.save(post);
    }

    public void deletePostById(int postId) {         // 게시글 삭제
        postRepository.deleteById(postId);
    }

    public PostDetailResponseDto getPostDetail(int postId) {
        Post post =  postRepository.findByPostWithUserFetchjoin(postId);
        if (post == null) {
            return null;
        }
        PostDetailResponseDto retPost = new PostDetailResponseDto(
                post.getPostId(),
                post.getTitle(),
                post.getBody(),
                post.getLikes(),
                post.getWriter().getUseId());

        return retPost;
    }

    public PostDetailResponseDto createPostWithUser(String useId, PostCreateRequestDto postDto) {
        User user = userRepository.findByUseId(useId);
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
        post.setWriter(user);
        log.info("=============================="+post.toString());
        postRepository.save(post);
        return getPostDetail(post.getPostId());
    }

    public PostDetailResponseDto updateBodyWithUser(int postId, String useId, PostUpdateRequestDto postDto) {
        Post post = postRepository.findById(postId).get();
        if(post != null && post.getWriter().getUseId().equals(useId)){
            post.setBody(postDto.getBody());
            Post save = postRepository.save(post);
            return getPostDetail(save.getPostId());
        }
        return null;
    }

    public void removePost(int postId, String useId) {
        Post post = postRepository.findById(postId).get();
        if(post != null && post.getWriter().getUseId().equals(useId)){
            postRepository.delete(post);
        }
    }


}
