package ru.geekbrains.webstore.services;

import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.dtos.OrderDto;
import ru.geekbrains.webstore.entities.Order;
import ru.geekbrains.webstore.entities.Product;
import ru.geekbrains.webstore.entities.User;
import ru.geekbrains.webstore.exceptions.ResourceNotFoundException;
import ru.geekbrains.webstore.repositories.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService implements ru.geekbrains.webstore.services.Service<Order> {

  private OrderRepository orderRepository;
  private UserService userService;
  private ProductService productService;

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

  @Transactional
  public void updateOrderFromDto(OrderDto orderDto) {
    Long id = orderDto.getId();
    Order order = findById(id).orElseThrow(() -> new ResourceNotFoundException("Order id = " + id + " not found"));
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
  }
}
