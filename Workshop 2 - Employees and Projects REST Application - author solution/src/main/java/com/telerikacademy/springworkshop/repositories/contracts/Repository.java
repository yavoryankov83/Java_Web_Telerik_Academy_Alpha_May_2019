package com.telerikacademy.springworkshop.repositories.contracts;

import com.telerikacademy.springworkshop.models.Employee;

import java.util.List;

public interface Repository<T> {

    void create(T entity);

    List<T> getAll();

    T getById(int id);

    void update(int id, T entity);

    void delete(int id);
}
