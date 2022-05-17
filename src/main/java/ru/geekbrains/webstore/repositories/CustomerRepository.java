package ru.geekbrains.webstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.webstore.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
