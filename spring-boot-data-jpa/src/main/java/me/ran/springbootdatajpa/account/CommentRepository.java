package me.ran.springbootdatajpa.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Stream;

public interface CommentRepository extends MyRepository<Comment, Long> {

    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int likeCount);

    Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

    Stream<Comment> findByCommentContainsIgnoreCaseAndLikeCountIsLessThan(String keyword, int likeCount, Pageable pageable);
}
