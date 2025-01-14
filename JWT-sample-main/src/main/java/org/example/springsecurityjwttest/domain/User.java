package org.example.springsecurityjwttest.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String  userid;
    private String  password;

    @Column(length = 20)
    private String  name;
    private String  address;

    // 여러개의 권한을 가지게 하려면 authority 엔티티를 추가해서 다대일 관계를 줄 수도 있는데
    // 우리는 예제를 단순하게 하기 위해 권한을 하나만 가지게 할 예정이다.
    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private LocalDateTime registerDate;
}
