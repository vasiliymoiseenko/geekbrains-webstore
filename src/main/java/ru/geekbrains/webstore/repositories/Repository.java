package ru.geekbrains.webstore.repositories;

import ru.geekbrains.webstore.entities.Product;

import java.util.List;

public interface Repository<T> {

    T findById(Long id);

    List<T> findAll();

    void deleteById(Long id);

    void save(T object);

    void update(T object);
}
