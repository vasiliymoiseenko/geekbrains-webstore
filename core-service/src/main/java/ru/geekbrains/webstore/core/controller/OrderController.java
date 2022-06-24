package ru.geekbrains.webstore.core.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import ru.geekbrains.webstore.core.mapper.OrderMapper;
import ru.geekbrains.webstore.core.service.OrderService;
import java.security.Principal;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.api.dto.OrderDetailsDto;
import ru.geekbrains.webstore.api.dto.OrderDto;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
@CrossOrigin("*")
public class OrderController {

  private OrderService orderService;

  @GetMapping
  public List<OrderDto> getAllOrders(Principal principal) {
    return OrderMapper.ORDER_MAPPER.toOrderDtoList(orderService.findAllByUsername(principal.getName()));
  }

  @GetMapping("/{id}")
  public OrderDto getOrderById(@PathVariable Long id) {
    return OrderMapper.ORDER_MAPPER.fromOrder(orderService.findById(id));
  }

  @PostMapping
  public OrderDto saveOrder(@RequestBody OrderDetailsDto orderDetailsDto, Principal principal) {
    return OrderMapper.ORDER_MAPPER.fromOrder(orderService.createOrder(orderDetailsDto, principal));
  }

  @DeleteMapping("/{id}")
  public void deleteOrder(@PathVariable Long id) {
    orderService.deleteById(id);
  }

}
