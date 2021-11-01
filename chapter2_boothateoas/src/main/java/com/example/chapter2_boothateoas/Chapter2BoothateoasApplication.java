package com.example.chapter2_boothateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootApplication
public class Chapter2BoothateoasApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter2BoothateoasApplication.class, args);
    }

}

@RestController
class GreetingController {

    @RequestMapping("/greet")
    Greet greet() {
        return new Greet("Hello World");
    }

    @RequestMapping("/greeting")
    @ResponseBody
    public HttpEntity<Greet> greeting(@RequestParam(value = "name", required = false, defaultValue = "HATEOAS") String name) {
        Greet greet = new Greet("Hello " + name);
        greet.add(linkTo(methodOn(GreetingController.class)
                .greeting(name))
                .withSelfRel());
        return new ResponseEntity<Greet>(greet, HttpStatus.OK);
    }
}

class Greet extends RepresentationModel {
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