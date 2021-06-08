package com.example.iothome.model.response.openaq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResultsEntityCountries {
    @JsonProperty("sources")
    private int sources;
    @JsonProperty("cities")
    private int cities;
    @JsonProperty("count")
    private long count;
    @JsonProperty("parameters")
    private List<String> parameters;
    @JsonProperty("lastUpdated")
    private String lastupdated;
    @JsonProperty("firstUpdated")
    private String firstupdated;
    @JsonProperty("locations")
    private int locations;
    @JsonProperty("name")
    private String name;
    @JsonProperty("code")
    private String code;
}
