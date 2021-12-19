package me.ran.springbootdatajpa.reply;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void getComment() {
        // 기본전략은 ManyToOne 이므로 EAGER이였으나 LAZY로 변경)
        replyRepository.findById(1L);

        System.out.println("=====================");

        // NamedEntityGraph를 이용하여 EAGER로 변경
        replyRepository.getReplyById(1L);
    }

}