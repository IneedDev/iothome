package com.example.iothome.model.response.openaq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ParametersEntity {
    @JsonProperty("firstUpdated")
    private String firstupdated;
    @JsonProperty("parameterId")
    private int parameterid;
    @JsonProperty("lastUpdated")
    private String lastupdated;
    @JsonProperty("displayName")
    private String displayname;
    @JsonProperty("parameter")
    private String parameter;
    @JsonProperty("lastValue")
    private double lastvalue;
    @JsonProperty("average")
    private double average;
    @JsonProperty("count")
    private int count;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("id")
    private int id;
}
