package com.example.chapter2_bootactuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.concurrent.atomic.LongAdder;

@SpringBootApplication
public class Chapter2BootactuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter2BootactuatorApplication.class, args);
    }

}

/**
 * 1분동안의 트랜잭션 수를 카운트
 * 1분에 2개 이상의 트랜잭션을 받으면 서버상태가 'Out of Service'로 설정
 */
class TPSCounter {
    LongAdder count;
    int threshold = 2;
    Calendar expiry = null;

    TPSCounter() {
        this.count = new LongAdder();
        this.expiry = Calendar.getInstance();
        this.expiry.add(Calendar.MINUTE, 1);
    }

    // 1분이 경과했는지 검사
    boolean isExpired() {
        return Calendar.getInstance().after(expiry);
    }

    // 1분동안의 트랜잭션 수가 허용치 이내에 있는지 검사
    boolean isWeak() {
        return (count.intValue() > threshold);
    }

    // 트랜잭션 요청에 따라 단순히 카운트 증가
    void increment() {
        count.increment();
    }

}

@Component
class TPSHealth implements HealthIndicator {
    TPSCounter counter;

    @Override
    public Health health() {
        boolean health = counter.isWeak();  // health 체크
        if (health) {
            return Health.outOfService()
                    .withDetail("Too many requests", "OutofService")
                    .build();
        }
        return Health.up().build();
    }

    void updateTx() {
        if (counter == null || counter.isExpired()) {
            counter = new TPSCounter();
        }
        counter.increment();
    }
}

@RestController
class GreeterController {
    private static final Logger logger = LoggerFactory.getLogger(GreeterController.class);

    TPSHealth health;
    CounterService counterService;
    GaugeService gaugeService;

    @Autowired
    GreeterController(TPSHealth health, CounterService counterService, GaugeService gaugeService) {
        this.health = health;
        this.counterService = counterService;
        this.gaugeService = gaugeService;
    }

    @CrossOrigin
    @RequestMapping("/")
    Greet greet() {
        logger.info("Service Request....!!!");
        health.updateTx();
        return new Greet("Hello World");
    }

    @CrossOrigin
    @RequestMapping("/greet2")
    Greet greet2() {
        logger.info("Serving Request....!!!");
        health.updateTx();
        this.counterService.increment("greet.txnCount");
        gaugeService.submit("greet.customgauge", 1.0);
        return new Greet("Hello World");
    }
}

class Greet {
    private String massage;

    public Greet() {
    }

    public Greet(String massage) {
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
