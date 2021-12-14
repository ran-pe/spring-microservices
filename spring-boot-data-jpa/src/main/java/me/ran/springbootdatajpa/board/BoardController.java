package me.ran.springbootdatajpa.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BoardController {

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/boards/{id}")
    public String getBoard(@PathVariable Long id) {
        Optional<Board> byId = boardRepository.findById(id);
        Board board = byId.get();
        return board.getTitle();
    }

    @GetMapping("/boards/title/{id}")
    public String getBoardTitle(@PathVariable("id") Board board) {
        return board.getTitle();
    }

    @GetMapping("/boards")
    public Page<Board> getBoards(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

}
