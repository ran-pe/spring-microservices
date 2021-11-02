package com.example.chapter2_bootrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@EnableAuthorizationServer
@SpringBootApplication
public class Chapter2BootrestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter2BootrestApplication.class, args);
    }

}

@RestController
class GreetingController {

    private static Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    Environment environment;

    Greet greet() {
        logger.info("bootrest.customproperty " + environment.getProperty("bootrest.customproperty"));
        return new Greet("Hello World!");
    }

    @CrossOrigin
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
