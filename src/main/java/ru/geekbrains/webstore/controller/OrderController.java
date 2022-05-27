package ru.geekbrains.webstore.controller;

import static ru.geekbrains.webstore.mapper.OrderMapper.ORDER_MAPPER;

import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.dto.OrderDto;
import ru.geekbrains.webstore.entity.Order;
import ru.geekbrains.webstore.entity.Product;
import ru.geekbrains.webstore.entity.User;
import ru.geekbrains.webstore.exception.DataValidationException;
import ru.geekbrains.webstore.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.service.OrderService;
import ru.geekbrains.webstore.service.ProductService;
import ru.geekbrains.webstore.service.UserService;

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
  public OrderDto saveOrder(@RequestBody @Validated OrderDto orderDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new DataValidationException(bindingResult.getAllErrors().stream()
          .map(ObjectError::getDefaultMessage)
          .collect(Collectors.toList()));
    }
    return ORDER_MAPPER.fromOrder(orderService.save(orderDto));
  }

  @PutMapping
  public OrderDto updateOrder(@RequestBody @Validated OrderDto orderDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new DataValidationException(bindingResult.getAllErrors().stream()
          .map(ObjectError::getDefaultMessage)
          .collect(Collectors.toList()));
    }
    return ORDER_MAPPER.fromOrder(orderService.update(orderDto));
  }

  @DeleteMapping("/{id}")
  public void deleteOrder(@PathVariable Long id) {
    orderService.deleteById(id);
  }
}
