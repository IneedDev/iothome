package com.example.iothome.controller;

import com.example.iothome.controller.endpoint.IoTHomeEndpoint;
import com.example.iothome.model.entity.ResponseTypeDTO;
import com.example.iothome.model.response.GlobalResponseModel;
import com.example.iothome.model.response.ResponseType;
import com.example.iothome.repository.SensorDataLocalRepository;
import com.example.iothome.service.PersistenceService;
import com.example.iothome.service.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/iothome")
public class IoTHomeController implements IoTHomeEndpoint {

    private static final String sensorId = "000000001";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    RedisService redisService;

    @Autowired
    SensorDataLocalRepository sensorDataLocalRepository;

    //temporary
    @Autowired
    PersistenceService persistenceService;

    @Override
    public ResponseEntity<GlobalResponseModel> getCurrentSensorData() throws JsonProcessingException {

        ResponseType responseType = mapper.readValue(redisService.getDataFromRedis(sensorId).replace("'", "\""), ResponseType.class);
        ResponseTypeDTO dto = new ResponseTypeDTO();
        dto.setSensor_id(sensorId);
        dto.setTemperature(Double.parseDouble(responseType.getData().getTemperature()));
        dto.setHumidity(Double.parseDouble(responseType.getData().getHumidity()));
        dto.setDateCreated(responseType.getTime());
        sensorDataLocalRepository.saveDoList(dto);
        System.out.println("Sensor data list: " + sensorDataLocalRepository.getAllSensorData().size());

        persistenceService.saveSensorData(sensorId, responseType);

        return ResponseEntity
                .status(200)
                .body(GlobalResponseModel.builder()
                        .payload(responseType)
                        .status(GlobalResponseModel.Status.OK_WITH_RESPONSE)
                        .build()
                );
    }

    @GetMapping(value="/getList", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getCurrentDataSensorListSize() {
        System.out.println(sensorDataLocalRepository.getAllSensorData().size());
        return sensorDataLocalRepository.getAllSensorData().toString();
    }

//    connection to esp server return webpage
//    String url = "http://192.168.0.95/api";
//        ResponseType response = restTemplate.getForObject(url, ResponseType.class);
}
