package api.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;


public class RedisSyncConnect {

    public static RedisCommands creatSyncConnect() {
        // 获取redis客户端
        RedisURI redisURI = RedisURI.builder()
                .withHost("192.168.220.129")
                .withPort(6379)
                .build();

        RedisClient redisClient = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        RedisCommands<String, String> commands = connect.sync();

        return commands;
    }
}
