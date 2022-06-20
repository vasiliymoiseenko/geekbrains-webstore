package ru.geekbrains.webstore.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.webstore.entity.Role;

@Data
@NoArgsConstructor
public class ProfileDto {

  private Long id;

  @NotNull(message = "User must have a name")
  @Length(min = 3, max = 255, message = "Name length must be 3-255 characters")
  private String username;

  @NotNull(message = "User must have a password")
  @Length(min = 3, max = 255, message = "Password length must be 3-255 characters")
  private String password;

  private String firstName;

  private String middleName;

  private String lastName;

  private String email;

  private Long phone;

  private List<Role> roles;
}
