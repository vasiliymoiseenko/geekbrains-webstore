package ru.geekbrains.webstore.mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.dto.UserDto;
import ru.geekbrains.webstore.entity.User;

@Mapper
public interface UserMapper {

  UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

  @Mapping(source="roles", target="roles")
  User toUser(UserDto dto);

  @InheritInverseConfiguration
  UserDto fromUser(User user);

  List<User> toUserList(List<UserDto> userDtoList);

  List<UserDto> fromUserList(List<User> userList);
}
