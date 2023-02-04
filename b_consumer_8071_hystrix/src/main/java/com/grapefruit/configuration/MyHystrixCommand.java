/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.configuration;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.apache.commons.configuration.AbstractConfiguration;
import org.springframework.web.client.RestTemplate;

/**
 * 自定义熔断
 *
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2023/02/04
 */
//注意：有两个HystrixCommand：一个是注解，一个是抽象类。千万不要导错了包！
public class MyHystrixCommand extends HystrixCommand<String> {
    private RestTemplate restTemplate;

    public MyHystrixCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    public void setDynamicParameter(int executionTimeout) {
        // TODO 使用"类名"
        String commandKey = "MyHystrixCommand"; // must fit to the used key

        // 下面这行代码是固定要求
        String prefix = "hystrix.command." + commandKey + ".";
        AbstractConfiguration config = ConfigurationManager.getConfigInstance();
        config.setProperty(prefix + "execution.isolation.thread.timeoutInMilliseconds", executionTimeout);
        //config.setProperty(prefix + "circuitBreaker.sleepWindowInMilliseconds", circuit_sleep_window);

        // check that it worked ;-)
        HystrixCommandProperties props = getProperties();
        System.out.println("ExecutionTimeoutMillis \t" + getProperties().executionTimeoutInMilliseconds().get());
        System.out.println("CircuitBreakerSleepMs  \t" + getProperties().circuitBreakerSleepWindowInMilliseconds().get());
    }

    public MyHystrixCommand(HystrixCommandGroupKey group, RestTemplate restTemplate, int timeout) {
        super(group, timeout);
        this.restTemplate = restTemplate;

        // 设置动态参数
        setDynamicParameter(timeout);
    }

    @Override
    protected String run() throws Exception {
        // TODO 具体业务逻辑

        // TODO 模拟业务超时
        Thread.sleep(400);

        // TODO 模拟业务异常
        //int i = 1 / 0;

        // 返回结果
        return "run------>";
    }

    @Override
    protected String getFallback() {
        // 服务降级(兜底处理)
        // 超时、异常等

        // 获取异常信息
        String error = super.getExecutionException().getMessage();

        // 返回业务的默认结果
        return "fallback---------> super.getExecutionException().getMessage():" + error;
    }
}
