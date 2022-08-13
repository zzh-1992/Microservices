package com.grapefruit.consumer.restconfig;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        /**
         * 设置连接超时时
         */
        simpleClientHttpRequestFactory.setConnectTimeout(500);
        simpleClientHttpRequestFactory.setReadTimeout(500);
        return simpleClientHttpRequestFactory;
    }

    /**
     * 设置负载平衡
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        return new RestTemplate(clientHttpRequestFactory);
    }

    /**
     * openFeign connectTimeoutUnit and readTimeoutUnit settings
     *
     * @return Options
     */
/*    @Bean
    public Request.Options options() {
        return new Request.Options(3000, TimeUnit.MILLISECONDS,
                3000, TimeUnit.MILLISECONDS,
                true);
    }*/
}
