package ru.geekbrains.webstore.service;

import java.security.Principal;
import java.util.UUID;
import java.util.function.Consumer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.cart.Cart;
import ru.geekbrains.webstore.cart.CartUuid;

@Service
@RequiredArgsConstructor
public class CartService {

  @Value("${utils.cart.prefix}")
  @Getter
  private String cartPrefix;
  private final ProductService productService;
  private final RedisTemplate<String, Object> redisTemplate;

  public Cart getCartForCurrentUser(String cartId) {

    if (!redisTemplate.hasKey(cartId)) {
      redisTemplate.opsForValue().set(cartId, new Cart());
    }
    return (Cart) redisTemplate.opsForValue().get(cartId);
  }

  public void add(String cartId, Long id) {
    execute(cartId, c -> {
      if (c.add(id)) {
        redisTemplate.opsForValue().set(cartId, c);
        return;
      }
      c.add(productService.findById(id));
    });
  }

  public void sub(String cartId, Long id) {
    execute(cartId, c -> c.sub(id));
  }

  public void remove(String cartId, Long id) {
    execute(cartId, c -> c.remove(id));
  }

  public void clear(String cartId) {
    execute(cartId, Cart::clear);
  }

  public String getCartId(Principal principal, String uuid) {
    if (principal != null) {
      return cartPrefix + principal.getName();
    }
    return cartPrefix + uuid;
  }

  public CartUuid generateUuid() {
    return new CartUuid(UUID.randomUUID().toString());
  }

  public void merge(String userCartId, String uuidCartId) {
    Cart userCart = getCartForCurrentUser(userCartId);
    Cart uuidCart = getCartForCurrentUser(uuidCartId);
    userCart.merge(uuidCart);
    redisTemplate.opsForValue().set(userCartId, userCart);
    redisTemplate.opsForValue().set(uuidCartId, uuidCart);
  }

  private void execute(String cartId, Consumer<Cart> action) {
    Cart cart = getCartForCurrentUser(cartId);
    action.accept(cart);
    redisTemplate.opsForValue().set(cartId, cart);
  }
}
