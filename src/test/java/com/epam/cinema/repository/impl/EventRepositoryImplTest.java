package com.epam.cinema.repository.impl;

import com.epam.cinema.exception.EventNotFoundException;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.impl.EventModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.cinema.constants.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

class EventRepositoryImplTest {

    EventRepositoryImpl eventRepository;

    List<Event> testEventList;

    Event testEvent;

    @BeforeEach
    void setUp() {
        eventRepository = new EventRepositoryImpl();
        testEvent = new EventModel();
        testEvent.setDate(LocalDate.parse(EVENT_TEST_DATE));
        testEvent.setTitle(EVENT_TITLE_EXIST);
        testEvent.setId(EVENT_TEST_ID);

        testEventList = new ArrayList<>();
        testEventList.add(testEvent);
        eventRepository.setEvents(testEventList);
    }

    @Test
    void getAllShouldReturnValidListTest() {
        List<Event> actual = eventRepository.getAll();

        assertEquals(testEventList.size(), actual.size());
        assertTrue(testEventList.contains(testEvent));
    }

    @Test
    void shouldReturnEventWithValidTest() {
        Event actual = eventRepository.getById(EVENT_TEST_ID);

        assertEquals(EVENT_TITLE_EXIST, actual.getTitle());
        assertEquals(EVENT_TEST_ID, actual.getId());
        assertEquals(LocalDate.parse(EVENT_TEST_DATE), actual.getDate());
    }

    @Test
    void shouldThrowEventNotFoundExceptionIfEventIdDoesNotExistTest() {
        assertThrows(EventNotFoundException.class, () -> eventRepository.getById(2));
    }

    @Test
    void create() {
        Event newEvent = new EventModel();
        newEvent.setDate(LocalDate.parse(EVENT_TEST_DATE));
        newEvent.setTitle(EVENT_NEW_TITLE);
        newEvent.setId(EVENT_NEW_TEST_ID);

        eventRepository.create(newEvent);
    }

    @Test
    void shouldUpdateEventByIdTest() {
        Event expected = new EventModel();
        expected.setDate(LocalDate.parse(EVENT_TEST_DATE));
        expected.setTitle(EVENT_NEW_TITLE);

        Event actual = eventRepository.updateById(EVENT_TEST_ID, expected);

        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    void deleteById() {
        boolean isDeleted = eventRepository.deleteById(EVENT_TEST_ID);
        assertTrue(isDeleted);
        assertEquals( 0,testEventList.size());
    }

    @Test
    void shouldReturnEmptyListIfTitleNotExistTest() {
        List<Event> actual = eventRepository.getEventsByTitle(EVENT_TITLE_NOT_EXIST, 1, 1);
        assertEquals(actual.size(), 0);
    }

    @Test
    void shouldReturnValidListIfTitleExistTest() {
        List<Event> actual = eventRepository.getEventsByTitle(EVENT_TITLE_EXIST, 1, 1);
        assertEquals(1, actual.size());
        assertTrue(actual.contains(testEvent));
    }

    @Test
    void shouldReturnEmptyListIfDateNotExistTest() {
        List<Event> actual = eventRepository.getEventsForDay(LocalDate.parse(EVENT_TEST_NEW_DATE), 1, 1);
        assertEquals(0, actual.size());
    }

    @Test
    void shouldReturnValidListIfDateExistTest() {
        List<Event> actual = eventRepository.getEventsForDay(LocalDate.parse(EVENT_TEST_DATE), 1, 1);
        assertEquals(1, actual.size());
        assertTrue(actual.contains(testEvent));
    }
}
