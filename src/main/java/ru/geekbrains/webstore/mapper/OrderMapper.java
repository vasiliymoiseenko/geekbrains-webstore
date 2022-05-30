package ru.geekbrains.webstore.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.dto.OrderDto;
import ru.geekbrains.webstore.entity.Order;
import ru.geekbrains.webstore.entity.Product;
import ru.geekbrains.webstore.entity.User;
import ru.geekbrains.webstore.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.service.ProductService;
import ru.geekbrains.webstore.service.UserService;

@Mapper
public interface OrderMapper {

  OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

  @Mapping(target = "username", source = "user", qualifiedByName = "userToUsername")
  @Mapping(target = "productTitle", source = "product", qualifiedByName = "productToTitle")
  OrderDto fromOrder(Order order);

  @Named("userToUsername")
  default String userToUsername(User user) {
    return user.getUsername();
  }

  @Named("productToTitle")
  default String productToTitle(Product product) {
    return product.getTitle();
  }

  @Mapping(target = "user", ignore = true)
  @Mapping(target = "product", ignore = true)
  Order toOrder(OrderDto orderDto, @Context UserService userRepository, @Context ProductService productRepository);

  @AfterMapping
  default void toOrder(@MappingTarget Order order, OrderDto orderDto, @Context UserService userRepository,
      @Context ProductService productRepository) {
    order.setUser(userRepository.findByUsername(orderDto.getUsername())
        .orElseThrow(() -> new ResourceNotFoundException("User name = " + orderDto.getUsername() + " not found")));
    order.setProduct(productRepository.findByTitle(orderDto.getProductTitle())
        .orElseThrow(() -> new ResourceNotFoundException("Product title = " + orderDto.getProductTitle() + " not found")));
  }
}
