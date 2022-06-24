package ru.geekbrains.webstore.core.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import ru.geekbrains.webstore.core.mapper.OrderItemMapper;
import ru.geekbrains.webstore.core.service.OrderItemService;
import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.api.dto.CommentDto;
import ru.geekbrains.webstore.api.dto.OrderItemDto;

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
    return OrderItemMapper.ORDER_ITEM_MAPPER.fromOrderItem(orderItemService.updateComment(commentDto, principal));
  }
}