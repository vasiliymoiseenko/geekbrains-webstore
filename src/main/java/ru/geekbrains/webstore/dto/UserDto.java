package ru.geekbrains.webstore.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.webstore.entity.Role;
import ru.geekbrains.webstore.entity.User;

@Data
@NoArgsConstructor
public class UserDto {

  private Long id;

  @NotNull(message = "User must have a name")
  @Length(min = 3, max = 255, message = "Name length must be 3-255 characters")
  private String username;

  @NotNull(message = "User must have a password")
  @Length(min = 3, max = 255, message = "Password length must be 3-255 characters")
  private String password;

  private List<Role> roles;
}
