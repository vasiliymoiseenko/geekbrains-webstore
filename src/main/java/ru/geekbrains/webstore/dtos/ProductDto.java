package ru.geekbrains.webstore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.webstore.entities.Product;

@Data
@NoArgsConstructor
public class ProductDto {

  private Long id;

  private String title;

  private Double price;

  public ProductDto(Product product) {
    this.id = product.getId();
    this.title = product.getTitle();
    this.price = product.getPrice();
  }

}
