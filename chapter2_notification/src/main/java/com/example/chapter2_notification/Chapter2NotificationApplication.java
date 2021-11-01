package com.example.chapter2_notification;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class Chapter2NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter2NotificationApplication.class, args);
    }

}

@Component
class Receiver {
    @Autowired
    Mailer mailer;

    @Bean
    Queue queue() {
        return new Queue("UserQ", false);
    }

    @RabbitListener(queues = "UserQ")
    public void processMessage(String email) {
        System.out.println(email);
        mailer.sendMail(email);
    }
}

@Component
class Mailer {
    private JavaMailSender javaMailSender;

    Mailer() {}

    public void sendMail(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Registration");
        simpleMailMessage.setText("Successfully Registered");

        javaMailSender.send(simpleMailMessage);
    }
}
