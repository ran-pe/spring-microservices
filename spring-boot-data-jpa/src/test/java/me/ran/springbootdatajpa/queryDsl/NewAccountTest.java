package me.ran.springbootdatajpa.queryDsl;

import com.querydsl.core.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class NewAccountTest {

    @Autowired
    NewAccountRepository newAccountRepository;

    @Test
    public void crud() {
        QNewAccount newAccount = QNewAccount.newAccount;
        Predicate predicate = newAccount.firstName.containsIgnoreCase("youngran")
                .and(newAccount.lastName.startsWith("kwon"));

        Optional<NewAccount> one = newAccountRepository.findOne(predicate);
        assertThat(one).isEmpty();
    }
}