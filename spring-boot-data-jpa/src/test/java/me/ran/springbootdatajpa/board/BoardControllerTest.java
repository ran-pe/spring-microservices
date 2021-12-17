package me.ran.springbootdatajpa.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void getBoard() throws Exception {
        Board board = makeBoard("jpa");

        mockMvc.perform(get("/boards/" + board.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("jpa"));
    }

    @Test
    public void getBoardTitle() throws Exception {
        Board board = makeBoard("spring");

        mockMvc.perform(get("/boards/title/" + board.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("spring"));
    }

    @Test
    public void getBoards() throws Exception {
        makeBoard("java8");

        mockMvc.perform(get("/boards")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "created,desc")
                        .param("sort", "title"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title", is("java8")));

    }

    @Test
    public void findByTitleStartsWith() {
        makeBoard("spring");
        List<Board> all = boardRepository.findByTitleStartsWith("spring");
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleForNamedQuery() {
        makeBoard("spring");
        List<Board> all = boardRepository.findByTitleForNamedQuery("spring");
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void findByTitle_Sort() {
        makeBoard("spring");
        List<Board> all = boardRepository.findByTitle("spring", Sort.by("title"));
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void findByTitle_JpaSort() {
        makeBoard("spring");
        List<Board> all = boardRepository.findByTitle("spring", JpaSort.unsafe("LENGTH(title)"));   // 사용불가능 Sort.by("LENGTH(title)")
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void updateTitle_no_recommend() {
        Board board = makeBoard("spring");
        String hibernate = "hibernate";
        int update = boardRepository.updateTitle(board.getId(), hibernate);
        assertThat(update).isEqualTo(1);

        Optional<Board> byId = boardRepository.findById(board.getId());
        assertThat(byId.get().getTitle()).isEqualTo(hibernate); // @Modifying(clearAutomatically = true) 셋팅해야 테스트성공
    }

    @Test
    public void updateTitle_recommend() {
        Board board = makeBoard("spring");
        String hibernate = "hibernate";
        board.setTitle(hibernate);

        List<Board> boards = boardRepository.findAll();
        assertThat(boards.get(0).getTitle()).isEqualTo(hibernate);
    }

    private Board makeBoard(String title) {
        Board board = new Board();
        board.setTitle(title);
        boardRepository.save(board);
        return board;
    }
}