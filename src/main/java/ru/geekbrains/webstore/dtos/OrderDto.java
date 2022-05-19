package ru.geekbrains.webstore.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.webstore.entities.Order;

@Data
@NoArgsConstructor
public class OrderDto {

  private Long id;

  @NotNull(message = "Order must have a purchase price")
  @Min(value = 1, message = "Purchase price must be greater than 0")
  private Double purchasePrise;

  @NotNull(message = "Customer must have a name")
  private String customerName;

  @NotNull(message = "Product must have a title")
  private String productTitle;

  public OrderDto(Order order) {
    this.id = order.getId();
    this.purchasePrise = order.getPurchasePrise();
    this.customerName = order.getCustomer().getName();
    this.productTitle = order.getProduct().getTitle();
  }
}
