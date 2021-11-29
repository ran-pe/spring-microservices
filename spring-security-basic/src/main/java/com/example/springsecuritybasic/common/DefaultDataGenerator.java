package com.example.springsecuritybasic.common;

import com.example.springsecuritybasic.account.Account;
import com.example.springsecuritybasic.account.AccountService;
import com.example.springsecuritybasic.book.Book;
import com.example.springsecuritybasic.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataGenerator implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account user_ran = createUser("ran");
        Account user_ran1 = createUser("ran1");
        createBook("spring-security", user_ran);
        createBook("jpa", user_ran1);
    }

    private void createBook(String title, Account account) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(account);
        bookRepository.save(book);
    }

    private Account createUser(String username) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword("123");
        account.setRole("USER");
        return accountService.createNew(account);
    }
}
