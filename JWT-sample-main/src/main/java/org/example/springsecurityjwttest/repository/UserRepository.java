package org.example.springsecurityjwttest.repository;

import org.example.springsecurityjwttest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    //위치 기반 파라미터를 사용할 때는 반드시 파라미터의 위치를 명확히 하기 위해 ?1, ?2 등의 형식을 사용해야 함
    @Query("select u from User u where u.userid=?1") //JPQL문법
    User duplicateCheck(String userid);

    Boolean existsByUserid(String userid); //QueryMethod방식

    //username을 받아 DB 테이블에서 회원을 조회하는 메소드 작성
    User findByUserid(String userid);////QueryMethod방식
}
