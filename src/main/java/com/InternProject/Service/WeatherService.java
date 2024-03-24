package com.InternProject.Service;

import com.InternProject.Entity.Event;
import com.InternProject.Entity.EventDataList;
import com.InternProject.Repository.EventDataRepository;
import com.InternProject.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private EventDataRepository eventDataRepository;

    @Autowired
    private EventRepository eventRepository;


        public String getWeather(String cityName, String date) {

        String apiUrl = "https://gg-backend-assignment.azurewebsites.net/api/Weather?code=KfQnTWHJbg1giyB_Q9Ih3Xu3L9QOBDTuU5zwqVikZepCAzFut3rqsg=="
                + "&city=" + cityName
                + "&date=" + date;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, String.class);

    }


    public String getCityNameByDate(String date) {
        List<EventDataList> eventDataList = eventDataRepository.findByDate(date);

        if (eventDataList.isEmpty()) {
            return "City not found for the given date.";
        } else {
            String cityName = eventDataList.get(0).getCity_Name();
            return cityName;
        }
    }



    public String getEventName(String date) {
        List<EventDataList> eventDataList = eventDataRepository.findByDate(date);

        if (eventDataList.isEmpty()) {
            return "Event Name is not found for the given date.";
        } else {
            String EventName = eventDataList.get(0).getEvent_Name();
            return EventName;
        }
    }














}

