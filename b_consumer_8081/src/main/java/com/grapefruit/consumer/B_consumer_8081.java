package com.grapefruit.consumer;

import com.grapefruit.configuration.ServerRibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "grapefruit.provider",configuration = ServerRibbonConfig.class)
@EnableFeignClients
public class B_consumer_8081 {

    public static void main(String[] args) {
        SpringApplication.run(B_consumer_8081.class, args);
    }

}
