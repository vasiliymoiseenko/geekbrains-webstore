package ru.geekbrains.webstore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.dtos.OrderDto;
import ru.geekbrains.webstore.entities.Customer;
import ru.geekbrains.webstore.entities.Order;
import ru.geekbrains.webstore.entities.Product;
import ru.geekbrains.webstore.exceptions.ResourceNotFoundException;
import ru.geekbrains.webstore.services.CustomerService;
import ru.geekbrains.webstore.services.OrderService;
import ru.geekbrains.webstore.services.ProductService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

  private OrderService orderService;
  private CustomerService customerService;
  private ProductService productService;

  @GetMapping
  public Page<OrderDto> getAllOrders(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
    if (pageIndex < 1) {
      pageIndex = 1;
    }
    return orderService.findAll(pageIndex - 1, 10).map(OrderDto::new);
  }

  @GetMapping("/{id}")
  public OrderDto getOrderById(@PathVariable Long id) {
    Order order = orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order id = " + id + " not found"));
    return new OrderDto(order);
  }

  @PostMapping
  public OrderDto addOrder(@RequestBody OrderDto orderDto) {
    Order order = new Order();
    order.setPurchasePrise(orderDto.getPurchasePrise());
    Customer customer = customerService.findByName(orderDto.getCustomerName())
        .orElseThrow(() -> new ResourceNotFoundException("Customer name = " + orderDto.getCustomerName() + " not found"));
    order.setCustomer(customer);
    Product product = productService.findByTitle(orderDto.getProductTitle())
        .orElseThrow(() -> new ResourceNotFoundException("Product title = " + orderDto.getProductTitle() + " not found"));
    order.setProduct(product);
    orderService.save(order);
    return new OrderDto(order);
  }

  @PutMapping
  public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
    Long id = orderDto.getId();
    Order order = orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order id = " + id + " not found"));
    order.setPurchasePrise(orderDto.getPurchasePrise());
    Customer customer = customerService.findByName(orderDto.getCustomerName())
        .orElseThrow(() -> new ResourceNotFoundException("Customer name = " + orderDto.getCustomerName() + " not found"));
    order.setCustomer(customer);
    Product product = productService.findByTitle(orderDto.getProductTitle())
        .orElseThrow(() -> new ResourceNotFoundException("Product title = " + orderDto.getProductTitle() + " not found"));
    order.setProduct(product);
    orderService.save(order);
    return new OrderDto(order);
  }

  @DeleteMapping("/{id}")
  public void deleteOrder(@PathVariable Long id) {
    orderService.deleteById(id);
  }
}
