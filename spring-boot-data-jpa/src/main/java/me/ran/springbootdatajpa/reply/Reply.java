package me.ran.springbootdatajpa.reply;

import lombok.Getter;
import lombok.Setter;
import me.ran.springbootdatajpa.board.Board;

import javax.persistence.*;

//@NamedEntityGraph(name = "Reply.board", attributeNodes = @NamedAttributeNode("board"))
@Getter
@Setter
@Entity
public class Reply {

    @Id
    @GeneratedValue
    private Long id;

    private String reply;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private int up;

    private int down;

    private boolean best;

}
