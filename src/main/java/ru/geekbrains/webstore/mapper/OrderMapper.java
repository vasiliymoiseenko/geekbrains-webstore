package ru.geekbrains.webstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.dto.OrderDto;
import ru.geekbrains.webstore.entity.Order;
import ru.geekbrains.webstore.entity.Product;
import ru.geekbrains.webstore.entity.User;

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
}
