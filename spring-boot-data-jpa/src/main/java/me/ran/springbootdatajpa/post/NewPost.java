package me.ran.springbootdatajpa.post;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class NewPost {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}
