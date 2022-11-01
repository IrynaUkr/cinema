package com.epam.cinema.model;

import java.time.LocalDate;


public interface Event extends Entity {

    String getTitle();

    void setTitle(String title);

    LocalDate getDate();

    void setDate(LocalDate date);
}
