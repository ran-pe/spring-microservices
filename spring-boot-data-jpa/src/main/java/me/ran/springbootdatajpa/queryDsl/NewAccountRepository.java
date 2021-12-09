package me.ran.springbootdatajpa.queryDsl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface NewAccountRepository extends JpaRepository<NewAccount, Long>, QuerydslPredicateExecutor<NewAccount> {
}
