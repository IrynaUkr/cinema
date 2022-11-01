package com.epam.cinema.model;

public interface User extends Entity{

    String getName();

    void setName(String name);

    /**
     * User email. UNIQUE.
     */
    String getEmail();

    void setEmail(String email);
}
