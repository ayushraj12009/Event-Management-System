package com.InternProject.Service;

import com.InternProject.Entity.EventDataList;
import com.InternProject.Repository.EventDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventDataService {

    @Autowired
    private EventDataRepository eventDataRepository;

    public EventDataList saveEventDataList(EventDataList eventDataList) {
        return eventDataRepository.save(eventDataList);
    }

}
