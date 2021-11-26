package com.example.springsecuritybasic.form;

import com.example.springsecuritybasic.account.Account;
import com.example.springsecuritybasic.account.AccountContext;
import com.example.springsecuritybasic.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;

@Service
public class SampleService {

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
//    @RolesAllowed()
//    @PreAuthorize("hasRole('USER')")
//    @PostAuthorize()
    public void dashboard() {
        // 같은 스레드에서 account를 공유(SecurityContextHolder의 기본 전략)
        Account account = AccountContext.getAccount();
        System.out.println("======================");
        System.out.println(account.getUsername());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("======================");
        System.out.println(authentication);
        System.out.println(userDetails.getUsername());
    }

    @Async
    public void asyncService() {
        SecurityLogger.log("Async Service");
        System.out.println("Async service is called.");
    }

}
