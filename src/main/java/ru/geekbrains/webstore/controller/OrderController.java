package ru.geekbrains.webstore.controller;

import static ru.geekbrains.webstore.mapper.OrderMapper.ORDER_MAPPER;

import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.component.Cart;
import ru.geekbrains.webstore.dto.OrderDto;
import ru.geekbrains.webstore.service.OrderService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

  private OrderService orderService;

  @GetMapping
  public Page<OrderDto> getAllOrders(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
    return orderService.findAll(pageIndex - 1, 10).map(ORDER_MAPPER::fromOrder);
  }

  @GetMapping("/{id}")
  public OrderDto getOrderById(@PathVariable Long id) {
    return ORDER_MAPPER.fromOrder(orderService.findById(id));
  }

  @PostMapping
  public void saveOrder(@RequestBody Cart cart, Principal principal) {
    orderService.save(cart);
  }

  @DeleteMapping("/{id}")
  public void deleteOrder(@PathVariable Long id) {
    orderService.deleteById(id);
  }

}
