package ru.geekbrains.webstore.services;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.models.Product;
import ru.geekbrains.webstore.repositories.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {

  private ProductRepository productRepository;

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public void add(Product product) {
    productRepository.add(product);
  }
}
