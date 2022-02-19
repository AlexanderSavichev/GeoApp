package com.example.test_geo.service;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;

@Component("NewMethod")
public class Method {
    private RestTemplate RestTemplate = new RestTemplate();
    public String GetLocation(String NewLocation) throws URISyntaxException {
        String Response = RestTemplate.getForObject(new URI(NewLocation), String.class);
        return Response;
    }
}
