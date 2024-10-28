package com.example.springweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDetailResponseDto {
    private int postId;
    private String title;
    private String content;
    private int likes;
}
