package com.InternProject.Service;

import com.InternProject.Entity.EventDataList;
import com.InternProject.Repository.EventDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventDataService {

    @Autowired
    private EventDataRepository eventDataRepository;

    @Autowired
    private DistanceCalculatorService distanceCalculatorService;

    @Autowired
    private WeatherService weatherService;

    // logic for adding data to DB
    public EventDataList saveEventDataList(EventDataList eventDataList) {
        return eventDataRepository.save(eventDataList);
    }

}
