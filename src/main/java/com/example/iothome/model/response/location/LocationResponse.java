package com.example.iothome.model.response.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LocationResponse {
    @JsonProperty("results")
    private List<ResultsBean> results;
    @JsonProperty("meta")
    private MetaBean meta;
}
