package me.ran.springbootdatajpa.account;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("ranran");
        account.setPassword("hibernate");

        Study study = new Study();
        study.setName("Spring Data JPA");
        // study가 주인일때는 study에 account의 정보를 넣어서 저장
//        study.setAccount(account);

        // account가 주인일때는 account에 study의 정보를 넣어서 저장 --> account로 이동
//        account.getStudies().add(study); // optional
//        study.setAccount(account);  // 필수

        account.addStudy(study);

        // entityManager를 이용해 저장
//        entityManager.persist(account);

//        Hibernate Session을 이용해 저장
        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);

        Account account_re = session.load(Account.class, account.getId());
        account_re.setUsername("ran");
        System.out.println("======================");
        System.out.println(account_re.getUsername());
    }
}
