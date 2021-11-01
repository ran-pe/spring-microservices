package com.example.chapter2_bootrest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GreetTest.class)
class GreetTest {

    @Test
    public void testVanillaService() {
        RestTemplate restTemplate = new RestTemplate();
        Greet greet = restTemplate.getForObject("http://localhost:8080", Greet.class);
        Assert.assertEquals("Hello World!", greet.getMessage());
    }

    @Test
    public void testSecureService() {
        String plainCreds = "guest:guest123";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " + new String(Base64.encode(plainCreds.getBytes())));
        HttpEntity<String> request = new HttpEntity<String>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Greet> response = restTemplate.exchange("http://localhost:9080", HttpMethod.GET, request, Greet.class);
        Assert.assertEquals("Hello World!", response.getBody().getMessage());
    }
}