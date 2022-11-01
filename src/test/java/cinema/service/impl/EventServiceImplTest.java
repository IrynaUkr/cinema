package cinema.service.impl;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.impl.EventModel;
import com.epam.cinema.repository.impl.EventRepositoryImpl;
import com.epam.cinema.service.impl.EventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    public static final String EVENT_TEST_TITLE = "event test title";
    public static final long EVENT_TEST_ID= 1L;
    public static final String EVENT_TEST_DATE = "2022-01-01";
    public static final String NEW_TITLE = "New title";
    Event testEvent;
    List<Event> testEventList;

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventRepositoryImpl eventRepository;

    @BeforeEach
    void setUp(){
        testEvent= new EventModel();
        testEvent.setDate(LocalDate.parse(EVENT_TEST_DATE));
        testEvent.setTitle(EVENT_TEST_TITLE);
        testEvent.setId(EVENT_TEST_ID);

        testEventList = new ArrayList<>();
        testEventList.add(testEvent);
    }


    @Test
    void shouldReturnValidEventWhenEventExistTest() {
        when(eventRepository.getById(EVENT_TEST_ID)).thenReturn(testEvent);

        Event event = eventService.getById(EVENT_TEST_ID);

        assertEquals(testEvent.getTitle(), event.getTitle());
        assertEquals(testEvent.getId(), event.getId());
        assertEquals(testEvent.getDate(), event.getDate());
    }


    @Test
    void shouldCreateValidEventByEventDtoTest() {
        Event eventDto= new EventModel();
        eventDto.setDate(LocalDate.parse(EVENT_TEST_DATE));
        eventDto.setTitle(EVENT_TEST_TITLE);
        eventDto.setId(EVENT_TEST_ID);

        when(eventRepository.create(eventDto)).thenReturn(testEvent);

        eventService.create(eventDto);

        assertEquals(testEvent.getTitle(), eventDto.getTitle());
        assertEquals(testEvent.getId(), eventDto.getId());
        assertEquals(testEvent.getDate(), eventDto.getDate());
    }

    @Test
    void shouldBeUpdatedByIdTest() {
        Event eventDto= new EventModel();
        eventDto.setDate(LocalDate.parse(EVENT_TEST_DATE));
        eventDto.setTitle(NEW_TITLE);
        eventDto.setId(EVENT_TEST_ID);
        testEvent.setTitle(NEW_TITLE);

        when(eventRepository.updateById(EVENT_TEST_ID, eventDto)).thenReturn(testEvent);

        eventService.updateById(EVENT_TEST_ID, eventDto);

        assertEquals(testEvent.getTitle(), eventDto.getTitle());
        assertEquals(testEvent.getId(), eventDto.getId());
        assertEquals(testEvent.getDate(), eventDto.getDate());
    }

    @Test
    void deleteById() {
        eventService.deleteById(EVENT_TEST_ID);

        verify(eventRepository).deleteById(EVENT_TEST_ID);
    }

    @Test
    void getEventsByTitle() {
        when(eventRepository.getEventsByTitle(EVENT_TEST_TITLE, 1,1)).thenReturn(testEventList);

        eventService.getEventsByTitle(EVENT_TEST_TITLE,1,1);
        for(Event ev:testEventList){
            assertEquals(ev.getTitle(), EVENT_TEST_TITLE);
        }
    }

    @Test
    void getEventsForDay() {
        when(eventRepository.getEventsForDay(LocalDate.parse(EVENT_TEST_DATE), 1,1)).thenReturn(testEventList);

        eventService.getEventsForDay(LocalDate.parse(EVENT_TEST_DATE), 1,1);
        for(Event ev:testEventList){
            assertEquals(ev.getDate(), LocalDate.parse(EVENT_TEST_DATE));
        }
    }
}
