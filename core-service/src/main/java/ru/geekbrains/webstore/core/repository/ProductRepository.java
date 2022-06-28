package ru.geekbrains.webstore.core.repository;

import ru.geekbrains.webstore.core.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

  List<Product> findAllByPriceIsLessThanEqual(Long maxPrice);

  List<Product> findAllByPriceGreaterThanEqual(Long minPrice);

  List<Product> findAllByPriceBetween(Long minPrice, Long maxPrice);

  Optional<Product> findProductByTitle(String title);
}
