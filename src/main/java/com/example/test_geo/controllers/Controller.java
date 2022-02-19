package com.example.test_geo.controllers;

import com.example.test_geo.service.LonLocLocator;
import com.example.test_geo.service.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;

@RestController
@Component
public class Controller {
    @Autowired
    Method NewMethod;
    @GetMapping("/param")
    public String GetLocation(@RequestParam(value = "location") String location) throws URISyntaxException {
        UriComponents UriComponents = UriComponentsBuilder.newInstance()
               .scheme("https")
               .host("api.geoapify.com")
                .path("/v1/geocode/search?text=")
                .pathSegment(location, "&format=json&apiKey=c1f45f7bcb654b4698fb833af17c4d67").encode()
                .build();
        String NewUri = UriComponents.toUriString();
        String NewUri1 = NewUri.replace("%3F","?");
        String ResultString = NewMethod.GetLocation(NewUri1);
        LonLocLocator NewLocator = new LonLocLocator();
        return "\"lon\""+ NewLocator.LonLocString(ResultString, "\"lon\"", "\"formatted\"" );

    }


}
