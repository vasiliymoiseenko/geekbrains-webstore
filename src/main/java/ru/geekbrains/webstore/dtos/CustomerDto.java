package ru.geekbrains.webstore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.webstore.entities.Customer;

@Data
@NoArgsConstructor
public class CustomerDto {

  private Long id;

  private String name;

  public CustomerDto(Customer customer) {
    this.id = customer.getId();
    this.name = customer.getName();
  }
}
