package ru.geekbrains.webstore.services;

import java.util.Optional;
import org.springframework.data.domain.Page;

public interface Service<T> {

  Page<T> findAll(int pageIndex, int pageSize);

  Optional<T> findById(Long id);

  void deleteById(Long id);

  void save(T object);
}
