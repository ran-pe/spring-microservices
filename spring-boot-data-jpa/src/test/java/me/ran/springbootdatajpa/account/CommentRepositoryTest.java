package me.ran.springbootdatajpa.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() {
        Comment comment = new Comment();
        comment.setComment("Hello Comment");
        commentRepository.save(comment);

        List<Comment> comments = commentRepository.findAll();
        assertThat(comments.size()).isEqualTo(1);

        long count = commentRepository.count();
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void crud2() {
        this.createComment(100, "Spring Data JPA");
        this.createComment(50, "Spring Security");

        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("spring", 70);
        assertThat(comments.size()).isEqualTo(1);
        assertThat(comments).first().hasFieldOrPropertyWithValue("likeCount", 100);
    }

    @Test
    public void crud_paging() {
        this.createComment(100, "Spring Data JPA");
        this.createComment(50, "Spring Security");

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));

        Page<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("spring", pageRequest);
        assertThat(comments.getTotalElements()).isEqualTo(2);
        assertThat(comments).last().hasFieldOrPropertyWithValue("likeCount", 50);
    }

    @Test
    public void crud_stream() {
        this.createComment(100, "Spring Data JPA");
        this.createComment(500, "Spring Security");

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));
        try(Stream<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountIsLessThan("spring", 200, pageRequest)) {
            Comment firstComment = comments.findFirst().get();
            assertThat(firstComment.getLikeCount()).isEqualTo(100);
        }
    }

    // 비동기쿼리는 권장하지 않음
//    1. 테스트 코드 작성이 어려움.
//    2. 코드 복잡도 증가.
//    3. 성능상 이득이 없음.
//     --> DB 부하는 결국 같고 메인 쓰레드 대신 백드라운드 쓰레드가 일하는 정도의 차이.
//     --> 단, 백그라운드로 실행하고 결과를 받을 필요가 없는 작업이라면 @Async를 사용해서 응답 속도를 향상 시킬 수는 있다.
    @Test
    public void crud_future() throws ExecutionException, InterruptedException {
        this.createComment(100, "Spring Data JPA");
        this.createComment(500, "Spring Security");

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));

        Future<List<Comment>> future = commentRepository.findByCommentContains("Spring", pageRequest);

        System.out.println("is Done?" + future.isDone());
        List<Comment> comments = future.get();
        comments.forEach(System.out::println);

    }

    @Test
    public void crud_listenableFuture() {
        this.createComment(100, "Spring Data JPA");
        this.createComment(500, "Spring Security");


        ListenableFuture<List<Comment>> future = commentRepository.findByCommentContains("Spring");

        System.out.println("is Done?" + future.isDone());

        future.addCallback(new ListenableFutureCallback<List<Comment>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println(ex);
            }

            @Override
            public void onSuccess(@Nullable List<Comment> result) {
                result.forEach(System.out::println);
            }
        });


    }



    private void createComment(int likeCount, String comment) {
        Comment newComment = new Comment();
        newComment.setLikeCount(likeCount);
        newComment.setComment(comment);
        commentRepository.save(newComment);
    }


}