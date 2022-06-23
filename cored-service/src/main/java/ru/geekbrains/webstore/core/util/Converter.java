package ru.geekbrains.webstore.core.util;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ru.geekbrains.webstore.api.dto.CategoryDto;
import ru.geekbrains.webstore.api.dto.CommentDto;
import ru.geekbrains.webstore.api.dto.OrderDto;
import ru.geekbrains.webstore.api.dto.OrderItemDto;
import ru.geekbrains.webstore.api.dto.ProductDto;
import ru.geekbrains.webstore.api.dto.ProfileDto;
import ru.geekbrains.webstore.core.entity.Category;
import ru.geekbrains.webstore.core.entity.Comment;
import ru.geekbrains.webstore.core.entity.Order;
import ru.geekbrains.webstore.core.entity.OrderItem;
import ru.geekbrains.webstore.core.entity.Product;
import ru.geekbrains.webstore.core.entity.Profile;

@Component
public class Converter {

  public ProductDto fromProduct(Product product) {
    return new ProductDto(
        product.getId(),
        product.getTitle(),
        product.getPrice(),
        product.getCategory().getTitle(), null);
  }

  public CategoryDto fromCategory(Category category) {
    List<ProductDto> products = category.getProducts().stream().map(this::fromProduct).collect(Collectors.toList());
    return new CategoryDto(
        category.getId(),
        category.getTitle(),
        products);
  }

  public ProfileDto fromProfile(Profile profile) {
    return new ProfileDto(
        profile.getId(),
        profile.getUsername(),
        profile.getPassword(),
        profile.getFirstName(),
        profile.getMiddleName(),
        profile.getLastName(),
        profile.getEmail(),
        profile.getPhone());
  }

  public CommentDto fromComment(Comment comment) {
    if (comment == null) {
      return new CommentDto();
    }
    return new CommentDto(
        comment.getId(),
        comment.getUser().getUsername(),
        comment.getOrderItem().getId(),
        comment.getText(),
        comment.getUpdatedAt());
  }

  public OrderItemDto fromOrderItem(OrderItem orderItem) {
    return new OrderItemDto(
        orderItem.getId(),
        orderItem.getProduct().getId(),
        orderItem.getProduct().getTitle(),
        orderItem.getAmount(),
        orderItem.getPricePerProduct(),
        orderItem.getPrice(),
        fromComment(orderItem.getComment()));
  }

  public OrderDto fromOrder(Order order) {
    List<OrderItemDto> items = order.getItems().stream().map(this::fromOrderItem).collect(Collectors.toList());
    return new OrderDto(
        order.getId(),
        order.getUser().getUsername(),
        order.getPhone(),
        order.getAddress(),
        order.getPrice(),
        items);
  }
}
