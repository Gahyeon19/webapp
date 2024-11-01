package com.example.mini_sns.postdomain.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequestDto {
    private int postId;
    private String body;
}
