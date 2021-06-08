package com.example.iothome.model.response.openaq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResultsEntity2 {
    @JsonProperty("measurements")
    private int measurements;
    @JsonProperty("firstUpdated")
    private String firstupdated;
    @JsonProperty("lastUpdated")
    private String lastupdated;
    @JsonProperty("coordinates")
    private CoordinatesEntity coordinates;
    @JsonProperty("sensorType")
    private String sensortype;
    @JsonProperty("parameters")
    private List<ParametersEntity> parameters;
    @JsonProperty("isAnalysis")
    private boolean isanalysis;
    @JsonProperty("isMobile")
    private boolean ismobile;
    @JsonProperty("sources")
    private List<SourcesEntity> sources;
    @JsonProperty("country")
    private String country;
    @JsonProperty("entity")
    private String entity;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private int id;
}
