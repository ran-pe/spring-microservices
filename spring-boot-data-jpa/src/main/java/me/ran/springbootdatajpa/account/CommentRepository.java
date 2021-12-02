package me.ran.springbootdatajpa.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface CommentRepository extends MyRepository<Comment, Long> {

    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int likeCount);

    Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

    Stream<Comment> findByCommentContainsIgnoreCaseAndLikeCountIsLessThan(String keyword, int likeCount, Pageable pageable);

    @Async
    Future<List<Comment>> findByCommentContains(String keyword, Pageable pageable);

    @Async
    ListenableFuture<List<Comment>> findByCommentContains(String keyword);
}
