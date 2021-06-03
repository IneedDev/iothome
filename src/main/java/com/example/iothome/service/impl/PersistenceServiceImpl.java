package com.example.iothome.service.impl;

import com.example.iothome.model.entity.ResponseTypeDTO;
import com.example.iothome.model.response.ResponseType;
import com.example.iothome.model.response.SensorData;
import com.example.iothome.repository.SensorDataLocalRepository;
import com.example.iothome.repository.SensorDataPersistenceRepository;
import com.example.iothome.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistenceServiceImpl implements PersistenceService {

    @Autowired
    SensorDataPersistenceRepository sensorDataPersistenceRepository;

    @Autowired
    SensorDataLocalRepository sensorDataLocalRepository;

    @Override
    public void saveSensorData(String sensorId, ResponseType responseType) {
        sensorDataPersistenceRepository.save(prepareResponseDTO(sensorId, responseType));
    }


    public List<ResponseTypeDTO> getAllSensorDataForChart() {
        return sensorDataPersistenceRepository.findAll();
    }

    public List<ResponseTypeDTO> getAllCurrentSensorDataForChart() {
        return sensorDataLocalRepository.getAllSensorData();
    }

    private ResponseTypeDTO prepareResponseDTO(String sensorId, ResponseType responseType) {
        ResponseTypeDTO dto = new ResponseTypeDTO();
        dto.setSensor_id(sensorId);
        dto.setTemperature(Double.parseDouble(responseType.getData().getTemperature()));
        dto.setHumidity(Double.parseDouble(responseType.getData().getHumidity()));
        dto.setDateCreated(responseType.getTime());
        return dto;
    }
}
