package ru.geekbrains.webstore.service;

import static ru.geekbrains.webstore.mapper.OrderMapper.ORDER_MAPPER;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.component.Cart;
import ru.geekbrains.webstore.dto.OrderDetailsDto;
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
  private CartService cartService;

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
    order.getItems().forEach(i -> i.setOrder(order));
    return orderRepository.save(order);
  }

  public Order createOrder(OrderDetailsDto orderDetailsDto, String username) {
    OrderDto orderDto = new OrderDto();
    Cart cart = cartService.getCart();

    orderDto.setUsername(username);
    orderDto.setPhone(orderDetailsDto.getPhone());
    orderDto.setAddress(orderDetailsDto.getAddress());
    orderDto.setPrice(cart.getPrice());
    orderDto.getItems().addAll(cart.getItems());
    cartService.clear();

    return save(orderDto);
  }
}
