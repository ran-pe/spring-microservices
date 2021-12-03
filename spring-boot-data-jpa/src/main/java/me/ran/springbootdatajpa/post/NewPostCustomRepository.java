package me.ran.springbootdatajpa.post;

import java.util.List;

public interface NewPostCustomRepository<T> {
    List<NewPost> findByPost();

    void delete(T entity);
}
