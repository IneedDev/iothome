package com.example.iothome.service.impl;

import com.example.iothome.config.EnvHelper;
import com.example.iothome.config.redis.RedisStandaloneConnection;
import com.example.iothome.service.RedisService;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.cluster.api.async.RedisClusterAsyncCommands;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {


    private final EnvHelper envHelper;
    private RedisStandaloneConnection redisStandaloneConnection;

    @PostConstruct
    void create() {
        this.redisStandaloneConnection = new RedisStandaloneConnection(envHelper);
    }

    @Override
    public String getDataFromRedis(String sensorId) {
        StatefulRedisConnection<String, String> connection = redisStandaloneConnection.getConnection();
        RedisClusterAsyncCommands<String, String> commands = connection.async();
        RedisFuture<String> futures = commands.get(sensorId);
        String resultRedis = null;
        try {
            resultRedis = futures.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(resultRedis);
        return resultRedis;
    }
}
