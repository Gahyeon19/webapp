//package com.example.querydsl_ex;
//
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Repository;
//import org.springframework.util.StringUtils;
//
//import java.util.List;
//
//import static com.example.querydsl_ex.QPost.post;
//import static com.example.querydsl_ex.QUser.user;
//
//@Repository
//@AllArgsConstructor
//public class PostCustomRepositoryImpl implements PostCustomRepository {
//    private final JPAQueryFactory jpaQueryFactory;
//
//    @Override
//    public List<Post> findAllLeftFetchJoin(User writer) {
//        List<Post> posts = jpaQueryFactory
//                .select(post)
//                .from(post)
//                .where(writerEq(writer.getUserName()))
//                .fetch();
//        return posts;
//    }
//
//    private BooleanExpression writerEq(String userName) {
//        return StringUtils.hasText(userName) ? post.writer.userName.eq(userName) : null;
//    }
//
//
//}
//
//
//
