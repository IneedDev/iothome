package com.example.iothome.controller;

import com.example.iothome.model.response.location.CoordinatesBean;
import com.example.iothome.model.response.location.LocationResponse;
import com.example.iothome.model.response.location.ResultsBean;
import com.example.iothome.model.response.openaq.Countries;
import com.example.iothome.model.response.openaq.OpenAQResponse;
import com.example.iothome.repository.SensorDataLocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/openaq")
public class OpenAQRestController {

    @Autowired
    private SensorDataLocalRepository sensorDataLocalRepository;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/getlocation")
    public OpenAQResponse getDataForSpecificLocation() {
        String url = "https://docs.openaq.org/v2/locations/62232?limit=100&page=1&offset=0&sort=desc&radius=1000&order_by=lastUpdated&dumpRaw=false";
        OpenAQResponse result = restTemplate.getForObject(url, OpenAQResponse.class);
        return result;
    }

    @GetMapping(value = "/getlocations/{limit}/{page}/{country}")
    public List<ResultsBean> getAllLocations(@PathVariable(value = "limit") String limit,
                                             @PathVariable(value = "page") String page,
                                             @PathVariable(value = "country") String country, ModelMap modelMap) {

        Map<String, String> params = new HashMap<>();
        params.put("limit", limit);
        params.put("page", page);
        String url = "https://u50g7n0cbj.execute-api.us-east-1.amazonaws.com/v2/locations?limit={limit}&page={page}&offset=0&sort=desc&radius=100000&order_by=lastUpdated&dumpRaw=false";
        LocationResponse result = restTemplate.getForObject(url, LocationResponse.class, params);
        System.out.println("ELEMENT SIZE  ++++++++++++" + result.getResults().size());

        List<ResultsBean> reduced = new ArrayList<>();

        Set<String> countries = new HashSet<>();

        for (ResultsBean resultResult : result.getResults()) {
            if (Optional.ofNullable(resultResult.getCountry()).isPresent()) {
                countries.add(resultResult.getCountry());
                if (resultResult.getCountry().equals(country)) {
                    System.out.println("ELEMENT ADDED ++++++++++++");
                    reduced.add(resultResult);
                }
            }
        }

        for (String s : countries) {
            System.out.println(s);
        }

        List<CoordinatesBean> coordinatesBeans = new ArrayList<>();

        for (ResultsBean s : result.getResults()) {
            coordinatesBeans.add(s.getCoordinates());
        }

        sensorDataLocalRepository.saveAllCoordinates(coordinatesBeans);
        System.out.println("COORDINATES");
        System.out.println(sensorDataLocalRepository.getAllCoordinates());

        modelMap.addAttribute("coordinates", coordinatesBeans);

        return reduced;
//        return "openaq_chart";
    }

    @GetMapping("/countries")
    public Countries getCountries() {
        String url = "https://u50g7n0cbj.execute-api.us-east-1.amazonaws.com/v2/countries?limit=200&page=1&offset=0&sort=asc&order_by=country";
        Countries result = restTemplate.getForObject(url, Countries.class);

        result.getResults();

        result.getResults().removeIf(resultResult -> !resultResult.getName().equals("Poland"));

        return result;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
