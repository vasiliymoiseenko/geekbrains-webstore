package ru.geekbrains.webstore.service;

import static ru.geekbrains.webstore.mapper.OrderMapper.ORDER_MAPPER;

import java.security.Principal;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.dto.OrderDto;
import ru.geekbrains.webstore.dto.OrderItemDto;
import ru.geekbrains.webstore.entity.OrderItem;
import ru.geekbrains.webstore.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.repository.OrderItemRepository;

@Service
@AllArgsConstructor
public class OrderItemService {

  private OrderItemRepository orderItemRepository;
  private OrderService orderService;

  public OrderItem update(OrderItemDto orderItemDto) {
    Long id = orderItemDto.getId();
    OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found"));
    orderItem.setComment(orderItemDto.getComment());
    return orderItemRepository.save(orderItem);
  }

  public OrderItemDto checkComments(Long productId, Principal principal) {
    List<OrderDto> orders = ORDER_MAPPER.toOrderDtoList(orderService.findAllByUsername(principal.getName()));
    for (OrderDto order : orders) {
      for (OrderItemDto item : order.getItems()) {
        if (item.getProductId().equals(productId) && item.getComment() == null) {
          return item;
        }
      }
    }
    return new OrderItemDto();
  }
}
