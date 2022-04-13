package ru.geekbrains.webstore.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.webstore.models.Product;

@Component
@AllArgsConstructor
public class ProductRepository {

  private List<Product> productList;

  @PostConstruct
  public void init() {
    productList = new ArrayList<>(Arrays.asList(
        new Product(1L, "Phone", 100.00),
        new Product(2L, "TV", 1000.00),
        new Product(3L, "Camera", 500.00),
        new Product(4L, "X-Box", 400.00),
        new Product(5L, "PlayStation", 450.00)
    ));
  }

  public Product find(Long id) {
    return productList.stream().filter(i -> i.getId().equals(id)).findFirst().get();
  }

  public List<Product> findAll() {
    return Collections.unmodifiableList(productList);
  }

  public void add(Product product) {
    productList.add(product);
  }
}
