package ru.geekbrains.webstore.services;

import ru.geekbrains.webstore.entities.Product;

import java.util.List;

public interface Service<T> {

    List<T> findAll();

    T findById(Long id);

    void deleteById(Long id);

    void save(T object);

    void update(T object);
}
