package com.InternProject.Service;

import com.InternProject.Entity.Event;
import com.InternProject.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event addEventToDB(Event event){
        return eventRepository.save(event);
    }
}
