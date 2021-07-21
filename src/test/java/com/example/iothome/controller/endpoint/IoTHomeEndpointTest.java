package com.example.iothome.controller.endpoint;

import com.example.iothome.exception.IoTHomeException;
import com.example.iothome.model.entity.ResponseTypeDTO;
import com.example.iothome.model.response.ResponseType;
import com.example.iothome.model.response.SensorData;
import com.example.iothome.repository.SensorDataLocalRepository;
import com.example.iothome.repository.SensorDataPersistenceRepository;
import com.example.iothome.service.RedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Ignore
public class IoTHomeEndpointTest {

    private static final String sensorId = "000000001";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RedisService redisService;

    @MockBean
    SensorDataLocalRepository sensorDataLocalRepository;
    @Test
    public void shouldGetCurrentSensorData() throws Exception{
        //given
        ResponseTypeDTO responseType = prepareSimpleResponse();

        //when
        Mockito.when(redisService.getDataFromRedis(sensorId)).thenReturn(responseType);

        //then
        mockMvc.perform(get("/api/v1/iothome/getCurrentSensorData")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldGetCurrentSensorDataNullException() throws Exception{
        //given
        //when
        Mockito.when(redisService.getDataFromRedis(sensorId)).thenThrow(IoTHomeException.class);

        //then
        mockMvc.perform(get("/api/v1/iothome/getCurrentSensorData")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("EXCEPTION"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exceptionKey").value("IoTHomeException"))

                //TODO prepare mock for proper exception testing
//                .andExpect(MockMvcResultMatchers.jsonPath("$.exceptionMessage").value(null))
                .andDo(MockMvcResultHandlers.print());
    }

    private ResponseTypeDTO prepareSimpleResponse() {
        ResponseTypeDTO responseType = new ResponseTypeDTO();
        responseType.setDateCreated("2021-06-04 17:54:46");
        responseType.setId(1000000001);
        responseType.setHumidity(39.1);
        responseType.setTemperature(29.7);
        return responseType;
    }

}