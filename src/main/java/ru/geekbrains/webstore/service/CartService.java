package ru.geekbrains.webstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.component.Cart;

@Service
@RequiredArgsConstructor
public class CartService {

  private final ProductService productService;
  private final RedisTemplate<String, Object> redisTemplate;

  public Cart getCartForCurrentUser() {
    if (!redisTemplate.hasKey("cart")) {
      redisTemplate.opsForValue().set("cart", new Cart());
    }
    Cart cart = (Cart) redisTemplate.opsForValue().get("cart");
    return cart;
  }

  public void updateCart(Cart cart) {
    redisTemplate.opsForValue().set("cart", cart);
  }

  public void add(Long id) {
    Cart cart = getCartForCurrentUser();
    if (cart.add(id)) {
      updateCart(cart);
      return;
    }
    cart.add(productService.findById(id));
    updateCart(cart);
  }

  public void sub(Long id) {
    Cart cart = getCartForCurrentUser();
    cart.sub(id);
    updateCart(cart);
  }

  public void remove(Long id) {
    Cart cart = getCartForCurrentUser();
    cart.remove(id);
    updateCart(cart);
  }

  public void clear() {
    Cart cart = getCartForCurrentUser();
    cart.clear();
    updateCart(cart);
  }
}
