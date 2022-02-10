package com.example.test_geo.service;

import com.example.test_geo.models.GeoResult;
import com.example.test_geo.models.result;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component("newMethod")
public class method {
    private RestTemplate restTemplate = new RestTemplate();
    public List<result> getLocation (String newLocation) throws URISyntaxException {
        GeoResult response = restTemplate.getForObject(new URI(newLocation), GeoResult.class);
        return response.results;
    }
}
