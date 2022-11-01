package cinema.repository.impl;

import com.epam.cinema.exception.EventNotFoundException;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.impl.EventModel;
import com.epam.cinema.repository.impl.EventRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventRepositoryImplTest {

    public static final String EVENT_TITLE_NOT_EXIST = "123";
    EventRepositoryImpl eventRepository;
    public static final String EVENT_TEST_NEW_DATE = "2022-03-03";
    public static final String EVENT_TITLE_EXIST = "event test title";
    public static final long EVENT_TEST_ID = 1L;
    public static final long EVENT_NEW_TEST_ID = 2L;
    public static final String EVENT_TEST_DATE = "2022-01-01";
    public static final String EVENT_NEW_TITLE = "New title";
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

        assertEquals(actual.getTitle(), EVENT_TITLE_EXIST);
        assertEquals(actual.getId(), EVENT_TEST_ID);
        assertEquals(actual.getDate(), LocalDate.parse(EVENT_TEST_DATE));
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
    void shouldThrowIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> eventRepository.create(null));
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
        assertEquals(testEventList.size(), 0);
    }

    @Test
    void shouldReturnEmptyListIfTitleNotExistTest() {
        List<Event> actual = eventRepository.getEventsByTitle(EVENT_TITLE_NOT_EXIST, 1, 1);
        assertEquals(actual.size(), 0);
    }
    @Test
    void shouldReturnValidListIfTitleExistTest() {
        List<Event> actual = eventRepository.getEventsByTitle(EVENT_TITLE_EXIST, 1, 1);
        assertEquals(actual.size(), 1);
        assertTrue(actual.contains(testEvent));
    }

    @Test
    void shouldReturnEmptyListIfDateNotExistTest() {
        List<Event> actual = eventRepository.getEventsForDay(LocalDate.parse(EVENT_TEST_NEW_DATE),1,1);
        assertEquals(actual.size(), 0);
    }
    @Test
    void shouldReturnValidListIfDateExistTest() {
        List<Event> actual = eventRepository.getEventsForDay(LocalDate.parse(EVENT_TEST_DATE),1,1);
        assertEquals(actual.size(), 1);
        assertTrue(actual.contains(testEvent));
    }
}
