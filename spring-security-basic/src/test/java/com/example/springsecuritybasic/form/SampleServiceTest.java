package com.example.springsecuritybasic.form;

import com.example.springsecuritybasic.account.Account;
import com.example.springsecuritybasic.account.AccountService;
import com.example.springsecuritybasic.account.WithAdmin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleServiceTest {

    @Autowired
    SampleService sampleService;

    @Autowired
    AccountService accountService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Test
    public void dashboard() {

//        // 아래 소스코드는 @WithAdmin 으로 대체가능
        Account account = new Account();
        account.setUsername("user");
        account.setPassword("123");
        account.setRole("ADMIN");
        accountService.createNew(account);

        UserDetails userDetails = accountService.loadUserByUsername("user");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, "123");
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        sampleService.dashboard();
    }

}