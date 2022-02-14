package com.example.test_geo.controllers;
import com.example.test_geo.service.LonLocLocator;
import com.example.test_geo.service.method;
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
    method newMethod;
    @GetMapping("/param")
    //public List<result> getLocation (@RequestParam(value = "location") String location) throws URISyntaxException {
    public String getLocation (@RequestParam(value = "location") String location) throws URISyntaxException {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
               .scheme("https")
               .host("api.geoapify.com")
                //.path("/v1/geocode/search")
                //.queryParam("text",location)
               // .pathSegment("&format=json&apiKey=c1f45f7bcb654b4698fb833af17c4d67").encode()
                //.queryParam("/v1/geocode/search?text=",location,  "&format=json&apiKey=c1f45f7bcb654b4698fb833af17c4d67").encode()
                .path("/v1/geocode/search?text=")
                .pathSegment(location, "&format=json&apiKey=c1f45f7bcb654b4698fb833af17c4d67").encode()
                .build();
        String newUri = uriComponents.toUriString();
        String newUri1 = newUri.replace("%3F","?");
        System.out.println(newUri1);
        String resultString = newMethod.getLocation(newUri1);
        LonLocLocator newLocator = new LonLocLocator();
        return "\"lon\""+ newLocator.LonLocString(resultString, "\"lon\"", "\"formatted\"" );

    }
}
