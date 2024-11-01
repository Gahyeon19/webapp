package com.example.mini_sns.postdomain.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostAllResponseDto {
    private int postId;
    private String title;
    private int likes;
    private String useId;

    public static PostAllResponseDto of(Post post) {
        return new PostAllResponseDto(
                post.getPostId(),
                post.getTitle(),
                post.getLikes(),
                post.getWriter().getUseId());
    }
}
