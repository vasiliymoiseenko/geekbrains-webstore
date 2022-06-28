package ru.geekbrains.webstore.cart.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.api.dto.ProductDto;
import ru.geekbrains.webstore.cart.integration.ProductServiceIntegration;
import ru.geekbrains.webstore.cart.service.CartService;
import ru.geekbrains.webstore.cart.util.Cart;
import ru.geekbrains.webstore.cart.util.CartUuid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

  private CartService cartService;
  private ProductServiceIntegration productServiceIntegration;

  @GetMapping("/generate")
  public CartUuid generateUuid() {
    return cartService.generateUuid();
  }

  @GetMapping("{uuid}/merge")
  public void mergeCarts(@RequestHeader(required = false) String username, @PathVariable String uuid) {
    cartService.merge(cartService.getCartId(username, null), cartService.getCartId(null, uuid));
  }

  @GetMapping("{uuid}")
  public Cart getCart(@RequestHeader(required = false) String username, @PathVariable String uuid) {
    return cartService.getCartForCurrentUser(cartService.getCartId(username, uuid));
  }

  @GetMapping("{uuid}/add/{id}")
  public void addProduct(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
    ProductDto product = productServiceIntegration.getProductById(id);
    cartService.add(cartService.getCartId(username, uuid), product);
  }

  @GetMapping("{uuid}/sub/{id}")
  public void subProduct(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
    cartService.sub(cartService.getCartId(username, uuid), id);
  }

  @GetMapping("{uuid}/remove/{id}")
  public void removeProduct(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
    cartService.remove(cartService.getCartId(username, uuid), id);
  }

  @GetMapping("/{uuid}/clear")
  public void clear(@RequestHeader(required = false) String username, @PathVariable String uuid) {
    cartService.clear(cartService.getCartId(username, uuid));
  }
}
