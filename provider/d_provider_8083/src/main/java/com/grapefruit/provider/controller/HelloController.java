package com.grapefruit.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 功能
 *
 * @Author ZhangZhihuang
 * @Date 2022/8/13 13:08
 * @Version 1.0
 */
@RestController
public class HelloController {
    @Value("${server.port}")
    int port;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(value = "time") long time) throws InterruptedException {
        Thread.sleep(time);
        return "port:" + port + " " + LocalDateTime.now() + " time:" + time;
    }
}
