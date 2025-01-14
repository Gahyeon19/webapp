//package com.example.querydsl_ex;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.AllArgsConstructor;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import static com.example.querydsl_ex.QUser.user;
//import static com.example.querydsl_ex.QPost.post;
//
//@Repository
//@AllArgsConstructor
//public class UserCustomRepositoryImpl implements UserCustomRepository{
//    private final JPAQueryFactory jpaQueryFactory;
//
//    @Override
//    public User findAllLeftFetchJoin(String userId) {
//        return jpaQueryFactory
//                .selectFrom(user)
//                .where(user.userId.eq(userId))
//                .leftJoin(user.userName)
//                .fetchJoin()
//                .fetchOne();
//    }
//}
