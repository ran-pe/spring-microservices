package me.ran.springbootdatajpa.post;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class SimpleMyRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements MyRepository<T, ID> {

    private EntityManager entityManager;

    public SimpleMyRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public boolean contains(T entity) {
        return entityManager.contains(entity);
    }

    @Override
    public List<T> findByPost() {
        System.out.println("== SimpleMyRepository findByPost");
        return entityManager.createQuery("select p from NewPost as p").getResultList();
    }

    @Override
    public void delete(T entity) {
        System.out.println("== SimpleMyRepository delete");
        entityManager.detach(entity);
    }
}