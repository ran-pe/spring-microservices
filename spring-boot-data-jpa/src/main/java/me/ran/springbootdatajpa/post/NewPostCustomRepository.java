package me.ran.springbootdatajpa.post;

import java.util.List;

public interface NewPostCustomRepository<T> {
    List<T> findByPost();

    void delete(T entity);
}
