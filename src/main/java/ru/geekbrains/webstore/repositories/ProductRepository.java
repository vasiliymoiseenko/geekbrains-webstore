package ru.geekbrains.webstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.webstore.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
