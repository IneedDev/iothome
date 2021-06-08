package com.example.iothome.mapper;

import com.example.iothome.model.entity.ResponseTypeDTO;
import com.example.iothome.model.response.ResponseType;


public class ResponseMapper {

    public static ResponseTypeDTO responseMapperToDTO(String sensorId, ResponseType responseType) {
        ResponseTypeDTO dto = new ResponseTypeDTO();
        dto.setSensor_id(sensorId);
        dto.setTemperature(Double.parseDouble(responseType.getData().getTemperature()));
        dto.setHumidity(Double.parseDouble(responseType.getData().getHumidity()));
        dto.setDateCreated(responseType.getTime());
        return dto;
    }

}
