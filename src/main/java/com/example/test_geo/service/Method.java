package com.example.test_geo.service;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@Component("NewMethod")
public class Method {
    private RestTemplate RestTemplate = new RestTemplate();
    public String GetLocation(String NewLocation) throws URISyntaxException {
        UriComponents UriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.geoapify.com")
                .path("/v1/geocode/search?text=")
                .pathSegment(NewLocation, "&format=json&apiKey=c1f45f7bcb654b4698fb833af17c4d67").encode()
                .build();
        String NewUri = UriComponents.toUriString();
        String NewUri1 = NewUri.replace("%3F","?");
        String Response = RestTemplate.getForObject(new URI(NewUri1), String.class);
        return Response;
    }
}
