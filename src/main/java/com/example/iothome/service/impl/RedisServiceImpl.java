package com.example.iothome.service.impl;

import com.example.iothome.config.EnvHelper;
import com.example.iothome.config.redis.RedisStandaloneConnection;
import com.example.iothome.exception.IoTHomeException;
import com.example.iothome.mapper.ResponseMapper;
import com.example.iothome.model.entity.ResponseTypeDTO;
import com.example.iothome.model.response.ResponseType;
import com.example.iothome.service.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private static final ObjectMapper mapper = new ObjectMapper();


    @PostConstruct
    void create() {
        this.redisStandaloneConnection = new RedisStandaloneConnection(envHelper);
    }

    @Override
    public ResponseTypeDTO getDataFromRedis(String sensorId) throws JsonProcessingException {
        StatefulRedisConnection<String, String> connection = redisStandaloneConnection.getConnection();
        RedisClusterAsyncCommands<String, String> commands = connection.async();
        RedisFuture<String> futures = commands.get(sensorId);
        String responseType = null;
        try {
            responseType = futures.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (responseType == null) {
            throw new IoTHomeException("Redis returns null");
        }
        ResponseTypeDTO dto = ResponseMapper.responseMapperToDTO("", mapper.readValue(responseType.replace("'", "\""), ResponseType.class));
        return dto;
    }
}
