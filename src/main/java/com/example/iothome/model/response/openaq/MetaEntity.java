package com.example.iothome.model.response.openaq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MetaEntity {
    @JsonProperty("found")
    private int found;
    @JsonProperty("limit")
    private int limit;
    @JsonProperty("page")
    private int page;
    @JsonProperty("website")
    private String website;
    @JsonProperty("license")
    private String license;
    @JsonProperty("name")
    private String name;
}
