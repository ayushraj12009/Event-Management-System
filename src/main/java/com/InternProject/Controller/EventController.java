package com.InternProject.Controller;

import com.InternProject.Entity.EventDataList;
import com.InternProject.Entity.PaginationResponse;
import com.InternProject.Repository.EventDataRepository;
import com.InternProject.Service.DistanceCalculatorService;
import com.InternProject.Service.EventDataService;
import com.InternProject.Service.WeatherService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private DistanceCalculatorService distanceCalculatorService;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private EventDataRepository eventDataRepository;

    @Autowired
    private EventDataService eventDataService;

    @PostMapping("/addEvent")
    public ResponseEntity<EventDataList> addEventToDB(@RequestBody EventDataList eventDataList){
        EventDataList createdEvents = eventDataService.saveEventDataList(eventDataList);
        return new ResponseEntity<>(createdEvents, HttpStatus.CREATED);
    }


    @GetMapping("/find")
    public PaginationResponse<EventDataList> getEventData(@RequestParam double latitude, @RequestParam double longitude,
                                                          @RequestParam String searchDate, @RequestParam(defaultValue = "1") int page,
                                                          @RequestParam(defaultValue = "10") int pageSize) {

        List<EventDataList> eventDataList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(searchDate, formatter);

        // Calculate total number of items and starting index for pagination
        int totalItems = 15; // Assuming always fetching data for next 15 days
        int totalEvents = totalItems; // You can replace this with the actual total number of events
        int totalPages = (int) Math.ceil((double) totalEvents / pageSize);
        int startIndex = (page - 1) * pageSize;

        for (int i = startIndex; i < totalItems && i < startIndex + pageSize; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            String currentDateString = currentDate.format(formatter);

            String cityNameByDate = weatherService.getCityNameByDate(currentDateString);

            String eventName = weatherService.getEventName(currentDateString);

            String weatherApiResponse = weatherService.getWeather(cityNameByDate, currentDateString);
            String weather = extractWeatherFromApiResponse(weatherApiResponse);

            // Calculate distance using user's source latitude and longitude
            String distanceApiResponse = distanceCalculatorService.calculateDistance(latitude, longitude);
            Double distance = extractDistanceFromApiResponse(distanceApiResponse);

            // Create and add EventDataList object to the list
            EventDataList eventData = new EventDataList();
            eventData.setEvent_Name(eventName);
            eventData.setCity_Name(cityNameByDate);
            eventData.setDate(currentDateString);
            eventData.setWeather(weather);
            eventData.setDistance(distance);

            eventDataList.add(eventData);
        }

        return new PaginationResponse<>(eventDataList, page, pageSize, totalEvents, totalPages);
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
