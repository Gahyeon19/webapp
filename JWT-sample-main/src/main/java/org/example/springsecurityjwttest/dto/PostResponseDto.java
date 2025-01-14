package org.example.springsecurityjwttest.dto;

import lombok.*;
import org.example.springsecurityjwttest.domain.Post;
import org.example.springsecurityjwttest.domain.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDto {
    private Long postid;//글번호
    private String title;//제목
    private String content;//내용
    private UserResponseDto user;//작성자
    private String regDate;//등록일

    public PostResponseDto(Post post) {
        postid = post.getPostid();
        title=post.getTitle();
        content=post.getContent();
        regDate=post.getRegDate().toString();
        user =new UserResponseDto(post.getUser().getId(),
                post.getUser().getUserid() , post.getUser().getName());
    }
}
