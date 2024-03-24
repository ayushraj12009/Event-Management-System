package com.InternProject.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DistanceCalculatorService {

    public String calculateDistance(double latitude, double longitude) {
        String apiUrl = "https://gg-backend-assignment.azurewebsites.net/api/Distance?code=IAKvV2EvJa6Z6dEIUqqd7yGAu7IZ8gaH-a0QO6btjRc1AzFu8Y3IcQ=="
                + "&latitude1=40.7128"
                + "&longitude1=-74.0060"
                + "&latitude2=" + latitude
                + "&longitude2=" + longitude;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, String.class);
    }
}