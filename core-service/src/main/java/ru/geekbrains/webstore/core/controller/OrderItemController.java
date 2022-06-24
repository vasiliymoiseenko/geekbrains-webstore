package ru.geekbrains.webstore.core.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.api.dto.CommentDto;
import ru.geekbrains.webstore.api.dto.OrderItemDto;
import ru.geekbrains.webstore.core.mapper.OrderItemMapper;
import ru.geekbrains.webstore.core.service.OrderItemService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order_items")
public class OrderItemController {

  private OrderItemService orderItemService;

  @GetMapping("/check/{productId}")
  public OrderItemDto checkComments(@PathVariable Long productId, @RequestHeader String username) {
    return orderItemService.checkComments(productId, username);
  }

  @PutMapping
  public OrderItemDto updateOrderItem(@RequestBody CommentDto commentDto, @RequestHeader String username) {
    return OrderItemMapper.ORDER_ITEM_MAPPER.fromOrderItem(orderItemService.updateComment(commentDto, username));
  }
}
