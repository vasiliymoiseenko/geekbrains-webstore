package ru.geekbrains.webstore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.component.Cart;
import ru.geekbrains.webstore.entity.Product;
import ru.geekbrains.webstore.exception.ResourceNotFoundException;

@Service
@AllArgsConstructor
public class CartService {

  private ProductService productService;
  private Cart cart;

  public Cart getCart() {
    return cart;
  }

  public void add(Long id) {
    cart.add(productService.findById(id));
  }

  public void remove(Long id) {
    cart.remove(productService.findById(id));
  }
}
