package com.example.iothome.service.impl;

import com.example.iothome.model.entity.ResponseTypeDTO;
import com.example.iothome.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    PersistenceServiceImpl persistenceService;

    @Override
    public List<List<Object>> getFullChartData() {
        return getLists(persistenceService.getAllSensorDataForChart());
    }

    @Override
    public List<List<Object>> getReducedChartData(int range) {
        return prepareReducedList(getLists(null), range);
    }

    private List<List<Object>> getLists(List<ResponseTypeDTO> list) {
        if (list == null) {
            list = persistenceService.getAllCurrentSensorDataForChart();
        }
        List<List<Object>> listOfList = new ArrayList<>();
        listOfList.add(Arrays.asList("Year", "Temp", "Humidity"));
        for (ResponseTypeDTO responseTypeDTO : list) {
            listOfList.add(Arrays.asList(responseTypeDTO.getDateCreated(), responseTypeDTO.getTemperature(), responseTypeDTO.getHumidity()));
        }
        return listOfList;
    }

    private List<List<Object>> prepareReducedList(List<List<Object>> dataList, int range) {
        List<List<Object>> subList = new ArrayList<>();
        subList.add(dataList.get(0));
        if (dataList.size() >= range) {
            IntStream.range(dataList.size() - range, dataList.size()).forEachOrdered(i -> subList.add(dataList.get(i)));
        }
        return subList;
    }
}
