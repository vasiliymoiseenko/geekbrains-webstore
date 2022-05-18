package ru.geekbrains.webstore.services;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.entities.Order;
import ru.geekbrains.webstore.repositories.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService implements ru.geekbrains.webstore.services.Service<Order> {

  private OrderRepository orderRepository;

  @Override
  public Page<Order> findAll(int pageIndex, int pageSize) {
    return orderRepository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by("id")));
  }

  @Override
  public Optional<Order> findById(Long id) {
    return orderRepository.findById(id);
  }

  @Override
  public void deleteById(Long id) {
    orderRepository.deleteById(id);
  }

  @Override
  public void save(Order order) {
    orderRepository.save(order);
  }
}
