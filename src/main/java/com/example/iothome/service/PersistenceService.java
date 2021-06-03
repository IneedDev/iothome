package com.example.iothome.service;

import com.example.iothome.model.response.ResponseType;

public interface PersistenceService {

    void saveSensorData(String sensorId, ResponseType responseType);
}
