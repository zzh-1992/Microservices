package com.grapefruit.c.controller;

import com.grapefruit.c.entity.Stu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/7/27 11:50:52
 */
//@RestController
public class Index {

    @Value("${server.port}")
    int port;

    @GetMapping("/")
    public Stu toIndex() {

        Stu stu = new Stu();
        stu.setAge(2020);
        stu.setName("grapefruit");
        stu.setSex("boy " + port);
        return stu;
    }
}
