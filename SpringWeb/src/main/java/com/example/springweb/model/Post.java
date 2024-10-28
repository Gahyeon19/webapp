package com.example.springweb.model;    //=domain

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post {
    private int postId;
    private String title;
    private String content;
    private int likes;
}
