package ru.geekbrains.webstore.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {

  private Long id;
  private String username;
  private Long Price;
  private List<OrderItemDto> items = new ArrayList<>();

}
