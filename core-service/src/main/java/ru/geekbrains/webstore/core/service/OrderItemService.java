package ru.geekbrains.webstore.core.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.api.dto.CommentDto;
import ru.geekbrains.webstore.api.dto.OrderDto;
import ru.geekbrains.webstore.api.dto.OrderItemDto;
import ru.geekbrains.webstore.api.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.core.entity.Comment;
import ru.geekbrains.webstore.core.entity.OrderItem;
import ru.geekbrains.webstore.core.mapper.CommentMapper;
import ru.geekbrains.webstore.core.mapper.OrderMapper;
import ru.geekbrains.webstore.core.repository.OrderItemRepository;

@Service
@AllArgsConstructor
public class OrderItemService {

  private OrderItemRepository orderItemRepository;
  private OrderService orderService;

  public OrderItem updateComment(CommentDto commentDto, String username) {
    Long id = commentDto.getOrderItemId();
    OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("OrderItem id = " + id + " not found"));
    commentDto.setUsername(username);
    Comment comment = CommentMapper.COMMENT_MAPPER.toComment(commentDto, this);
    orderItem.setComment(comment);
    return orderItemRepository.save(orderItem);
  }

  public OrderItemDto checkComments(Long productId, String username) {
    List<OrderDto> orders = OrderMapper.ORDER_MAPPER.toOrderDtoList(orderService.findAllByUsername(username));
    for (OrderDto order : orders) {
      for (OrderItemDto item : order.getItems()) {
        if (item.getProductId().equals(productId) && item.getCommentDto() == null) {
          return item;
        }
      }
    }
    return new OrderItemDto();
  }

  public OrderItem findById(Long id) {
    return orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("OrderItem id = " + id + " not found"));
  }
}
