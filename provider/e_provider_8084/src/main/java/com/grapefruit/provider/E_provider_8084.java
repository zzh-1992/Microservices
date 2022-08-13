package com.grapefruit.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class E_provider_8084 {

    public static void main(String[] args) {
        SpringApplication.run(E_provider_8084.class, args);
    }

}
