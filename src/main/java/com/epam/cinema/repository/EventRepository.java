package com.epam.cinema.repository;

import com.epam.cinema.model.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends Repository<Event> {
     List<Event> getEventsByTitle(String title, int pageSize, int pageNum);
     List<Event> getEventsForDay(LocalDate day, int pageSize, int pageNum);
}
