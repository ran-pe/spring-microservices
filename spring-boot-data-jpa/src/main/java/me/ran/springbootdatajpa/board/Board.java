package me.ran.springbootdatajpa.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NamedQuery(name="Board.findByTitleForNamedQuery", query = "select b from Board as b where b.title = ?1")
public class Board {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

}

