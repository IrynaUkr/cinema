package com.epam.cinema.service;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.model.User;
import com.epam.cinema.model.impl.TicketModel;

import java.util.List;

public interface TicketService extends CrudService<TicketModel> {
    Ticket bookTicket(long userId, long eventId, int place);

    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

    boolean cancelTicket(long ticketId);
}
