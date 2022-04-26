package ru.geekbrains.webstore.repositories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.webstore.entities.Customer;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomerRepository implements Repository<Customer>{

    private final EntityManager entityManager;

    @Override
    @Transactional
    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    @Transactional
    public List<Customer> findAll() {
        return entityManager
                .createQuery("Select a from Customer a", Customer.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        entityManager
                .createQuery("delete from Customer a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);
    }
}
