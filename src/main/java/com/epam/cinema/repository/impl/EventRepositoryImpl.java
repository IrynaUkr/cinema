package com.epam.cinema.repository.impl;

import com.epam.cinema.exception.EventNotFoundException;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.impl.EventModel;
import com.epam.cinema.repository.EventRepository;
import com.epam.cinema.repository.Storage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Setter
@Getter
public class EventRepositoryImpl implements EventRepository {

    private Storage storage;

    private List<Event> events;

    @Override
    public List<Event> getAll() {
        return events;
    }

    @Override
    public Event getById(long id) {
        log.info("getting ById {} in the event repository", id);
        return events
                .stream()
                .filter(event -> event.getId() == id)
                .findAny().orElseThrow(() -> new EventNotFoundException("event not found"));
    }

    @Override
    public Event create(Event entityDto) {
        log.info("creating event in the event repository");
        if (entityDto == null) {
            throw new EventNotFoundException("event is null");
        }
        long id = entityDto.getId();
        events.add(entityDto);

        return getById(id);
    }

    @Override
    public Event updateById(long id, Event entityDto) {
        log.info("updating ById {} in the event repository", id);
        Event event = new EventModel();
        for (Event value : events) {
            if (value.getId() == id) {
                value.setTitle(entityDto.getTitle());
                value.setDate(entityDto.getDate());
                event = value;
            }
        }
        return event;
    }

    @Override
    public boolean deleteById(long id) {
        log.info("delete ById {} in the event repository", id);
        boolean isRemoved = false;
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == id) {
                events.remove(i);
                isRemoved = true;
            }
        }
        return isRemoved;
    }


    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        log.info("getEventsByTitle By title {} in the event repository", title);
        return events
                .stream()
                .filter(event -> event.getTitle().equals(title))
                .collect(toList());
    }

    @Override
    public List<Event> getEventsForDay(LocalDate day, int pageSize, int pageNum) {
        log.info("getEventsForDay ByDay {} in the event repository", day);
        return events
                .stream()
                .filter(event -> event.getDate().equals(day))
                .collect(toList());
    }
}
