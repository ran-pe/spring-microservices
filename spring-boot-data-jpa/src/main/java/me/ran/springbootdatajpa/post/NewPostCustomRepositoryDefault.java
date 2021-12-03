package me.ran.springbootdatajpa.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class NewPostCustomRepositoryDefault implements NewPostCustomRepository<NewPost> {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<NewPost> findByPost() {
        System.out.println("== custom findByPost");
        return entityManager.createQuery("select p from NewPost as p", NewPost.class).getResultList();
    }

    @Override
    public void delete(NewPost entity) {
        System.out.println("== custom delete");
        entityManager.detach(entity);
    }
}
