package com.epam.cinema.model.impl;

import com.epam.cinema.model.Ticket;
import lombok.Data;

@Data
public class TicketModel implements Ticket {

    private long id;

    private long eventId;

    private long userId;

    private int place;

    private Category category;
}
