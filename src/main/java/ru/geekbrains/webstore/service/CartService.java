package ru.geekbrains.webstore.service;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.component.Cart;

@Service
@RequiredArgsConstructor
public class CartService {

  private final ProductService productService;
  private Cart cart;

  @PostConstruct
  public void init() {
    this.cart = new Cart();
  }

  public Cart getCart() {
    return cart;
  }

  public void add(Long id) {
    if (cart.add(id)) {
      return;
    }
    cart.add(productService.findById(id));
  }

  public void sub(Long id) {
    cart.sub(id);
  }

  public void remove(Long id) {
    cart.remove(id);
  }

  public void clear() {
    cart.clear();
  }
}
