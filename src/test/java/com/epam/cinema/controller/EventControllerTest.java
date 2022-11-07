package com.epam.cinema.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static com.epam.cinema.constants.TestData.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"/applicationContextMVC.xml"})
@WebAppConfiguration()
class EventControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void isServletContextAndBookingFacadeControllerNotNull() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("bookingFacadeController"));
    }


    @Test
    void getEventById() throws Exception {
        this.mockMvc
                .perform(get("/event/{id}", EVENT_ID_CHRISTMAS))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(EVENT_DATE_CHRISTMAS)))
                .andExpect(content().string(containsString(EVENT_TITLE_CHRISTMAS)));
    }

    @Test
    void getAllEventsByTitle() throws Exception {
        this.mockMvc
                .perform(get("/event/{title}/events", EVENT_TITLE_CHRISTMAS))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(EVENT_DATE_CHRISTMAS)))
                .andExpect(content().string(containsString(EVENT_TITLE_CHRISTMAS)));
    }

    @Test
    void getAllEventsByDay() throws Exception {
        this.mockMvc
                .perform(get("/event/{day}/eventsForDay", EVENT_DATE_CHRISTMAS))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(EVENT_TITLE_CHRISTMAS)));
    }

    @Test
    void showFormCreatingEvent() throws Exception {
        this.mockMvc
                .perform(get("/new"))
                .andExpect(view().name("createNewEvent"))
                .andExpect(model().attributeExists("event"))
                .andExpect(status().isOk());
    }

    @Test
    void createEvent() throws Exception {
        MockHttpServletRequestBuilder createMessage = post("/eventCreate")
                .param("event id", String.valueOf(NEW_EVENT_ID))
                .param("title", EVENT_NEW_TITLE);

        this.mockMvc
                .perform(createMessage)
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string(containsString("the event was created")))
                .andExpect(content().string(containsString(String.valueOf(NEW_EVENT_ID))))
                .andExpect(content().string(containsString(EVENT_NEW_TITLE)));
    }

    @Test
    void deleteEventById() throws Exception {
        this.mockMvc
                .perform(delete("/event/{id}", EXISTED_EVENT_ID))
                .andExpect(view().name("deleteEvent"))
                .andExpect(status().isOk());
    }
}