package com.grapefruit.d.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/7/27 16:09:56
 */
@Configuration
public class MyRestConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {

        return new RestTemplate(clientHttpRequestFactory);
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {

        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        /**
         * 设置连接超时时间
         */
        simpleClientHttpRequestFactory.setConnectTimeout(5 * 60 * 1000);

        return simpleClientHttpRequestFactory;
    }
}
