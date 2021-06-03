package com.example.iothome.service.impl;

import com.example.iothome.model.entity.ResponseTypeDTO;
import com.example.iothome.model.response.ResponseType;
import com.example.iothome.model.response.SensorData;
import com.example.iothome.repository.SensorDataPersistenceRepository;
import com.example.iothome.service.PersistenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersistenceServiceImplTest {


    @Autowired
    PersistenceService persistenceService;

    @Autowired
    SensorDataPersistenceRepository sensorDataPersistenceRepository;

    @Test
    void shouldSaveSensorDataInBatch() {
        List<ResponseTypeDTO> list = new ArrayList<>();

        for (int i = 0; i < 110; i++) {
            SensorData data = new SensorData();
            data.setHumidity("45");
            data.setTemperature("20");
            ResponseType responseType = new ResponseType();
            responseType.setNumber("123456789");
            responseType.setData(data);

            SensorData dataDto = responseType.getData();
            ResponseTypeDTO dto = new ResponseTypeDTO();
            dto.setSensor_id("123456");
            dto.setTemperature(Double.parseDouble(data.getTemperature()));
            dto.setHumidity(Double.parseDouble(data.getHumidity()));

            list.add(dto);
//            persistenceService.saveSensorData("test_batch", responseType);

        }
        sensorDataPersistenceRepository.saveAll(list);

    }
}