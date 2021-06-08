package com.example.iothome.controller;

import com.example.iothome.controller.endpoint.IoTHomeEndpoint;
import com.example.iothome.model.entity.ResponseTypeDTO;
import com.example.iothome.model.response.GlobalResponseModel;
import com.example.iothome.repository.SensorDataLocalRepository;
import com.example.iothome.service.PersistenceService;
import com.example.iothome.service.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/iothome")
public class IoTHomeController implements IoTHomeEndpoint {

    private static final String sensorId = "000000001";

    @Autowired
    RedisService redisService;

    @Autowired
    SensorDataLocalRepository sensorDataLocalRepository;

    //temporary
    @Autowired
    PersistenceService persistenceService;

    @Override
    public ResponseEntity<GlobalResponseModel> getCurrentSensorData(String sensorId) throws Exception {
        ResponseTypeDTO dto = redisService.getDataFromRedis(sensorId);
        //TODO separete method
        sensorDataLocalRepository.saveDoList(dto);
        System.out.println("Sensor data list: " + sensorDataLocalRepository.getAllSensorData().size());

        persistenceService.saveSensorData(dto);

        return ResponseEntity
                .status(200)
                .body(GlobalResponseModel.builder()
                        .payload(dto)
                        .status(GlobalResponseModel.Status.OK_WITH_RESPONSE)
                        .build()
                );
    }

    @GetMapping(value="/getList", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getCurrentDataSensorListSize() {
        System.out.println(sensorDataLocalRepository.getAllSensorData().size());
        return sensorDataLocalRepository.getAllSensorData().toString();
    }
}
