package com.example.querydlssample.account;

import com.querydsl.core.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StudyRepository studyRepository;


    @Test
    public void crud() {

        Predicate predicate = QAccount.account
                .firstname.containsIgnoreCase("youngran")
                .and(QAccount.account.lastname.startsWith("kwon"));

        Optional<Account> one = accountRepository.findOne(predicate);
        assertThat(one).isEmpty();

    }

    @Test
    public void searchStudy() {
        List<StudyDto> studyList = studyRepository.findByKeywordAndDate("Spring", "20211016", "20211031");
        assertThat(studyList).isNotEmpty();
    }
}