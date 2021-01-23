package com.grapefruit.c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class C_provider_8082 {

    public static void main(String[] args) {
        SpringApplication.run(C_provider_8082.class, args);
    }

}
