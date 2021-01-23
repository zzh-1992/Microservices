package com.grapefruit.d.controller;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/11/12 19:30:54
 */
@RestController
@RequestMapping("/")
public class RedisOrderWithRedissonLock {
    @Autowired
    private static final String KEY = "key";

    private static final String LOCK = "lock";

    @Value("${server.port}")
    private String serverPort;

//    @Autowired
//    Jedis jedis;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    
    

    /*@GetMapping("/get")
    public String get(){
        // 先获取值
        int i = Integer.parseInt(jedis.get(KEY));
        // 每次请求减1
        i--;
        // 写入redis
        jedis.set("key", i+"");
        // 返回余量
        String value = jedis.get(KEY);
        System.out.println("8081  余量:" + value);
        return "8081  " + value;
    }*/
    @GetMapping("/get")
    public synchronized String get(){
        while (!tryLock()){}
        // 先获取值
        int i = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get(KEY)));
        if(i == 0){
            releaseLock();
            System.out.println("8080  余量:" + "ByBy");
            return "ByBy";
        }
        // 每次请求减1
        i = i - 1;
        // 写入redis
        redisTemplate.opsForValue().set(KEY, i +"");
        //jedis.set("key", i+"");
        // 返回余量
        String value = redisTemplate.opsForValue().get(KEY);
        System.out.println( serverPort+ "  余量:" + value);
        //任务完成释放锁
        releaseLock();
        return serverPort + "  "+ value;
    }


    @GetMapping("/set")
    public String set(){
        redisTemplate.opsForValue().set(KEY,"100");
        return "=====";
    }

    public boolean tryLock(){
        //ValueOperations ops = redisTemplate.opsForValue();
        //return ops.setIfAbsent(LOCK, LOCK,2, TimeUnit.SECONDS);
        return redissonClient.getLock(LOCK).tryLock();
    }

    /*public boolean releaseLock(){
        return redisTemplate.delete(LOCK);
    }*/

    public void releaseLock(){
        redissonClient.getLock(LOCK).unlock();
    }
}

