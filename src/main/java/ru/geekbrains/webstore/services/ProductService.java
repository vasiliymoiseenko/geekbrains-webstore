package ru.geekbrains.webstore.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.entities.Product;
import ru.geekbrains.webstore.repositories.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService implements ru.geekbrains.webstore.services.Service<Product> {

  private ProductRepository productRepository;

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }

  @Override
  public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
  }

  @Override
  public void deleteById(Long id) {
    productRepository.deleteById(id);
  }

  @Override
  public void save(Product product) {
    productRepository.save(product);
  }

  public List<Product> findAllByPrice(Double minPrice, Double maxPrice) {
    if (minPrice == null && maxPrice == null) {
      return productRepository.findAll();
    } else if (minPrice != null && maxPrice != null) {
      return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    } else if (minPrice == null) {
      return productRepository.findAllByPriceBefore(maxPrice);
    } else {
      return productRepository.findAllByPriceAfter(minPrice);
    }
  }
}
