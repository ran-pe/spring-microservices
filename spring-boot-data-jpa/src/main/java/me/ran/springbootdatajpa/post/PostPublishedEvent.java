package me.ran.springbootdatajpa.post;

import org.springframework.context.ApplicationEvent;

public class PostPublishedEvent extends ApplicationEvent {

    private final NewPost post;

    public PostPublishedEvent(Object source) {
        super(source);
        this.post = (NewPost) source;
    }

    public NewPost getPost() {
        return post;
    }
}
