package com.example.springsecuritybasic.form;

import com.example.springsecuritybasic.account.Account;
import com.example.springsecuritybasic.account.AccountContext;
import com.example.springsecuritybasic.account.AccountRepository;
import com.example.springsecuritybasic.account.UserAccount;
import com.example.springsecuritybasic.book.BookRepository;
import com.example.springsecuritybasic.common.CurrentUser;
import com.example.springsecuritybasic.common.SecurityLogger;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@AllArgsConstructor
@Controller
@EnableAsync
public class SampleController {

    SampleService sampleService;

    AccountRepository accountRepository;

    BookRepository bookRepository;

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal UserAccount userAccount) {
        model.addAttribute("pageName", "index");
        if (userAccount == null) {
            model.addAttribute("message", "Hello Spring Security");
        } else {
            model.addAttribute("message", "Hello " + userAccount.getUsername() + ", Spring Security");
        }
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("pageName", "info");
        model.addAttribute("message", "info");

        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account") Account account) {
        model.addAttribute("pageName", "dashboard");
        model.addAttribute("message", "Hello " + account.getUsername());
        AccountContext.setAccount(accountRepository.findByUsername(account.getUsername()));
        sampleService.dashboard();

        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, @CurrentUser Account account) {
        model.addAttribute("pageName", "admin");
        model.addAttribute("message", "Hello Admin, " + account.getUsername());

        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, @CurrentUser Account account) {
        model.addAttribute("pageName", "user");
        model.addAttribute("message", "Hello User, " + account.getUsername());
        model.addAttribute("books", bookRepository.findCurrentUserBooks());

        return "user";
    }

    @GetMapping("/async-handler")
    @ResponseBody
    public Callable<String> asyncHandler() {
        SecurityLogger.log("MVC");
        return () -> {
            SecurityLogger.log("Callable");
            return "Async Handler";
        };
    }

    @GetMapping("/async-service")
    @ResponseBody
    public String asyncService() {
        SecurityLogger.log("MVC, before async service");
        sampleService.asyncService();
        SecurityLogger.log("MVC, after async service");
        return "Async Service";
    }
}
