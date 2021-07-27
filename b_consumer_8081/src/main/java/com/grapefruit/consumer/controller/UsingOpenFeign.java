/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用OpenFeign调用
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-07-27 10:54 下午
 */
@RestController
public class UsingOpenFeign {

    @Autowired
    HelloClient client;

    @RequestMapping("/hello")
    public String hello() {
        return client.hello();
    }

    @FeignClient("grapefruit.provider")
    interface HelloClient {
        @RequestMapping(value = "/hello", method = RequestMethod.GET)
        String hello();
    }
}
