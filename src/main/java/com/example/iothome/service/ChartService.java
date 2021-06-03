package com.example.iothome.service;

import java.util.List;

public interface ChartService {

    List<List<Object>> getFullChartData();
    List<List<Object>> getReducedChartData(int range);
}
