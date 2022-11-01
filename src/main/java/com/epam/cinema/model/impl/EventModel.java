package com.epam.cinema.model.impl;

import com.epam.cinema.model.Event;
import lombok.Data;

import java.time.LocalDate;
@Data
public class EventModel implements Event {

    private long id;

    private String title;

    private LocalDate date;
}
