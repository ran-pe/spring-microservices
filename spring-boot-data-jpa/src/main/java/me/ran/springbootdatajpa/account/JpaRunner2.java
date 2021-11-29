package me.ran.springbootdatajpa.account;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

        // JPQL(HQL) 사용
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post AS p", Post.class);
        List<Post> posts_jpql = query.getResultList();
        for (Post post1 : posts_jpql) {
            System.out.println("[JPQL] " + post1);
        }

        // Criteria 사용
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = builder.createQuery(Post.class);
        Root<Post> root = criteriaQuery.from(Post.class);
        criteriaQuery.select(root);
        List<Post> post_criteria = entityManager.createQuery(criteriaQuery).getResultList();
        for (Post post2 : post_criteria) {
            System.out.println("[Criteria] " + post2);
        }

        //Native Query 사용
        List<Post> posts_native = entityManager.createNativeQuery("select * from post", Post.class).getResultList();
        for (Post post3 : posts_native) {
            System.out.println("[Native] " + post3);
        }



    }
}
