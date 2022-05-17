package ru.geekbrains.webstore.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.webstore.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findAllByPriceIsLessThanEqual(Double maxPrice);

  List<Product> findAllByPriceGreaterThanEqual(Double minPrice);

  List<Product> findAllByPriceBetween(Double minPrice, Double maxPrice);
}
