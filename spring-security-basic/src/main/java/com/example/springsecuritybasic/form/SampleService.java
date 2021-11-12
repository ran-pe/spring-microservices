package com.example.springsecuritybasic.form;

import com.example.springsecuritybasic.common.SecurityLogger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void dashboard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("======================");
        System.out.println(authentication);
        System.out.println(userDetails.getUsername());
    }

    public void asyncService() {
        SecurityLogger.log("Async Service");
        System.out.println("Async service is called.");
    }

}
