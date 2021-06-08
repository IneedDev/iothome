package com.example.iothome.model.response.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResultsBean {
    @JsonProperty("measurements")
    private int measurements;
    @JsonProperty("firstUpdated")
    private String firstupdated;
    @JsonProperty("lastUpdated")
    private String lastupdated;
    @JsonProperty("coordinates")
    private CoordinatesBean coordinates;
    @JsonProperty("sensorType")
    private String sensortype;
    @JsonProperty("parameters")
    private List<ParametersBean> parameters;
    @JsonProperty("isAnalysis")
    private boolean isanalysis;
    @JsonProperty("isMobile")
    private boolean ismobile;
    @JsonProperty("sources")
    private List<SourcesBean> sources;
    @JsonProperty("country")
    private String country;
    @JsonProperty("entity")
    private String entity;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private int id;
}
