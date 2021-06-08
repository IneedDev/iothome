package com.example.iothome.model.response.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SourcesBean {
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    @JsonProperty("id")
    private String id;
}
