package ru.geekbrains.webstore.core.service;

import static ru.geekbrains.webstore.core.mapper.OrderMapper.ORDER_MAPPER;

import java.security.Principal;
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
import ru.geekbrains.webstore.core.integration.CartServiceIntegration;
import ru.geekbrains.webstore.core.repository.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService {

  private OrderRepository orderRepository;
  private UserService userService;
  private ProductService productService;
  private CartServiceIntegration cartServiceIntegration;

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
    Order order = ORDER_MAPPER.toOrder(orderDto, userService, productService);
    order.getItems().forEach(i -> i.setOrder(order));
    return orderRepository.save(order);
  }

  public Order createOrder(OrderDetailsDto orderDetailsDto, Principal principal) {
    OrderDto orderDto = new OrderDto();
    CartDto cart = cartServiceIntegration.getUserCartDto(principal);

    orderDto.setUsername(principal.getName());
    orderDto.setPhone(orderDetailsDto.getPhone());
    orderDto.setAddress(orderDetailsDto.getAddress());
    orderDto.setPrice(cart.getTotalPrice());
    orderDto.getItems().addAll(cart.getItems());
    cartServiceIntegration.clear(principal);

    return save(orderDto);
  }

  public List<Order> findAllByUsername(String username) {
    return orderRepository.findAllByUsername(username);
  }
}
