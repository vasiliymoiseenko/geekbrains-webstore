package ru.geekbrains.webstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.webstore.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findAllByPriceBefore(Double maxPrice);

  List<Product> findAllByPriceAfter(Double minPrice);

  List<Product> findAllByPriceBetween(Double minPrice, Double maxPrice);
}
