package ru.geekbrains.webstore.controller;

import static ru.geekbrains.webstore.mapper.OrderMapper.ORDER_MAPPER;

import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<?> getAllOrders(Principal principal) {
    if (principal == null) {
      return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>(ORDER_MAPPER.toOrderDtoList(orderService.findAllByUsername(principal.getName())), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public OrderDto getOrderById(@PathVariable Long id) {
    return ORDER_MAPPER.fromOrder(orderService.findById(id));
  }

  @PostMapping
  public ResponseEntity<?> saveOrder(@RequestBody OrderDetailsDto orderDetailsDto, Principal principal) {
    if (principal == null) {
      return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>(ORDER_MAPPER.fromOrder(orderService.createOrder(orderDetailsDto, principal.getName())), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public void deleteOrder(@PathVariable Long id) {
    orderService.deleteById(id);
  }

}
