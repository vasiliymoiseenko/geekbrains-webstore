package ru.geekbrains.webstore.dtos;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.webstore.entities.Customer;

@Data
@NoArgsConstructor
public class CustomerDto {

  private Long id;

  @NotNull(message = "Customer must have a name")
  @Length(min = 3, max = 255, message = "Name length must be 3-255 characters")
  private String name;

  public CustomerDto(Customer customer) {
    this.id = customer.getId();
    this.name = customer.getName();
  }
}
