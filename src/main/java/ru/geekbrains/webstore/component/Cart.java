package ru.geekbrains.webstore.component;

import static ru.geekbrains.webstore.mapper.ProductMapper.PRODUCT_MAPPER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.geekbrains.webstore.dto.ProductDto;
import ru.geekbrains.webstore.entity.Product;
import ru.geekbrains.webstore.exception.ResourceNotFoundException;

@Component
@Scope("singleton")
public class Cart {

  private final List<Product> productList = new ArrayList<>();
  @Getter
  private Long totalPrice = 0L;

  public List<ProductDto> getProductDtoList() {
    return Collections.unmodifiableList(PRODUCT_MAPPER.fromProductList(productList));
  }

  public void add(Product product) {
    productList.add(product);
    totalPrice += product.getPrice();
  }

  public void remove(Product product) {
    if (productList.remove(product)) {
      totalPrice -= product.getPrice();
    } else {
      throw new ResourceNotFoundException("Product not in cart");
    }
  }
}
