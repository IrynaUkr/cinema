package com.epam.cinema.repository;

import com.epam.cinema.model.User;

import java.util.List;

public interface UserRepository extends Repository<User> {
    User getUserByEmail(String email);

    List<User> getUsersByName(String name, int pageSize, int pageNum);
}
