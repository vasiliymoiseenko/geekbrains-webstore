package ru.geekbrains.webstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.webstore.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
