package com.example.test_geo.controllers;

import com.example.test_geo.models.result;
import com.example.test_geo.service.method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@Component
public class Controller {
    @Autowired
    method newMethod;
    @GetMapping("/param")
    public List<result> getLocation (@RequestParam(value = "location") String location) throws URISyntaxException {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("api.geoapify.com").path("/v1/geocode/search?text=").pathSegment(location, "&format=json&apiKey=a901afdd553443ea86f238b6c6326598").build();
        return newMethod.getLocation(uriComponents.toUriString());
    }
}
