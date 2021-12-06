package me.ran.springbootdatajpa.post;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryTestConfig {

//    @Bean
//    public PostListener postListener() {
//        return new PostListener();
//    }

    @Bean
    public ApplicationListener<PostPublishedEvent> postPublishedEventApplicationListener() {
        // PostListener 클래스를 구현해서 등록하는 방법 대신 직접 구현하는 방법
        return event -> {
            System.out.println("====================");
            System.out.println(event.getPost().getTitle() + "is published!!");
            System.out.println("====================");
        };
    }
}
