package ru.geekbrains.webstore.controller;

import static ru.geekbrains.webstore.mapper.OrderItemMapper.ORDER_ITEM_MAPPER;

import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.dto.CommentDto;
import ru.geekbrains.webstore.dto.OrderItemDto;
import ru.geekbrains.webstore.service.OrderItemService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order_items")
public class OrderItemController {

  private OrderItemService orderItemService;

  @GetMapping("/check/{productId}")
  public OrderItemDto checkComments(@PathVariable Long productId, Principal principal) {
    return orderItemService.checkComments(productId, principal);
  }

  @PutMapping
  public OrderItemDto updateOrderItem(@RequestBody CommentDto commentDto, Principal principal) {
    return ORDER_ITEM_MAPPER.fromOrderItem(orderItemService.updateComment(commentDto, principal));
  }
}
