package com.example.iothome.config.redis;

import com.example.iothome.config.EnvHelper;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.resource.ClientResources;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@RequiredArgsConstructor
public class RedisStandaloneConnection {

    private final EnvHelper envHelper;
    private static final RedisClient redisClient = RedisClient.create(ClientResources.builder().ioThreadPoolSize(1).computationThreadPoolSize(1).build());

    public StatefulRedisConnection<String, String> getConnection() {
        redisClient.setDefaultTimeout(Duration.ofMillis(envHelper.getRedisConnectionTimeout()));
        redisClient.setOptions(ClientOptions.builder()
                .timeoutOptions(TimeoutOptions.builder()
                        .fixedTimeout(Duration.ofMillis(envHelper.getRedisConnectionTimeout()))
                        .timeoutCommands(true).build())
                .build());
        RedisURI redisUriBuilder = RedisURI.Builder.redis("xxxx", 6379).withPassword("xxxxx").withDatabase(0).build();
//        RedisURI redisURI = RedisURI.create(envHelper.getSchema() + "://" + envHelper.getRedisEndpoint().get(0) + "/" + envHelper.getDatabaseName()+ "/" + "Sasanka0101");
        return redisClient.connect(redisUriBuilder);
    }


}
