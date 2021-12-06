package me.ran.springbootdatajpa.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NewPostRepositoryTest {

    @Autowired
    NewPostRepository newPostRepository;

    @Test
    public void crud1() {
        newPostRepository.findByPost();

        NewPost newPost = new NewPost();
        newPost.setTitle("hibernate");
        newPostRepository.save(newPost);

        newPostRepository.findByPost();

        newPostRepository.delete(newPost);
        newPostRepository.flush();
    }

    @Test
    public void crud2() {
        NewPost newPost = new NewPost();
        newPost.setTitle("hibernate");

        assertThat(newPostRepository.contains(newPost)).isFalse();
        newPostRepository.save(newPost);

        assertThat(newPostRepository.contains(newPost)).isTrue();

        newPostRepository.delete(newPost);
        newPostRepository.flush();
    }

}