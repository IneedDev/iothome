package com.example.iothome.service;

import com.example.iothome.model.entity.ResponseTypeDTO;

public interface PersistenceService {

    void saveSensorData(ResponseTypeDTO responseTypeDTO);
}
