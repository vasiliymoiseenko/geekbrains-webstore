package ru.geekbrains.webstore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.webstore.entities.Order;

@Data
@NoArgsConstructor
public class OrderDto {

  private Long id;

  private Double purchasePrise;

  private String customerName;

  private String productTitle;

  public OrderDto(Order order) {
    this.id = order.getId();
    this.purchasePrise = order.getPurchasePrise();
    this.customerName = order.getCustomer().getName();
    this.productTitle = order.getProduct().getTitle();
  }
}
