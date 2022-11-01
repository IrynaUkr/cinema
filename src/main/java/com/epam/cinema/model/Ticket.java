package com.epam.cinema.model;

public interface Ticket extends Entity {
   enum Category {STANDARD, PREMIUM, BAR}

    long getEventId();

    void setEventId(long eventId);

    long getUserId();

    void setUserId(long userId);

    Category getCategory();

   void setCategory(Category category);

    int getPlace();

    void setPlace(int place);
}
