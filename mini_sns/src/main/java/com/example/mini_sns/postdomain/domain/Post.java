package com.example.mini_sns.postdomain.domain;

import com.example.mini_sns.userdomain.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    @Column(length = 20, nullable = false)
    private String title;
    private String body;
    private int likes = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User writer;

}
