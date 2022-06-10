package ru.geekbrains.webstore.controller;

import static ru.geekbrains.webstore.mapper.OrderMapper.ORDER_MAPPER;

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
import ru.geekbrains.webstore.dto.OrderDetailsDto;
import ru.geekbrains.webstore.dto.OrderDto;
import ru.geekbrains.webstore.service.OrderService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

  private OrderService orderService;

  /*@GetMapping
  public Page<OrderDto> getAllOrders(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
    return orderService.findAll(pageIndex - 1, 10).map(ORDER_MAPPER::fromOrder);
  }
*/

  @GetMapping
  public List<OrderDto> getAllOrders(Principal principal) {
    return ORDER_MAPPER.toOrderDtoList(orderService.findAllByUsername(principal.getName()));
  }

  @GetMapping("/{id}")
  public OrderDto getOrderById(@PathVariable Long id) {
    return ORDER_MAPPER.fromOrder(orderService.findById(id));
  }

  @PostMapping
  public OrderDto saveOrder(@RequestBody OrderDetailsDto orderDetailsDto, Principal principal) {
    return ORDER_MAPPER.fromOrder(orderService.createOrder(orderDetailsDto, principal.getName()));
  }

  @DeleteMapping("/{id}")
  public void deleteOrder(@PathVariable Long id) {
    orderService.deleteById(id);
  }

}
