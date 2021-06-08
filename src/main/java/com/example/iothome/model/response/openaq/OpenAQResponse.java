package com.example.iothome.model.response.openaq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OpenAQResponse {

    @JsonProperty("results")
    private List<ResultsEntity2> results;
    @JsonProperty("meta")
    private MetaEntity meta;
}
