package com.example.iothome.controller;

import com.example.iothome.model.response.location.CoordinatesBean;
import com.example.iothome.model.response.location.LocationResponse;
import com.example.iothome.model.response.location.ResultsBean;
import com.example.iothome.repository.SensorDataLocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static java.util.Arrays.asList;

@Controller
@RequestMapping("/openaq")
public class OpenAQController {

    @Autowired
    private SensorDataLocalRepository sensorDataLocalRepository;

    @Qualifier("getRestTemplate2")
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/getlocations2")
    public String getAllLocations(Model modelMap) {
        int page = 1;
        LocationResponse result;
        List<LocationResponse> locationResponseList = new ArrayList<>();

        do {
            System.out.println("DO");
            String url = "https://u50g7n0cbj.execute-api.us-east-1.amazonaws.com/v2/locations?limit=1000&page={page}&offset=0&sort=desc&radius=100000&order_by=lastUpdated&dumpRaw=false";
            result = restTemplate.getForObject(url, LocationResponse.class, String.valueOf(page));
            locationResponseList.add(result);
            page++;
            System.out.println("while loop" + page);
        }
        while (result.getResults().size() != 0);


        System.out.println("ELEMENT SIZE  ++++++++++++" + locationResponseList.size());

        List<ResultsBean> reduced = new ArrayList<>();

        Set<String> countries = new HashSet<>();

        for (LocationResponse locationResponse : locationResponseList) {
            for (ResultsBean resultResult : locationResponse.getResults()) {
                if (Optional.ofNullable(resultResult.getCountry()).isPresent()) {
                    countries.add(resultResult.getCountry());
                    if (resultResult.getCountry().equals("PL")) {
                        System.out.println("ELEMENT ADDED ++++++++++++");
                        reduced.add(resultResult);
                    }
                }
            }
        }


        for (String s : countries) {
            System.out.println(s);
        }

        List<List<Double>> coordinatesBeans = new ArrayList<>();

        for (ResultsBean s : result.getResults()) {
            coordinatesBeans.add(asList(s.getCoordinates().getLongitude(),s.getCoordinates().getLatitude()));
        }

        List<String> countryList = new ArrayList<>();

        for (ResultsBean s : result.getResults()) {
            countryList.add(s.getCountry());
        }

//        sensorDataLocalRepository.saveAllCoordinates(coordinatesBeans);
//        System.out.println("COORDINATES");
//        System.out.println(sensorDataLocalRepository.getAllCoordinates());

        modelMap.addAttribute("coordinates", coordinatesBeans);
        modelMap.addAttribute("countries", countryList);

        return "openaq_chart";
    }

    @Bean
    public RestTemplate getRestTemplate2() {
        return new RestTemplate();
    }
}
