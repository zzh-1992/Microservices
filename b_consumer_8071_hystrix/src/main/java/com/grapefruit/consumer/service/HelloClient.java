/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhihuangzhang
 */
@FeignClient(value = "grapefruit.provider", fallback = HelloClientFallback.class)
public interface HelloClient {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello();

    @RequestMapping(value = "/helloTimeout", method = RequestMethod.GET)
    String helloTimeout();
}