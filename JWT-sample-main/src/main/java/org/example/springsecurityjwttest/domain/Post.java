package org.example.springsecurityjwttest.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity //서버 실행시에 해당 객체로 테이블 매핑생성
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postid;//글번호

    private String title;//제목

    @Column(length =100)
    private String content;//내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;//작성자

    @CreationTimestamp
    private LocalDateTime regDate;//등록일

    @UpdateTimestamp
    private LocalDateTime updateDate;//수정일
}
