package ru.geekbrains.webstore.service;

import java.security.Principal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.cart.Cart;
import ru.geekbrains.webstore.cart.CartUuid;

@Service
@RequiredArgsConstructor
public class CartService {

  public static final String CART_PREFIX = "WEBSTORE_CART_";
  private final ProductService productService;
  private final RedisTemplate<String, Object> redisTemplate;

  public Cart getCartForCurrentUser(String cartId) {
    if (!redisTemplate.hasKey(cartId)) {
      redisTemplate.opsForValue().set(cartId, new Cart());
    }
    Cart cart = (Cart) redisTemplate.opsForValue().get(cartId);
    return cart;
  }

  public void updateCart(String cartId, Cart cart) {
    redisTemplate.opsForValue().set(cartId, cart);
  }

  public void add(String cartId, Long id) {
    Cart cart = getCartForCurrentUser(cartId);
    if (cart.add(id)) {
      updateCart(cartId, cart);
      return;
    }
    cart.add(productService.findById(id));
    updateCart(cartId, cart);
  }

  public void sub(String cartId, Long id) {
    Cart cart = getCartForCurrentUser(cartId);
    cart.sub(id);
    updateCart(cartId, cart);
  }

  public void remove(String cartId, Long id) {
    Cart cart = getCartForCurrentUser(cartId);
    cart.remove(id);
    updateCart(cartId, cart);
  }

  public void clear(String cartId) {
    Cart cart = getCartForCurrentUser(cartId);
    cart.clear();
    updateCart(cartId, cart);
  }

  public String getCartId(Principal principal, String uuid) {
    if (principal != null) {
      return CART_PREFIX + principal.getName();
    }
    return CART_PREFIX + uuid;
  }

  public CartUuid generateUuid() {
    return new CartUuid(UUID.randomUUID().toString());
  }

  public void merge(Principal principal, String uuid) {
    String userCartId = CART_PREFIX + principal.getName();
    String uuidCartId = CART_PREFIX + uuid;

    Cart userCart = getCartForCurrentUser(userCartId);
    Cart uuidCart = getCartForCurrentUser(uuidCartId);
    userCart.merge(uuidCart);

    updateCart(userCartId, userCart);
    updateCart(uuidCartId, uuidCart);
  }
}
