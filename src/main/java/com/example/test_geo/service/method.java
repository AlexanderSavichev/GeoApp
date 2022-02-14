package com.example.test_geo.service;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;

@Component("newMethod")
public class method {
    private RestTemplate restTemplate = new RestTemplate();
    //public List<result> getLocation (String newLocation) throws URISyntaxException {
    public String getLocation (String newLocation) throws URISyntaxException {
        //GeoResult response = restTemplate.getForObject(new URI(newLocation), GeoResult.class);
        //JSONObject response = new JSONObject();
        String response = restTemplate.getForObject(new URI(newLocation), String.class);
        //String newResponse = response.toString();
        return response;
        //System.out.println(response);
       // return response.results;

    }
}
