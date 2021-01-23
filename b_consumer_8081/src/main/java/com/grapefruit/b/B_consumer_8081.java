package com.grapefruit.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class B_consumer_8081 {

    public static void main(String[] args) {
        SpringApplication.run(B_consumer_8081.class, args);
    }

}
