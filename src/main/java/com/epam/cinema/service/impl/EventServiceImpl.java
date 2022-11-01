package com.epam.cinema.service.impl;

import com.epam.cinema.model.Event;
import com.epam.cinema.repository.EventRepository;
import com.epam.cinema.service.EventService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
@Slf4j
public class EventServiceImpl implements EventService {

    EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event getById(long id) {
        log.info("eventService method getById {} was called",id);
        return eventRepository.getById(id);
    }

    @Override
    public Event create(Event event) {
        log.info("eventService method createEvent {} was called",event);
        return eventRepository.create(event);
    }

    @Override
    public Event updateById(long id, Event event) {
        log.info("eventService method updateById {} was called",id);
        return eventRepository.updateById(id, event);
    }

    @Override
    public boolean deleteById(long id) {
        log.info("eventService method deleteById {} was called",id);
        return eventRepository.deleteById(id);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        log.info("eventService method geEventByTitle  {} was called",title);
        return eventRepository.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(LocalDate day, int pageSize, int pageNum) {
        log.info("eventService method getForDay {} was called",day);
        return eventRepository.getEventsForDay(day,pageSize, pageNum);
    }
}
