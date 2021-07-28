package com.grapefruit.consumer.restconfig;

import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import feign.Request;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/7/27 16:09:56
 */
@Configuration
public class MyRestConfig {

    /**
     * 设置负载平衡
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {

        return new RestTemplate(clientHttpRequestFactory);
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {

        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        /**
         * 设置连接超时时
         */
        simpleClientHttpRequestFactory.setConnectTimeout(5 * 60 * 1000);
        simpleClientHttpRequestFactory.setReadTimeout(5 * 10);

        return simpleClientHttpRequestFactory;
    }

    /**
     * openFeign connectTimeoutUnit and readTimeoutUnit settings
     *
     * @return Options
     */
    @Bean
    public Request.Options options() {
        final Request.Options options = new Request.Options(2, TimeUnit.SECONDS,
                2, TimeUnit.SECONDS,
                true);
        return options;
    }
}
