package com.example.test_geo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/geocodeAddress")
    public String geocodeAddress() {
        return "geocodeAddress";
    }
    @GetMapping("/geocodeFile")
    public String geocodeFile() {
        return "geocodeFile";
    }
}
