package ru.geekbrains.webstore.core.mapper;

import static ru.geekbrains.webstore.core.mapper.OrderItemMapper.ORDER_ITEM_MAPPER;

import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.api.dto.OrderDto;
import ru.geekbrains.webstore.api.dto.OrderItemDto;
import ru.geekbrains.webstore.core.entity.Order;
import ru.geekbrains.webstore.core.entity.OrderItem;
import ru.geekbrains.webstore.core.service.ProductService;

@Mapper
public interface OrderMapper {

  OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

  @Mapping(target = "status", expression = "java(order.getStatus().getTitle())")
  @Mapping(target = "items", source = "items", qualifiedByName = "fromOrderItemList")
  OrderDto fromOrder(Order order);

  @Named("fromOrderItemList")
  default List<OrderItemDto> fromOrderItemList(List<OrderItem> items) {
    return ORDER_ITEM_MAPPER.fromOrderItemList(items);
  }

  @Mapping(target = "items", source = "items", qualifiedByName = "toOrderItemList")
  @Mapping(target = "status", ignore = true)
  Order toOrder(OrderDto orderDto, @Context ProductService productService);

  @Named("toOrderItemList")
  default List<OrderItem> toOrderItemList(List<OrderItemDto> items, @Context ProductService productService) {
    List<OrderItem> list = new ArrayList<>();
    items.forEach(i -> list.add(ORDER_ITEM_MAPPER.toOrderItem(i, productService)));
    return list;
  }

  List<OrderDto> toOrderDtoList(List<Order> orderList);
}
