package ru.geekbrains.webstore.controller;

import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.cart.Cart;
import ru.geekbrains.webstore.cart.CartUuid;
import ru.geekbrains.webstore.service.CartService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

  private CartService cartService;

  @GetMapping("/generate")
  public CartUuid generateUuid() {
    return cartService.generateUuid();
  }

  @GetMapping("{uuid}/merge")
  public void mergeCarts(Principal principal, @PathVariable String uuid) {
    cartService.merge(cartService.getCartId(principal, null), cartService.getCartId(null, uuid));
  }

  @GetMapping("{uuid}")
  public Cart getCart(Principal principal, @PathVariable String uuid) {
    return cartService.getCartForCurrentUser(cartService.getCartId(principal, uuid));
  }

  @GetMapping("{uuid}/add/{id}")
  public void addProduct(Principal principal, @PathVariable String uuid, @PathVariable Long id) {
    cartService.add(cartService.getCartId(principal, uuid), id);
  }

  @GetMapping("{uuid}/sub/{id}")
  public void subProduct(Principal principal, @PathVariable String uuid, @PathVariable Long id) {
    cartService.sub(cartService.getCartId(principal, uuid), id);
  }

  @GetMapping("{uuid}/remove/{id}")
  public void removeProduct(Principal principal, @PathVariable String uuid, @PathVariable Long id) {
    cartService.remove(cartService.getCartId(principal, uuid), id);
  }
}
