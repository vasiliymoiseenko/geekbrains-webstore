package ru.geekbrains.webstore.mapper;

import static ru.geekbrains.webstore.mapper.OrderItemMapper.ORDER_ITEM_MAPPER;

import java.util.ArrayList;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.dto.OrderDto;
import ru.geekbrains.webstore.dto.OrderItemDto;
import ru.geekbrains.webstore.entity.Order;
import ru.geekbrains.webstore.entity.OrderItem;
import ru.geekbrains.webstore.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.service.ProductService;
import ru.geekbrains.webstore.service.UserService;

@Mapper
public interface OrderMapper {

  OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

  @Mapping(target = "username", expression = "java(order.getUser().getUsername())")
  @Mapping(target = "items", source = "items", qualifiedByName = "fromOrderItemList")
  OrderDto fromOrder(Order order);

  @Named("fromOrderItemList")
  default List<OrderItemDto> fromOrderItemList(List<OrderItem> items) {
    return ORDER_ITEM_MAPPER.fromOrderItemList(items);
  }

  @Mapping(target = "user", ignore = true)
  @Mapping(target = "items", source = "items", qualifiedByName = "toOrderItemList")
  Order toOrder(OrderDto orderDto, @Context UserService userService, @Context ProductService productService);

  @Named("toOrderItemList")
  default List<OrderItem> toOrderItemList(List<OrderItemDto> items, @Context ProductService productService) {
    List<OrderItem> list = new ArrayList<>();
    items.forEach(i -> list.add(ORDER_ITEM_MAPPER.toOrderItem(i, productService)));
    return list;
  }

  @AfterMapping
  default void toOrder(@MappingTarget Order order, OrderDto orderDto, @Context UserService userService) {
    order.setUser(userService.findByUsername(orderDto.getUsername())
        .orElseThrow(() -> new ResourceNotFoundException("Username = " + orderDto.getUsername() + " not found")));
  }
}
