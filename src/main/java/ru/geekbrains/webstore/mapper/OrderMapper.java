package ru.geekbrains.webstore.mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.dto.OrderDto;
import ru.geekbrains.webstore.dto.UserDto;
import ru.geekbrains.webstore.entity.Order;
import ru.geekbrains.webstore.entity.User;

@Mapper
public interface OrderMapper {

  OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

  @Mapping(target = "username", source = "user", qualifiedByName = "userToUsername")
  OrderDto fromOrder(Order order);

  @Named("userToUsername")
  default String userToUsername(User user) {
    return user.getUsername();
  }
}
