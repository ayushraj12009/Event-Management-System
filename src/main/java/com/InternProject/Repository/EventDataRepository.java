package com.InternProject.Repository;

import com.InternProject.Entity.Event;
import com.InternProject.Entity.EventDataList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventDataRepository extends JpaRepository<EventDataList, Long> {

    List<EventDataList> findByDate(String date);


}
