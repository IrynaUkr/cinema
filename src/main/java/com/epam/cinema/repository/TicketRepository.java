package com.epam.cinema.repository;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.model.User;

import java.util.List;

public interface TicketRepository extends Repository<Ticket> {

    Ticket bookTicket(long userId, long eventId, int place);

    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

    boolean cancelTicket(long ticketId);
}
