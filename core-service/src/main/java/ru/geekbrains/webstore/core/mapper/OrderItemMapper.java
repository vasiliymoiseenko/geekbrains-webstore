package ru.geekbrains.webstore.core.mapper;

import static ru.geekbrains.webstore.core.mapper.CommentMapper.COMMENT_MAPPER;

import ru.geekbrains.webstore.core.entity.Comment;
import ru.geekbrains.webstore.core.entity.OrderItem;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.api.dto.CommentDto;
import ru.geekbrains.webstore.api.dto.OrderItemDto;
import ru.geekbrains.webstore.core.service.ProductService;

@Mapper
public interface OrderItemMapper {

  OrderItemMapper ORDER_ITEM_MAPPER = Mappers.getMapper(OrderItemMapper.class);

  @Mapping(target = "productId", expression = "java(orderItem.getProduct().getId())")
  @Mapping(target = "productTitle", expression = "java(orderItem.getProduct().getTitle())")
  @Mapping(target = "commentDto", source = "comment", qualifiedByName = "fromComment")
  OrderItemDto fromOrderItem(OrderItem orderItem);

  @Named("fromComment")
  default CommentDto fromComment(Comment comment) {
    return COMMENT_MAPPER.fromComment(comment);
  }

  @Mapping(target = "product", ignore = true)
  OrderItem toOrderItem(OrderItemDto orderItemDto, @Context ProductService productService);

  @AfterMapping
  default void toOrderItem(@MappingTarget OrderItem orderItem, OrderItemDto orderItemDto, @Context ProductService productService) {
    orderItem.setProduct(productService.findById(orderItemDto.getProductId()));
  }

  List<OrderItemDto> fromOrderItemList(List<OrderItem> orderItemList);
}
