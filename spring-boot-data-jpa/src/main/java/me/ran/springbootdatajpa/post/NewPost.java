package me.ran.springbootdatajpa.post;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class NewPost extends AbstractAggregateRoot<NewPost> {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public NewPost publish() {
        this.registerEvent(new PostPublishedEvent(this));
        return this;
    }
}
