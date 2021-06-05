package com.example.iothome.exception;

import com.example.iothome.model.response.GlobalResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMappingController {

    @ExceptionHandler(IoTHomeException.class)
    public ResponseEntity<GlobalResponseModel> handleException(IoTHomeException exception) {

        GlobalResponseModel globalResponseModel = GlobalResponseModel
                .builder()
                .httpStatusCode(404)
                .status(GlobalResponseModel.Status.EXCEPTION)
                .exceptionMessage(exception.getMessage())
                .exceptionKey(exception.getClass().getSimpleName())
                .build();

        return ResponseEntity
                .status(globalResponseModel.getHttpStatusCode())
                .body(globalResponseModel);

    }
}
