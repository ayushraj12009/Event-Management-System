package com.InternProject.Controller;

import com.InternProject.Entity.Event;
import com.InternProject.Entity.EventDataList;
import com.InternProject.Repository.EventDataRepository;
import com.InternProject.Service.DistanceCalculatorService;
import com.InternProject.Service.EventDataService;
import com.InternProject.Service.EventService;
import com.InternProject.Service.WeatherService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class EventController {

    @Autowired
    private DistanceCalculatorService distanceCalculatorService;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventDataRepository eventDataRepository;

    @Autowired
    private EventDataService eventDataService;

    @PostMapping("/add")
    public ResponseEntity<EventDataList> addEvents(@RequestBody EventDataList eventDataList){
        EventDataList createdEvents = eventDataService.saveEventDataList(eventDataList);
        return new ResponseEntity<>(createdEvents, HttpStatus.CREATED);
    }


    @PostMapping("/addEvents")
    public ResponseEntity<Event> addEvents(@RequestBody Event event){
        Event createdEvents = eventService.addEventToDB(event);
        return new ResponseEntity<>(createdEvents, HttpStatus.CREATED);
    }

    @GetMapping("/event")
    public EventDataList getEventData(@RequestParam double userSourceLatitude, @RequestParam double userSourceLongitude,
                                      @RequestParam String searchDate ) {

        // Calculate distance using user's source latitude and longitude
        String distanceApiResponse = distanceCalculatorService.calculateDistance(userSourceLatitude, userSourceLongitude);
        Double distance = extractDistanceFromApiResponse(distanceApiResponse);

        String cityNameByDate = weatherService.getCityNameByDate(searchDate);
        System.out.println(cityNameByDate);

        String EventName = weatherService.getEventName(searchDate);
        System.out.println(EventName);

        String weatherApiResponse = weatherService.getWeather( cityNameByDate , searchDate);
        String weather = extractWeatherFromApiResponse(weatherApiResponse);



        // Create and return Event object with combined data
        EventDataList list = new EventDataList();
        list.setEvent_Name(EventName);
        list.setCity_Name(cityNameByDate);
        list.setDate(searchDate);
        list.setWeather(weather);
        list.setDistance(distance);

//        Event event = new Event();
//        event.setDate(searchDate);
//        event.setWeather(weather);
//        event.setDistance(distance);

        //return event;
        return  list;
    }

    private Double extractDistanceFromApiResponse(String distanceApiResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(distanceApiResponse);
            String distanceString = rootNode.get("distance").asText();
            return Double.parseDouble(distanceString);

        } catch (IOException | NullPointerException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    private String extractWeatherFromApiResponse(String weatherApiResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(weatherApiResponse);
            String weather = rootNode.get("weather").asText();
            return weather;
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }
}
