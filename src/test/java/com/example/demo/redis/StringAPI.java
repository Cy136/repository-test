package com.example.demo.redis;

import api.redis.RedisSyncConnect;
import io.lettuce.core.KeyValue;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class StringAPI {

    @Test
    public void test1(){
        RedisCommands redisCommands = RedisSyncConnect.creatSyncConnect();


        String uuu = redisCommands.lset("user:2", 0, "uuu");
        System.out.println("uuu = " + uuu);
        Object lindex = redisCommands.lindex("user:2", 0);
        System.out.println("lindex = " + lindex);
    }
}
