package com.grapefruit.d;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class D_provider_8083 {

    public static void main(String[] args) {
        SpringApplication.run(D_provider_8083.class, args);
    }

}
