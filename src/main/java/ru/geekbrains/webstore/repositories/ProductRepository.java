package ru.geekbrains.webstore.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.geekbrains.webstore.entities.Product;

@Component
@Primary
@AllArgsConstructor
public class ProductRepository {

  private final EntityManager entityManager;
  private List<Product> productList;

  @PostConstruct
  public void init() {
//    productList = new ArrayList<>(Arrays.asList(
//        new Product(1L, "Phone", 100.00),
//        new Product(2L, "TV", 1000.00),
//        new Product(3L, "Camera", 500.00),
//        new Product(4L, "X-Box", 400.00),
//        new Product(5L, "PlayStation", 450.00)
//    ));
  }

  @Transactional
  public Long create(Product product) {
    entityManager.persist(product);
    return entityManager.find(Product.class, product.getId()).getId();
  }

  public Product selectById(int id) {
    return entityManager.find(Product.class, id);
  }

  public List<Product> selectAll() {
    return Collections.unmodifiableList(productList);
//    return entityManager
//        .createQuery("Select a from Product a", Product.class)
//        .getResultList();
  }

  @Transactional
  public int update(Product product) {
    entityManager.merge(product);

    return 0;
  }

  @Transactional
  public int delete(Product product) {
    entityManager.remove(product);

    return 0;
  }
}
