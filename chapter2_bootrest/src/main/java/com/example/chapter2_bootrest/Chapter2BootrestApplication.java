package com.example.chapter2_bootrest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Chapter2BootrestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter2BootrestApplication.class, args);
    }

}

@Slf4j
@RestController
class GreetingController {

    @Autowired
    Environment environment;

    Greet greet() {
        log.info("bootrest.customproperty " + environment.getProperty("bootrest.customproperty"));
        return new Greet("Hello World!");
    }

    @RequestMapping("/")
    Greet sayHello() {
        return greet();
    }
}

class Greet {
    private String message;

    public Greet() {
    }

    public Greet(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
