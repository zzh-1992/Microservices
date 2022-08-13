package com.grapefruit.servercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class A_server_8888 {
    public static void main(String[] args) {
        SpringApplication.run(A_server_8888.class, args);
    }
}
