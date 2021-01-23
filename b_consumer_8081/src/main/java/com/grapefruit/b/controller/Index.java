package com.grapefruit.b.controller;

import com.grapefruit.d.entity.Stu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/7/27 11:50:52
 */
@RestController
public class Index {

    @Value("${server.port}")
    int port;

    @Autowired
    RestTemplate restTemplate;

    //@GetMapping("/")
    public Stu toIndex(Model model) {

        String url = "http://192.168.2.100:8082/";
        //            grapefruit.provider
        url = "http://grapefruit.provider/";
        ResponseEntity<Stu> forEntity = restTemplate.getForEntity(url, Stu.class);

        Stu stu = null;
        if (forEntity.getStatusCode().value() == 200) {
            stu = forEntity.getBody();
        }
        model.addAttribute("stu", stu);
        return stu;
    }

    @GetMapping("/get")
    public String buy(){
        String url = "http://grapefruit.provider/get";
        return restTemplate.getForObject(url,String.class);
    }
}
