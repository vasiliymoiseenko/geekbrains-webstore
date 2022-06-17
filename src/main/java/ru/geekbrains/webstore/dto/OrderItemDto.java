package ru.geekbrains.webstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.webstore.entity.Product;

@Data
@NoArgsConstructor
public class OrderItemDto {

  private Long productId;
  private String productTitle;
  private Integer amount;
  private Long pricePerProduct;
  private Long price;

  public OrderItemDto(Product product) {
    this.productId = product.getId();
    this.productTitle = product.getTitle();
    this.amount = 1;
    this.pricePerProduct = product.getPrice();
    this.price = product.getPrice();
  }

  public void changeQuantity(int delta) {
    amount += delta;
    if (amount <= 0) {
      amount = 0;
    }
    price = pricePerProduct * amount;
  }

}
