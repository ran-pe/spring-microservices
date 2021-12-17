package me.ran.springbootdatajpa.board;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleStartsWith(String title);

    List<Board> findByTitleForNamedQuery(String title);

    @Query("select b from #{#entityName} as b where b.title = :title")
    List<Board> findByTitle(@Param("title") String keyword, Sort sort);

    @Modifying(clearAutomatically = true)
    @Query("update Board b set b.title = ?2 where b.id = ?1")
    int updateTitle(Long id, String title);

}
