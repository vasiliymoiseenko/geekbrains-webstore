package ru.geekbrains.webstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsDto {

  private String phone;
  private String address;

}
