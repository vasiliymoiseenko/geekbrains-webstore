package ru.geekbrains.webstore.mapper;

import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.dto.OrderItemDto;
import ru.geekbrains.webstore.entity.OrderItem;
import ru.geekbrains.webstore.service.ProductService;

@Mapper
public interface OrderItemMapper {

  OrderItemMapper ORDER_ITEM_MAPPER = Mappers.getMapper(OrderItemMapper.class);

  @Mapping(target = "productId", expression = "java(orderItem.getProduct().getId())")
  @Mapping(target = "productTitle", expression = "java(orderItem.getProduct().getTitle())")
  OrderItemDto fromOrderItem(OrderItem orderItem);

  @Mapping(target = "product", ignore = true)
  OrderItem toOrderItem(OrderItemDto orderItemDto, @Context ProductService productService);

  @AfterMapping
  default void toOrderItem(@MappingTarget OrderItem orderItem, OrderItemDto orderItemDto, @Context ProductService productService) {
    orderItem.setProduct(productService.findById(orderItemDto.getProductId()));
  }

  List<OrderItemDto> fromOrderItemList(List<OrderItem> orderItemList);
}
