package me.ran.springbootdatajpa.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

// 커스텀 리포지토리 사용
// public interface NewPostRepository extends JpaRepository<NewPost, Long>, NewPostCustomRepository<NewPost> {}

// 기본 리포지토리 커스터마이징
public interface NewPostRepository extends MyRepository<NewPost, Long>, QuerydslPredicateExecutor<NewPost> {}