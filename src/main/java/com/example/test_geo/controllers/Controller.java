package com.example.test_geo.controllers;

import com.example.test_geo.service.LonLocLocator;
import com.example.test_geo.service.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;



@RestController
@Component
public class Controller {
    @Autowired
    Method NewMethod;
    @GetMapping("/param")
    public String GetLocation(@RequestParam(value = "location") String location) throws URISyntaxException {
        String ResultString = NewMethod.GetLocation(location);
        LonLocLocator NewLocator = new LonLocLocator();
        return "\"lon\"" + NewLocator.LonLocString(ResultString, "\"lon\"", "\"formatted\"");
    }
}
