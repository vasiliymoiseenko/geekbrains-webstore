package ru.geekbrains.webstore.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.geekbrains.webstore.dtos.ProductDto;
import ru.geekbrains.webstore.entities.Product;
import ru.geekbrains.webstore.exceptions.ResourceNotFoundException;

@Component
@Scope("singleton")
public class Cart {

  private final List<Product> productList;
  private final List<ProductDto> productDtoList;

  @Getter
  private Double totalPrice;

  public Cart() {
    this.productList = new ArrayList<>();
    this.productDtoList = new ArrayList<>();
    this.totalPrice = 0.0;
  }

  public List<ProductDto> getProductDtoList() {
    return Collections.unmodifiableList(productDtoList);
  }

  public void add(Product product) {
    productList.add(product);
    productDtoList.add(new ProductDto(product));
    totalPrice += product.getPrice();
  }

  public void remove(Product product) {
    if (productList.remove(product)) {
      productDtoList.remove(new ProductDto(product));
      totalPrice -= product.getPrice();
    } else {
      throw new ResourceNotFoundException("Product not in cart");
    }
  }
}
