package com.example.iothome.model.response.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CoordinatesBean {
    @JsonProperty("longitude")
    private double longitude;
    @JsonProperty("latitude")
    private double latitude;
}
