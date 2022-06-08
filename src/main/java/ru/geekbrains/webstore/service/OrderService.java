package ru.geekbrains.webstore.service;

import static ru.geekbrains.webstore.mapper.OrderItemMapper.ORDER_ITEM_MAPPER;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.webstore.component.Cart;
import ru.geekbrains.webstore.dto.OrderItemDto;
import ru.geekbrains.webstore.entity.Order;
import ru.geekbrains.webstore.entity.OrderItem;
import ru.geekbrains.webstore.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.repository.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService {

  private OrderRepository orderRepository;
  private UserService userService;
  private ProductService productService;

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
  public Order save(Cart cart) {
    Order order = new Order();
    order.setPrice(cart.getPrice());
    order.setUser(userService.findByUsername("User1").get());
    for (OrderItemDto i : cart.getItems()) {
      OrderItem item = ORDER_ITEM_MAPPER.toOrderItem(i, productService);
      item.setOrder(order);
      order.getItems().add(item);
    }
    cart.clear();
    return orderRepository.save(order);
  }

}
