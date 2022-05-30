package ru.geekbrains.webstore.service;

import static ru.geekbrains.webstore.mapper.OrderMapper.ORDER_MAPPER;

import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.dto.OrderDto;
import ru.geekbrains.webstore.entity.Order;
import ru.geekbrains.webstore.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.repository.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService {

  private OrderRepository orderRepository;
  private UserService userService;
  private ProductService productService;

  public Page<Order> findAll(int pageIndex, int pageSize) {
    if (pageIndex < 0) {
      pageIndex = 0;
    }
    return orderRepository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by("id")));
  }

  public Order findById(Long id) {
    return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order id = " + id + " not found"));

  }

  public void deleteById(Long id) {
    orderRepository.deleteById(id);
  }

  public Order save(OrderDto orderDto) {
    Order order = ORDER_MAPPER.toOrder(orderDto, userService, productService);
    return orderRepository.save(order);
  }

  @Transactional
  public Order update(OrderDto orderDto) {
    Order order = ORDER_MAPPER.toOrder(orderDto, userService, productService);
    return orderRepository.save(order);
  }
}
