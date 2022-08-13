/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/7/27 11:50:52
 */
@DefaultProperties(defaultFallback = "defaultFallbackHandler", commandProperties = {
        @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "2000")
})
@RestController
public class Index {

    @Value("${server.port}")
    int port;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/inormal")
    public String inormal() {

        String s = Thread.currentThread().getName() + " 正常调用 ";
        return s + LocalDateTime.now();
    }

    @HystrixCommand(fallbackMethod = "itimeoutFallBack", commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "2000")
    })
    @GetMapping("/itimeout")
    public String itimeout(@RequestParam(value = "time") long time) {
        String s = Thread.currentThread().getName() + "itimeout正常调用 ";
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //int i = 1/0;
        return s + LocalDateTime.now();
    }

    public String itimeoutFallBack(int time) {
        String s = Thread.currentThread().getName() + " itimeout FallBack() ";
        return "input time:" + time + " " + s + LocalDateTime.now();
    }

    @HystrixCommand
    @GetMapping("/itimeout2")
    public String itimeout2(@RequestParam(value = "time") long time) {
        String s = Thread.currentThread().getName() + " itimeout2 正常调用";
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //int i = 1/0;
        return s + LocalDateTime.now();
    }

    public String defaultFallbackHandler() {
        String s = Thread.currentThread().getName() + " using defaultFallbackHandler() ";
        return s + LocalDateTime.now();
    }
}
