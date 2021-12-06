package me.ran.springbootdatajpa.post;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

// 1. implements 로 이벤트 등록 ex)public class PostListener implements ApplicationListener<PostPublishedEvent>
// 2. @EventListener 로 이벤트 등록
// 3. 이 클래스를 만들고 싶지 않다면 PostRepositoryTestConfig 에 구현해도 됨
//public class PostListener {
//
//    @EventListener
//    public void onApplicationEvent(PostPublishedEvent event) {
//        System.out.println("====================");
//        System.out.println(event.getPost().getTitle() + "is published!!");
//        System.out.println("====================");
//    }
//}
