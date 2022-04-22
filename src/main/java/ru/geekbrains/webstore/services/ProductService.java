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
    return productRepository.findAll();
  }

  public Product findById(Long id) {
    return productRepository.findById(id);
  }

  public void deleteById(Long id) {
    productRepository.deleteById(id);
  }

  public void save(Product product) {
    productRepository.save(product);
  }

  public void update(Product product) {
    productRepository.update(product);
  }
}
