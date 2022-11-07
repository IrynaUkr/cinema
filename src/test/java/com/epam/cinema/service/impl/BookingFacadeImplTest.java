package com.epam.cinema.service.impl;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.impl.EventModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static com.epam.cinema.constants.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"/applicationContextMVC.xml"})
class BookingFacadeImplTest {

    @Autowired
    BookingFacadeImpl bookingFacade;
    @Autowired
    ApplicationContext context;
    Event expected;

    @BeforeEach
    void setUp(){
        expected = new EventModel();
        expected.setId(EVENT_ID_CHRISTMAS);
        expected.setTitle("Christmas");
        expected.setDate(LocalDate.parse("2022-10-31"));
    }

    @Test
    void applicationContextShouldNotBeNullTest(){
        assertNotNull(context);
    }
    @Test
    void shouldCreateFacadeBeanWithPNameSpaceTest(){
        BookingFacadeImpl bean = context.getBean(BookingFacadeImpl.class);
        String actual = bean.getMessageFacade();
        String expected = "How are you?";
        assertNotNull(bean);
        assertEquals(expected, actual);
    }


    @Test
    void shouldReturnValidEventByIdIT(){
        Event actualEvent = bookingFacade.getEventById(EVENT_ID_CHRISTMAS);

        assertEquals(expected.getId(), actualEvent.getId());
        assertEquals(expected.getTitle(),actualEvent.getTitle());
    }

    @Test
    void shouldCreateEventIT() {
        Event expected = new EventModel();
        expected.setId(NEW_EVENT_ID);
        expected.setDate(LocalDate.parse(NEW_EVENT_DATE));
        expected.setTitle(NEW_EVENT_TITLE);

        Event actual = bookingFacade.createEvent(expected);

        assertEquals(expected.getId(),actual.getId());
        assertEquals(expected.getTitle(),actual.getTitle());
        assertEquals(expected.getDate(),actual.getDate());
    }

    @Test
    void shouldDeleteEventIT() {
        boolean isDeleted = bookingFacade.deleteEvent(EXISTED_EVENT_ID);
        assertTrue(isDeleted);
    }
}
