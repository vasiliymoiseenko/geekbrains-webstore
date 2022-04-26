package ru.geekbrains.webstore.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.webstore.entities.Product;

@Component
//@Primary
@AllArgsConstructor
public class ProductRepository implements Repository<Product>{

  private final EntityManager entityManager;

  @Override
  @Transactional
  public Product findById(Long id) {
    return entityManager.find(Product.class, id);
  }

  @Override
  @Transactional
  public List<Product> findAll() {
    return entityManager
            .createQuery("Select a from Product a", Product.class)
            .getResultList();
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    entityManager
            .createQuery("delete from Product a where a.id = :id")
            .setParameter("id", id)
            .executeUpdate();
  }

  @Override
  @Transactional
  public void save(Product product) {
    entityManager.persist(product);
  }

  @Override
  @Transactional
  public void update(Product product) {
    entityManager.merge(product);
  }
}
