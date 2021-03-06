package me.ran.springbootdatajpa.post;

import com.querydsl.core.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(PostRepositoryTestConfig.class)
public class NewPostRepositoryTest {

    @Autowired
    NewPostRepository newPostRepository;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void event() {
        // 스프링 프레임워크 ApplicationContext 를 이용한 이벤트 등록
        NewPost post = new NewPost();
        post.setTitle("event");
        PostPublishedEvent event = new PostPublishedEvent(post);

        applicationContext.publishEvent(event);
    }

    @Test
    public void crud1() {
        NewPost newPost = new NewPost();
        newPost.setTitle("hibernate");
        assertThat(newPostRepository.contains(newPost)).isFalse();
        // 스프링 데이터의 도메인 이벤트를 이용
        newPostRepository.save(newPost.publish());    //insert 전에 event가 등록됨
        assertThat(newPostRepository.contains(newPost)).isTrue();
        newPostRepository.findByPost();
        newPostRepository.delete(newPost);
        newPostRepository.flush();
    }

    @Test
    public void crud2() {
        NewPost newPost = new NewPost();
        newPost.setTitle("hibernate");
        newPostRepository.save(newPost.publish());

        Predicate predicate = QNewPost.newPost.title.containsIgnoreCase("Hi");
        Optional<NewPost> one = newPostRepository.findOne(predicate);
        assertThat(one).isNotEmpty();


    }

}