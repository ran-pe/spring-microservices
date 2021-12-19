package me.ran.springbootdatajpa.reply;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    /**
     * EntityGraph 설정방법
     * 1. Entity에 @NamedEntityGraph 설정 ex)@NamedEntityGraph(name = "Reply.post", attributeNodes = @NamedAttributeNode("post"))
     * 1-1. 쿼리메소드에 @EntityGraph설정 ex)@EntityGraph(value = "Reply.post", type = EntityGraph.EntityGraphType.LOAD)
     *
     * 2. 쿼리메소드에만 @EntityGraph설정 ex)@EntityGraph(attributePaths = "post")
     */

    /**
     * EntityGraphType
     * FETCH(기본값): 설정한 엔티티 애트리뷰트는 EAGER 패치 나머지는 LAZY 패치.
     * LOAD: 설정한 엔티티 애트리뷰트는 EAGER 패치 나머지는 기본 패치 전략 따름.
     */
    @EntityGraph(attributePaths = "post")
    Optional<Reply> getReplyById(Long id);
}
