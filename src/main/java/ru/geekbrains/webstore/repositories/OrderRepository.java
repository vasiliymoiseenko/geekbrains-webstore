package ru.geekbrains.webstore.repositories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.webstore.entities.Customer;
import ru.geekbrains.webstore.entities.Order;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Component
@AllArgsConstructor
public class OrderRepository implements Repository<Order>{

    private final EntityManager entityManager;

    @Override
    @Transactional
    public Order findById(Long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    @Transactional
    public List<Order> findAll() {
        return entityManager
                .createQuery("Select a from Order a", Order.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        entityManager
                .createQuery("delete from Order a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();

    }

    @Override
    @Transactional
    public void save(Order order) {
        entityManager.persist(order);
    }

    @Override
    @Transactional
    public void update(Order order) {
        entityManager.merge(order);
    }
}
