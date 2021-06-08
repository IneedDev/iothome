package com.example.iothome.controller;

import com.example.iothome.controller.endpoint.IoTHomeChartEndpoint;
import com.example.iothome.repository.SensorDataLocalRepository;
import com.example.iothome.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/iothome")
public class ChartController implements IoTHomeChartEndpoint {

	@Autowired
	private ChartService chartService;

	@Autowired
	private SensorDataLocalRepository sensorDataLocalRepository;

	@Override
	public String getDataSensorChart(ModelMap modelMap) {
		List<List<Object>> dataList = chartService.getFullChartData();


		List<Integer> dayList = new ArrayList<>();
		dayList.add(4);
		dayList.add(5);
		dayList.add(6);

		modelMap.addAttribute("daysTest", dayList);
		modelMap.addAttribute("chartData", dataList);
		return "google_chart";
	}

	@Override
	public String getReducedDataSensorChart(ModelMap modelMap) {
		List<List<Object>> dataList = chartService.getReducedChartData(5);
		modelMap.addAttribute("chartData", dataList);
		return "google_chart";
	}

	@GetMapping(value="/getDataCurrentSensorChart", produces = MediaType.TEXT_PLAIN_VALUE)
	public String getDataCurrentSensorChart() {
		System.out.println(sensorDataLocalRepository.getAllSensorData().size());
		return sensorDataLocalRepository.getAllSensorData().toString();
	}


}
