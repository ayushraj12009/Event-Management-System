package com.InternProject.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EventResponse {
    private String eventName;
    private String cityName;
    private String date;
    private String weather;
    private Double distance;

}
