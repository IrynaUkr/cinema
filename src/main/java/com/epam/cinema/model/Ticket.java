package com.epam.cinema.model;

public interface Ticket extends Entity {

    long getEventId();

    void setEventId(long eventId);

    long getUserId();

    void setUserId(long userId);

    int getPlace();

    void setPlace(int place);
}
