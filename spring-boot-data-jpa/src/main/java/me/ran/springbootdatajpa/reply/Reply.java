package me.ran.springbootdatajpa.reply;

import lombok.Getter;
import lombok.Setter;
import me.ran.springbootdatajpa.account.Post;

import javax.persistence.*;

//@NamedEntityGraph(name = "Reply.post", attributeNodes = @NamedAttributeNode("post"))
@Getter
@Setter
@Entity
public class Reply {

    @Id @GeneratedValue
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
