package ru.geekbrains.webstore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.components.Cart;
import ru.geekbrains.webstore.entities.Product;
import ru.geekbrains.webstore.exceptions.ResourceNotFoundException;

@Service
@AllArgsConstructor
public class CartService {

  private ProductService productService;
  private Cart cart;

  public Cart getCart() {
    return cart;
  }

  public void add(Long id) {
    Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found"));
    cart.add(product);
  }

  public void remove(Long id) {
    Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found"));
    cart.remove(product);
  }
}
