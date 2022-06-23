package ru.geekbrains.webstore.core.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.webstore.core.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query("select o from Order o where o.user.username = :username")
  List<Order> findAllByUsername(String username);

}
