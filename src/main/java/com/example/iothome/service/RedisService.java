package com.example.iothome.service;

import com.example.iothome.model.entity.ResponseTypeDTO;
import com.example.iothome.model.response.ResponseType;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface RedisService {

    ResponseTypeDTO getDataFromRedis (String sensorId) throws JsonProcessingException;
}
