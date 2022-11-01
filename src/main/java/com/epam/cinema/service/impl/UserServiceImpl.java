package com.epam.cinema.service.impl;

import com.epam.cinema.model.User;
import com.epam.cinema.repository.impl.UserRepositoryImpl;
import com.epam.cinema.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserRepositoryImpl userRepositoryImpl;

    public UserServiceImpl(UserRepositoryImpl userRepositoryImpl) {
        this.userRepositoryImpl = userRepositoryImpl;
    }

    @Override
    public User getById(long id) {
        return userRepositoryImpl.getById(id);
    }

    @Override
    public User create(User user) {
        return userRepositoryImpl.create(user);
    }

    @Override
    public User updateById(long id, User entity) {
        return userRepositoryImpl.updateById(id, entity);
    }

    @Override
    public boolean deleteById(long id) {
        return userRepositoryImpl.deleteById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return null;
    }
}
