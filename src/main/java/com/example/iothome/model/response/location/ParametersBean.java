package com.example.iothome.model.response.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParametersBean {
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
