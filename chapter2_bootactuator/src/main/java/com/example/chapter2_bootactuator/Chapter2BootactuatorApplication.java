package com.example.chapter2_bootactuator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Chapter2BootactuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter2BootactuatorApplication.class, args);
    }

}

@Slf4j
@RestController
class GreeterController {

    @RequestMapping("/")
    Greet sayHello() {
        return greet();
    }

    Greet greet() {
        return new Greet("Hello World");
    }

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Greet {
    private String massage;
}
