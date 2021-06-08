package com.example.iothome.repository;

import com.example.iothome.model.entity.ResponseTypeDTO;
import com.example.iothome.model.response.location.CoordinatesBean;

import java.util.List;

public interface SensorDataLocalRepository {

    void saveDoList(ResponseTypeDTO response);

    List<ResponseTypeDTO> getAllSensorData();

    void saveAllCoordinates(List<CoordinatesBean> coordinates);

    List<CoordinatesBean> getAllCoordinates();

}
