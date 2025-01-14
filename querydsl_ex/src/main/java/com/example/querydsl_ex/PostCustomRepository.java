package com.example.querydsl_ex;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostCustomRepository {
    @PersistenceContext
    EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public PostCustomRepository(EntityManager em) {
        this.em = em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    QPost post = QPost.post;

    public List<Post> findByTitle() {
        return jpaQueryFactory
                .select(post)
                .from(post)
                .where(post.title.contains("test"))
                .fetch();
    }
}

