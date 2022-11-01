package com.epam.cinema.repository;

import java.util.List;

public interface Repository <T>{
    List<T> getAll();

    T getById(long id);

    T create(T entity);

    T updateById(long id, T entityDto);

    boolean deleteById(long id);
}
