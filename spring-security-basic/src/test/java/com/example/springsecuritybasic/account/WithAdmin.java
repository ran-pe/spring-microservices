package com.example.springsecuritybasic.account;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username="admin", password = "123", roles = "ADMIN")
public @interface WithAdmin {
}
