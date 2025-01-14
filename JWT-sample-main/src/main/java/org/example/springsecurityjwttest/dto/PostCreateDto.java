package org.example.springsecurityjwttest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.springsecurityjwttest.domain.Post;
import org.example.springsecurityjwttest.domain.User;

@Setter
@Getter
@ToString
public class PostCreateDto {
    private String title;//제목
    private String content;//내용
    private Long id;//작성자 id

    public Post createPost(PostCreateDto postDto){
        return Post.builder().title(postDto.getTitle()).content(postDto.getContent())
                .user(User.builder().id(postDto.id).build())
                .build();
    }
}
