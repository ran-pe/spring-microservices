package com.example.chapter2_swagger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Greet {
    private String message;
}