package com.InternProject.Service;

import com.InternProject.Entity.EventDataList;
import com.InternProject.Repository.EventDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DistanceCalculatorService {

    @Autowired
    private EventDataRepository eventDataRepository;

    // logic for handling external api to get the value of distance
    public String calculateDistance(double latitude, double longitude) {
        String apiUrl = "https://gg-backend-assignment.azurewebsites.net/api/Distance?code=IAKvV2EvJa6Z6dEIUqqd7yGAu7IZ8gaH-a0QO6btjRc1AzFu8Y3IcQ=="
                + "&latitude1=40.7128"
                + "&longitude1=-74.0060"
                + "&latitude2=" + latitude
                + "&longitude2=" + longitude;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, String.class);
    }



    // logic for getting city name for database
    public Double getLatitudeByDate(String date) {

        List<EventDataList> eventDataList = eventDataRepository.findByDate(date);

//        if (eventDataList.isEmpty()) {
//            return "Latitude not found for the given date.";
//        } else {
            Double cityName = eventDataList.get(0).getLatitude();
            return cityName;
       // }
    }

    // logic for getting event name for database
    public Double getLongitudeByDate(String date) {
        List<EventDataList> eventDataList = eventDataRepository.findByDate(date);
//        if (eventDataList.isEmpty()) {
//            return "Longitude is not found for the given date.";
//        } else {
        Double EventName = eventDataList.get(0).getLongitude();
            return EventName;
       // }
    }

}