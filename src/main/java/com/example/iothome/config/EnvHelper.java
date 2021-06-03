package com.example.iothome.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class EnvHelper {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUserName;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Value("${spring.datasource.driverClassName}")
    private String datasourceDriverClassName;

    @Value("${redis.redisEndpoint}")
    private List<String> redisEndpoint;

    @Value("${redis.databaseName}")
    private int databaseName;

    @Value("${redis.schema}")
    private String schema;

    @Value("${redis.redisConnectionTimeout}")
    private int redisConnectionTimeout;

    @Value("${job.cron}")
    private String jobCron;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

}
