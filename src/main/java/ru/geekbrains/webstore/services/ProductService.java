package ru.geekbrains.webstore.services;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.entities.Product;
import ru.geekbrains.webstore.repositories.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {

  private ProductRepository productRepository;

  public List<Product> findAll() {
    return productRepository.selectAll();
  }

  public void add(Product product) {
    productRepository.create(product);
  }
}
