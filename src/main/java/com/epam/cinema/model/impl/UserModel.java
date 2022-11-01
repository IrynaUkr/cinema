package com.epam.cinema.model.impl;

import com.epam.cinema.model.User;
import lombok.Data;

@Data
public class UserModel implements User {

    private long id;

    private String email;

    private String name;
}
