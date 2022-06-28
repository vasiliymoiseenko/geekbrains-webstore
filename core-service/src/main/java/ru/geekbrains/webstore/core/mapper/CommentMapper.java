package ru.geekbrains.webstore.core.mapper;

import ru.geekbrains.webstore.core.entity.Comment;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.api.dto.CommentDto;
import ru.geekbrains.webstore.core.service.OrderItemService;

@Mapper
public interface CommentMapper {

  CommentMapper COMMENT_MAPPER = Mappers.getMapper(CommentMapper.class);

  //@Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
  @Mapping(target = "orderItemId", expression = "java(comment.getOrderItem().getId())")
  CommentDto fromComment(Comment comment);

  //@Mapping(target = "user", ignore = true)
  @Mapping(target = "orderItem", ignore = true)
  Comment toComment(CommentDto commentDto, @Context OrderItemService orderItemService);

  @AfterMapping
  default void toComment(@MappingTarget Comment comment, CommentDto commentDto, @Context OrderItemService orderItemService) {
    comment.setOrderItem(orderItemService.findById(commentDto.getOrderItemId()));
  }
}
