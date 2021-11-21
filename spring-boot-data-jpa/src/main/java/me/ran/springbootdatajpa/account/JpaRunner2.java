package me.ran.springbootdatajpa.account;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaRunner2 implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Post post = new Post();
        post.setTitle("Spring Data JPA 언제해?");

        Comment comment = new Comment();
        comment.setComment("빨리해주세요");
        post.addComment(comment);

        Comment comment1 = new Comment();
        comment1.setComment("빨리요");
        post.addComment(comment1);

        Session session = entityManager.unwrap(Session.class);
        session.save(post);

    }
}
