package ru.geekbrains.webstore.mapper;

import static ru.geekbrains.webstore.mapper.OrderItemMapper.ORDER_ITEM_MAPPER;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.dto.OrderDto;
import ru.geekbrains.webstore.dto.OrderItemDto;
import ru.geekbrains.webstore.entity.Order;
import ru.geekbrains.webstore.entity.OrderItem;

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
}
