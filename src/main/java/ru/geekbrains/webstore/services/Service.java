package ru.geekbrains.webstore.services;

import java.util.List;
import java.util.Optional;

public interface Service<T> {

  List<T> findAll();

  Optional<T> findById(Long id);

  void deleteById(Long id);

  void save(T object);
}
