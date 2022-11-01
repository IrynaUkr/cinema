package com.epam.cinema.repository.impl;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.model.User;
import com.epam.cinema.repository.Storage;
import com.epam.cinema.repository.TicketRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class TicketRepositoryImpl implements TicketRepository {
    List<Ticket> tickets;

    private Storage storage;

    @Override
    public List<Ticket> getAll() {
        return null;
    }

    @Override
    public Ticket getById(long id) {
        return null;
    }

    @Override
    public Ticket create(Ticket entityDto) {
        return null;
    }

    @Override
    public Ticket updateById(long id, Ticket entityDto) {
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
