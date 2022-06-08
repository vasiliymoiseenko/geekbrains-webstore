package ru.geekbrains.webstore.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.component.Cart;
import ru.geekbrains.webstore.service.CartService;

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

  @GetMapping("/sub/{id}")
  public void subProduct(@PathVariable Long id) {
    cartService.sub(id);
  }

  @GetMapping("/remove/{id}")
  public void removeProduct(@PathVariable Long id) {
    cartService.remove(id);
  }
}
