package ru.geekbrains.webstore.service;

import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.dto.OrderDto;
import ru.geekbrains.webstore.entity.Order;
import ru.geekbrains.webstore.entity.Product;
import ru.geekbrains.webstore.entity.User;
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
    Order order = new Order();
    order.setPurchasePrise(orderDto.getPurchasePrise());

    User user = userService.findByUsername(orderDto.getUsername())
        .orElseThrow(() -> new ResourceNotFoundException("Username = " + orderDto.getUsername() + " not found"));
    order.setUser(user);

    Product product = productService.findByTitle(orderDto.getProductTitle())
        .orElseThrow(() -> new ResourceNotFoundException("Product title = " + orderDto.getProductTitle() + " not found"));
    order.setProduct(product);

    return orderRepository.save(order);
  }

  @Transactional
  public Order update(OrderDto orderDto) {
    Long id = orderDto.getId();
    Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order id = " + id + " not found"));
    order.setPurchasePrise(orderDto.getPurchasePrise());

    if (!order.getUser().getUsername().equals(orderDto.getUsername())) {
      User user = userService.findByUsername(orderDto.getUsername())
          .orElseThrow(() -> new ResourceNotFoundException("User name = " + orderDto.getUsername() + " not found"));
      order.setUser(user);
    }

    if (!order.getProduct().getTitle().equals(orderDto.getProductTitle())) {
      Product product = productService.findByTitle(orderDto.getProductTitle())
          .orElseThrow(() -> new ResourceNotFoundException("Product title = " + orderDto.getProductTitle() + " not found"));
      order.setProduct(product);
    }

    return orderRepository.save(order);
  }
}
