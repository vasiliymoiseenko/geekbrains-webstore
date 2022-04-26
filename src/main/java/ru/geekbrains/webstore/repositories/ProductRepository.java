package ru.geekbrains.webstore.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.geekbrains.webstore.entities.Product;

@Component
@Primary
@AllArgsConstructor
public class ProductRepository implements Repository<Product>{

  private final EntityManager entityManager;

  @Override
  public Product findById(Long id) {
    return entityManager.find(Product.class, id);
  }

  @Override
  public List<Product> findAll() {
    return entityManager
            .createQuery("Select a from Product a", Product.class)
            .getResultList();
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    Product product = findById(id);
    entityManager.remove(product);
  }

  @Override
  @Transactional
  public void save(Product product) {
    entityManager.persist(product);
  }

  @Override
  @Transactional
  public void update(Product product) {
    Product productUpdate = findById(product.getId());
    productUpdate.setPrice(product.getPrice());
    productUpdate.setTitle(product.getTitle());
    entityManager.merge(productUpdate);
  }
}
