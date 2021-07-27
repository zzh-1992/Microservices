package com.grapefruit.configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载均衡算法规则
 * https://projects.spring.io/spring-cloud/spring-cloud.html#_customizing_the_ribbon_client
 *
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/7/27 16:09:56
 */
@Configuration
public class ServerRibbonConfig {
    /**
     * 设置负载平衡策略-随机匹配
     *
     * @return IRule
     */
    @Bean
    public IRule iRule() {
        //随机
        return new RandomRule();
    }
}
