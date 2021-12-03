package me.ran.springbootdatajpa.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NewPostRepositoryTest {

    @Autowired
    NewPostRepository newPostRepository;

    @Test
    public void crud() {
        newPostRepository.findByPost();

        NewPost newPost = new NewPost();
        newPost.setTitle("hibernate");
        newPostRepository.save(newPost);

        newPostRepository.findByPost();

        newPostRepository.delete(newPost);
        newPostRepository.flush();
    }

}