package com.example.iothome.model.response.openaq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Countries {

    @JsonProperty("results")
    private List<ResultsEntityCountries> results;
    @JsonProperty("meta")
    private MetaEntity meta;
}

