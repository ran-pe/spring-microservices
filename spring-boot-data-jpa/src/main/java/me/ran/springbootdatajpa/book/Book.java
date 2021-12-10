package me.ran.springbootdatajpa.book;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Book {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String content;

}
