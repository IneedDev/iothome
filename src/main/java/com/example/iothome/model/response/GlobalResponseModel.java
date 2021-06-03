package com.example.iothome.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalResponseModel {

    private Object payload;
    private String exceptionMessage;
    private String exceptionKey;
    private Status status;
    private String iid;

    @JsonIgnore
    private int httpStatusCode;

    public enum Status {
        OK, OK_WITH_RESPONSE, EXCEPTION
    }
}
