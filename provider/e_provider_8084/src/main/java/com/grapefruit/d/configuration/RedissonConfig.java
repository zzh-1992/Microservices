package com.grapefruit.d.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;
    
    @Bean
    public RedissonClient getRedissonClient(){
        Config config = new Config();
        config.useSingleServer().setTimeout(1000000).setAddress("redis://" + host + ":" + port).setPassword(password);

        return Redisson.create(config);
    }
}
