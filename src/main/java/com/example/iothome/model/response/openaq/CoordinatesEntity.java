package com.example.iothome.model.response.openaq;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoordinatesEntity {
    @JsonProperty("longitude")
    private double longitude;
    @JsonProperty("latitude")
    private double latitude;
}
