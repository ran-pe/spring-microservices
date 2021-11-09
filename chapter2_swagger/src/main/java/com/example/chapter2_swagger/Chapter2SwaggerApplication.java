package com.example.chapter2_swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Chapter2SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter2SwaggerApplication.class, args);
    }

}

@RestController
class GreeterController {

    @GetMapping("/greet1")
    Greet greet1() {
        return new Greet("greet1!!!");
    }

    @GetMapping("/greet2")
    Greet greet2() {
        return new Greet("greet2!!!");
    }

    @PostMapping("/greet3")
    Greet greet3() {
        return new Greet("greet3!!!");
    }
}

class Greet {
    private String message;

    public Greet() {}

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