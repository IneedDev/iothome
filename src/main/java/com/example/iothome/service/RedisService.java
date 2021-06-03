package com.example.iothome.service;

import java.util.Map;

public interface RedisService {

    String getDataFromRedis (String sensorId);
}
