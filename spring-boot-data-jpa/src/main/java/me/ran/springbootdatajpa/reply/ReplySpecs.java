package me.ran.springbootdatajpa.reply;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ReplySpecs {
    public static Specification<Reply> isBest() {
        return new Specification<Reply>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Reply> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.isTrue(root.get(Reply_.best));
            }
        };
    }

    public static Specification<Reply> isGood() {
        return new Specification<Reply>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Reply> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(Reply_.up), 10);
            }
        };
    }
}
