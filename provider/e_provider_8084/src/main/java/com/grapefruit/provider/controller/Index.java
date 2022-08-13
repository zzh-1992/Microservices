package com.grapefruit.provider.controller;

import com.grapefruit.provider.entity.Stu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/7/27 11:50:52
 */
@RestController
public class Index {

    @Value("${server.port}")
    int port;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/")
    public Stu toIndex() {

        Stu stu = new Stu();
        stu.setAge(2020);
        stu.setName("grapefruit");
        stu.setSex("boy " + port);
        return stu;
    }

    @GetMapping("/client")
    public DiscoveryClient getClient() {
        return discoveryClient;
    }
}
