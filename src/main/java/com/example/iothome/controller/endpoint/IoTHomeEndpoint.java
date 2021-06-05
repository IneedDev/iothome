package com.example.iothome.controller.endpoint;

import com.example.iothome.model.response.GlobalResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Api
public interface IoTHomeEndpoint {

    @ApiOperation(value = "Get current sensor data",
            notes = "notes example",
//            response = ResponseType.class,
            responseContainer = "GlobalResponseModel",
            tags = {"BackOfficeEndpoint"})
    @GetMapping(value = "/getCurrentSensorData",
            produces = "application/json")
    ResponseEntity<GlobalResponseModel> getCurrentSensorData() throws JsonProcessingException, Exception;
}
