package ru.geekbrains.webstore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.components.Cart;
import ru.geekbrains.webstore.services.CartService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

  private CartService cartService;

  @GetMapping
  public Cart getCart() {
    return cartService.getCart();
  }

  @GetMapping("/add/{id}")
  public void addProduct(@PathVariable Long id) {
    cartService.add(id);
  }

  @GetMapping("/remove/{id}")
  public void removeProduct(@PathVariable Long id) {
    cartService.remove(id);
  }
}
