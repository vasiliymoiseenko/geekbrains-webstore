package ru.geekbrains.webstore.service;

import static ru.geekbrains.webstore.mapper.CommentMapper.COMMENT_MAPPER;
import static ru.geekbrains.webstore.mapper.OrderMapper.ORDER_MAPPER;

import java.security.Principal;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.dto.CommentDto;
import ru.geekbrains.webstore.dto.OrderDto;
import ru.geekbrains.webstore.dto.OrderItemDto;
import ru.geekbrains.webstore.entity.Comment;
import ru.geekbrains.webstore.entity.OrderItem;
import ru.geekbrains.webstore.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.repository.OrderItemRepository;

@Service
@AllArgsConstructor
public class OrderItemService {

  private OrderItemRepository orderItemRepository;
  private OrderService orderService;

  private UserService userService;

  public OrderItem updateComment(CommentDto commentDto, Principal principal) {
    Long id = commentDto.getOrderItemId();
    OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("OrderItem id = " + id + " not found"));
    commentDto.setUsername(principal.getName());
    Comment comment = COMMENT_MAPPER.toComment(commentDto, userService, this);
    orderItem.setComment(comment);
    return orderItemRepository.save(orderItem);
  }

  public OrderItemDto checkComments(Long productId, Principal principal) {
    List<OrderDto> orders = ORDER_MAPPER.toOrderDtoList(orderService.findAllByUsername(principal.getName()));
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
