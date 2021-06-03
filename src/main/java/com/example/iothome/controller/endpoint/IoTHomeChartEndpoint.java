package com.example.iothome.controller.endpoint;

import com.example.iothome.model.response.GlobalResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Api
public interface IoTHomeChartEndpoint {

    @ApiOperation(value = "Get current sensor data",
            notes = "notes example",
//            response = ResponseType.class,
            responseContainer = "GlobalResponseModel",
            tags = {"BackOfficeEndpoint"})
    @GetMapping(value = "/getDataSensorChart")
    String getDataSensorChart(ModelMap modelMap) throws JsonProcessingException;

    @ApiOperation(value = "Get current sensor data",
            notes = "notes example",
//            response = ResponseType.class,
            responseContainer = "GlobalResponseModel",
            tags = {"BackOfficeEndpoint"})
    @GetMapping(value = "/getReducedDataSensorChart")
    String getReducedDataSensorChart(ModelMap modelMap) throws JsonProcessingException;
}
