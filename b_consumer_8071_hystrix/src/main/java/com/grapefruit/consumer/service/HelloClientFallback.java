/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.consumer.service;

import org.springframework.stereotype.Component;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-07-28 1:00 下午
 */
@Component
public class HelloClientFallback implements HelloClient {
    public String hello(long time) {
        return "HelloClientFallback hello + 降级处理 inputTime:" + time;
    }
}
