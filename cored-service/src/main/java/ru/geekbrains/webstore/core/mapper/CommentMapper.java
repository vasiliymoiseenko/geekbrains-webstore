package ru.geekbrains.webstore.core.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.api.dto.CommentDto;
import ru.geekbrains.webstore.core.entity.Comment;
import ru.geekbrains.webstore.core.service.OrderItemService;
import ru.geekbrains.webstore.core.service.UserService;

@Mapper
public interface CommentMapper {

  CommentMapper COMMENT_MAPPER = Mappers.getMapper(CommentMapper.class);

  @Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
  @Mapping(target = "orderItemId", expression = "java(comment.getOrderItem().getId())")
  CommentDto fromComment(Comment comment);

  @Mapping(target = "user", ignore = true)
  @Mapping(target = "orderItem", ignore = true)
  Comment toComment(CommentDto commentDto, @Context UserService userService, @Context OrderItemService orderItemService);

  @AfterMapping
  default void toComment(@MappingTarget Comment comment, CommentDto commentDto, @Context UserService userService,
      @Context OrderItemService orderItemService) {
    comment.setUser(userService.findByUsername(commentDto.getUsername()));
    comment.setOrderItem(orderItemService.findById(commentDto.getOrderItemId()));
  }
}
