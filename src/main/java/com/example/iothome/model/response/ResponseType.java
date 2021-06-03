package com.example.iothome.model.response;

import lombok.Data;

@Data
public class ResponseType {

    private String number;
    private String time;
    private SensorData data;
}
