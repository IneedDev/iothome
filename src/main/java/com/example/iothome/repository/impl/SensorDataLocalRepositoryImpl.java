package com.example.iothome.repository.impl;

import com.example.iothome.model.entity.ResponseTypeDTO;
import com.example.iothome.model.response.location.CoordinatesBean;
import com.example.iothome.repository.SensorDataLocalRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SensorDataLocalRepositoryImpl implements SensorDataLocalRepository {

    private List<ResponseTypeDTO> sensorData = new ArrayList<>();
    private List<CoordinatesBean> coordinates = new ArrayList<>();

    @Override
    public void saveDoList(ResponseTypeDTO response) {
        sensorData.add(response);
    }

    @Override
    public List<ResponseTypeDTO> getAllSensorData() {
        return sensorData;
    }

    @Override
    public void saveAllCoordinates(List<CoordinatesBean> coordinates) {
        this.coordinates = coordinates;

    }
    @Override
    public List<CoordinatesBean> getAllCoordinates() {
        return coordinates;
    }
}
