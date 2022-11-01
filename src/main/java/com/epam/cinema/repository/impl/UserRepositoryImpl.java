package com.epam.cinema.repository.impl;

import com.epam.cinema.exception.UserNotFoundException;
import com.epam.cinema.model.User;
import com.epam.cinema.repository.Storage;
import com.epam.cinema.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRepositoryImpl implements UserRepository {
    private List<User> userList;

    private Storage storage;

    @Override
    public List<User> getAll() {
        return userList;
    }

    @Override
    public User getById(long id) {
        return userList
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User is not found"));
    }

    @Override
    public User create(User entityDto) {
        long id = entityDto.getId();
        userList.add(entityDto);
        return getById(id);
    }

    @Override
    public User updateById(long id, User entityDto) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
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
