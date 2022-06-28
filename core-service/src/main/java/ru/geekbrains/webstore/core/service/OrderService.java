package ru.geekbrains.webstore.core.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.webstore.api.dto.CartDto;
import ru.geekbrains.webstore.api.dto.OrderDetailsDto;
import ru.geekbrains.webstore.api.dto.OrderDto;
import ru.geekbrains.webstore.api.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.core.entity.Order;
import ru.geekbrains.webstore.core.entity.Status;
import ru.geekbrains.webstore.core.integration.CartServiceIntegration;
import ru.geekbrains.webstore.core.mapper.OrderMapper;
import ru.geekbrains.webstore.core.repository.OrderRepository;
import ru.geekbrains.webstore.core.repository.StatusRepository;

@Service
@AllArgsConstructor
public class OrderService {

  private OrderRepository orderRepository;
  private ProductService productService;
  private CartServiceIntegration cartServiceIntegration;
  private StatusRepository statusRepository;

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


  @Transactional
  public Order save(OrderDto orderDto) {
    Order order = OrderMapper.ORDER_MAPPER.toOrder(orderDto, productService);
    Status status = statusRepository.findStatusByTitle("Awaiting payment")
        .orElseThrow(() -> new ResourceNotFoundException("Status \"Awayting payment\" not found"));
    order.setStatus(status);
    order.getItems().forEach(i -> i.setOrder(order));
    return orderRepository.save(order);
  }

  public Order createOrder(OrderDetailsDto orderDetailsDto, String username) {
    OrderDto orderDto = new OrderDto();
    CartDto cart = cartServiceIntegration.getUserCartDto(username);

    orderDto.setUsername(username);
    orderDto.setPhone(orderDetailsDto.getPhone());
    orderDto.setAddress(orderDetailsDto.getAddress());
    orderDto.setPrice(cart.getTotalPrice());
    orderDto.setItems(new ArrayList<>());
    orderDto.getItems().addAll(cart.getItems());
    cartServiceIntegration.clearUserCart(username);

    return save(orderDto);
  }

  public List<Order> findAllByUsername(String username) {
    return orderRepository.findAllByUsername(username);
  }

  public Order findByIdAndUsername(Long id, String username) {
    return orderRepository.findOrderByIdAndUsername(id, username)
        .orElseThrow(() -> new ResourceNotFoundException("Order id = " + id + "  and username = " + username + " not found"));
  }

  @Transactional
  public void setStatusPaid(Long id) {
    Order order = findById(id);
    Status status = statusRepository.findStatusByTitle("Paid")
        .orElseThrow(() -> new ResourceNotFoundException("Status \"Paid\" not found"));
    order.setStatus(status);
  }
}
