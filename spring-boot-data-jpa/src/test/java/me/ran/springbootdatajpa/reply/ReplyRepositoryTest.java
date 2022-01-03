package me.ran.springbootdatajpa.reply;

import me.ran.springbootdatajpa.board.Board;
import me.ran.springbootdatajpa.board.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void getComment() {
        // 기본전략은 ManyToOne 이므로 EAGER이였으나 LAZY로 변경)
        replyRepository.findById(1L);

        System.out.println("=====================");

        // NamedEntityGraph를 이용하여 EAGER로 변경
        replyRepository.getReplyById(1L);
    }

    @Test
    public void getReply() {
        replyRepository.findByBoard_Id(1L);
    }

    @Test
    public void getVote() {
        Board board = new Board();
        board.setTitle("jpa");
        Board savedBoard = boardRepository.save(board);

        Reply reply = new Reply();
        reply.setBoard(savedBoard);
        reply.setUp(10);
        reply.setDown(1);
        replyRepository.save(reply);

        replyRepository.findByBoard_Id(savedBoard.getId()).forEach(c -> {
            System.out.println(c.getVotes());
            System.out.println("================================");
            System.out.println(c.getVotes2());
        });

    }

    @Test
    public void testForClass() {
        Board board = new Board();
        board.setTitle("jpa");
        Board savedBoard = boardRepository.save(board);

        Reply reply = new Reply();
        reply.setBoard(savedBoard);
        reply.setUp(10);
        reply.setDown(1);
        replyRepository.save(reply);

        replyRepository.findByBoard_Title(savedBoard.getTitle()).forEach(c -> {
            System.out.println(c.getVotes());
            System.out.println("================================");
        });

    }

    @Test
    public void testForInterface() {
        Board board = new Board();
        board.setTitle("jpa");
        Board savedBoard = boardRepository.save(board);

        Reply reply = new Reply();
        reply.setBoard(savedBoard);
        reply.setUp(10);
        reply.setDown(1);
        replyRepository.save(reply);

        replyRepository.findByBoard_IdAndBoard_Title(savedBoard.getId(), savedBoard.getTitle(), ReplyOnly.class);

    }

    @Test
    public void specs() {
        Page<Reply> page = replyRepository.findAll(ReplySpecs.isBest().and(ReplySpecs.isGood()), PageRequest.of(0, 10));
    }

}