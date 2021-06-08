package com.example.iothome.scheduler;

import com.example.iothome.config.EnvHelper;
import com.example.iothome.mapper.ResponseMapper;
import com.example.iothome.model.entity.ResponseTypeDTO;
import com.example.iothome.model.response.ResponseType;
import com.example.iothome.repository.SensorDataLocalRepository;
import com.example.iothome.repository.SensorDataPersistenceRepository;
import com.example.iothome.service.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobScheduler {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final EnvHelper envHelper;
    private static int counter = 0;
    private static final String sensorId = "000000001";


    @Autowired
    SensorDataLocalRepository sensorDataLocalRepository;

    @Autowired
    SensorDataPersistenceRepository sensorDataPersistenceRepository;

    @Autowired
    RedisService redisService;

    @Scheduled(cron = "${job.cron}")
    public void getRedisDataJob() {
        log.info("Running scheduled job: {}", envHelper.getJobCron());
        try {
            sensorDataLocalRepository.saveDoList(redisService.getDataFromRedis(sensorId));
        } catch (JsonProcessingException | IllegalAccessException e) {
            e.printStackTrace();
        }
        List<ResponseTypeDTO> list = sensorDataLocalRepository.getAllSensorData();
        if (list.size() == envHelper.getBatchSize()) {
            sensorDataPersistenceRepository.saveAll(sensorDataLocalRepository.getAllSensorData());
            list.clear();

        }
        log.info("Local list size: {}", sensorDataLocalRepository.getAllSensorData().size());
    }
}
