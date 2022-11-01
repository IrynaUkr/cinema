package com.epam.cinema.service;

import com.epam.cinema.model.User;

import java.util.List;

public interface UserService extends CrudService<User>{
    User getUserByEmail(String email);

    List<User> getUsersByName(String name, int pageSize, int pageNum);
}
