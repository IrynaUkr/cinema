package com.epam.cinema.service.impl;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.model.User;
import com.epam.cinema.model.impl.TicketModel;
import com.epam.cinema.repository.TicketRepository;
import com.epam.cinema.service.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    @Override
    public TicketModel getById(long id) {
        return null;
    }

    @Override
    public TicketModel create(TicketModel entityDto) {
        return null;
    }

    @Override
    public TicketModel updateById(long id, TicketModel entityDto) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place) {
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return false;
    }
}
