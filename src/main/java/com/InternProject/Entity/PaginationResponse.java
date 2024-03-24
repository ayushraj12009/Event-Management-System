package com.InternProject.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PaginationResponse<T> {
    private List<T> events;
    private int page;
    private int pageSize;
    private int totalEvents;
    private int totalPages;

}
