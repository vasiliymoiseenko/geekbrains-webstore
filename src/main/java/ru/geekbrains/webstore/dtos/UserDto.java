package ru.geekbrains.webstore.dtos;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.webstore.entities.User;

@Data
@NoArgsConstructor
public class UserDto {

  private Long id;

  @NotNull(message = "User must have a name")
  @Length(min = 3, max = 255, message = "Name length must be 3-255 characters")
  private String username;

  public UserDto(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
  }
}
