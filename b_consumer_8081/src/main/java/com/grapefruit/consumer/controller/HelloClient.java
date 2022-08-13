package com.grapefruit.consumer.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("grapefruit.provider")
public interface HelloClient {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello(@RequestParam(value = "time") long time);
}