/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.consumer.controller;

import com.grapefruit.configuration.MyHystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 模拟自定义熔断(超时)
 *
 * @Author ZhangZhihuang
 * @Date 2023/2/4 09:30
 * @Version 1.0
 */
@RequestMapping("/grape")
@RestController
public class UsingMyHystrixCommand {
    @Autowired
    RestTemplate restTemplate;

    // api http://127.0.0.1:8071/grape/hello?time=50

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "time") int time) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 使用动态"超时配置"的自定义熔断
        MyHystrixCommand command = new MyHystrixCommand(HystrixCommandGroupKey.Factory.asKey("MyHystrixCommand"), restTemplate, time);
        String execute = command.execute();
        stopWatch.stop();

        return "time:" + stopWatch.getTotalTimeMillis() + " " + execute;
    }
}
