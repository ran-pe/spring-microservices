package com.example.chapter2;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;

@SpringBootApplication
public class Chapter2Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter2Application.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return (evt) -> {
            userRepository.save(new User("Adam", "adam@boot.com"));
            userRepository.save(new User("John", "john@boot.com"));
            userRepository.save(new User("Smith", "smith@boot.com"));
            userRepository.save(new User("Edgar", "edgar@boot.com"));
            userRepository.save(new User("Martin", "martin@boot.com"));
            userRepository.save(new User("Tom", "tom@boot.com"));
            userRepository.save(new User("Sean", "sean@boot.com"));
        };
    }
}

@RestController
class UserController {
    @Autowired
    UserRegister userRegister;

    UserController(UserRegister userRegister) {
        this.userRegister = userRegister;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    User register(@RequestBody User user) {
        return userRegister.register(user);
    }
}

@Component
@Lazy
class UserRegister {

    UserRepository userRepository;
    Sender sender;

    @Autowired
    UserRegister(UserRepository userRepository, Sender sender) {
        this.userRepository = userRepository;
        this.sender = sender;
    }

    User register(User user) {
        Optional<User> existingUser = userRepository.findByName(user.getName());
        if (existingUser.isPresent()) {
            throw new RuntimeException("is already exists");
        } else {
            userRepository.save(user);
            sender.send(user.getEmail());
        }
        return user;
    }

}

@Component
@Lazy
class Sender {

    RabbitMessagingTemplate template;

    @Autowired
    Sender(RabbitMessagingTemplate template) {
        this.template = template;
    }

    @Bean
    Queue queue() {
        return new Queue("UserQ", false);
    }

    public void send(String message) {
        template.convertAndSend("UserQ", message);
    }
}

@RepositoryRestResource
@Lazy
interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(@Param("name") String name);
}

@Entity
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;

    public User() {
    }

    public User(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
