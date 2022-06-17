package ru.geekbrains.webstore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.webstore.cart.Cart;
import ru.geekbrains.webstore.entity.Category;
import ru.geekbrains.webstore.entity.Product;
import ru.geekbrains.webstore.service.CartService;
import ru.geekbrains.webstore.service.ProductService;

@SpringBootTest
public class CartTest {

  public static final String CART = "TEST_CART";
  public static final String CART_OTHER = "TEST_CART_OTHER";
  @Autowired
  private CartService cartService;

  @MockBean
  private ProductService productService;

  @BeforeEach
  public void initCart() {
    cartService.clear(CART);
    cartService.clear(CART_OTHER);
  }

  @Test
  public void addToCartTest() {
    Product product = new Product();
    product.setId(1L);
    product.setTitle("Phone");
    product.setPrice(100L);

    Category category = new Category();
    category.setTitle("Electronics");
    product.setCategory(category);

    Mockito.doReturn(product).when(productService).findById(1L);

    cartService.add(CART, 1L);
    cartService.add(CART, 1L);
    cartService.add(CART, 1L);

    Mockito.verify(productService, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
    Assertions.assertEquals(1, cartService.getCartForCurrentUser(CART).getItems().size());
    Assertions.assertEquals(3, cartService.getCartForCurrentUser(CART).getItems().get(0).getAmount());
    Assertions.assertEquals(300L, cartService.getCartForCurrentUser(CART).getPrice());
  }

  @Test
  public void subFromCartTest() {
    Product product = new Product();
    product.setId(1L);
    product.setTitle("Phone");
    product.setPrice(100L);

    Category category = new Category();
    category.setTitle("Electronics");
    product.setCategory(category);

    Mockito.doReturn(product).when(productService).findById(1L);

    cartService.add(CART, 1L);
    cartService.add(CART, 1L);
    cartService.sub(CART, 1L);

    Mockito.verify(productService, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
    Assertions.assertEquals(1, cartService.getCartForCurrentUser(CART).getItems().size());
    Assertions.assertEquals(1, cartService.getCartForCurrentUser(CART).getItems().get(0).getAmount());
    Assertions.assertEquals(100L, cartService.getCartForCurrentUser(CART).getPrice());

    cartService.sub(CART, 1L);

    Mockito.verify(productService, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
    Assertions.assertEquals(0, cartService.getCartForCurrentUser(CART).getItems().size());
    Assertions.assertEquals(0L, cartService.getCartForCurrentUser(CART).getPrice());
  }

  @Test
  public void removeFromCartTest() {
    Product product = new Product();
    product.setId(1L);
    product.setTitle("Phone");
    product.setPrice(100L);

    Category category = new Category();
    category.setTitle("Electronics");
    product.setCategory(category);

    Mockito.doReturn(product).when(productService).findById(1L);

    cartService.add(CART, 1L);
    cartService.add(CART, 1L);
    cartService.add(CART, 1L);
    cartService.remove(CART, 1L);

    Mockito.verify(productService, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
    Assertions.assertEquals(0, cartService.getCartForCurrentUser(CART).getItems().size());
    Assertions.assertEquals(0L, cartService.getCartForCurrentUser(CART).getPrice());
  }

  @Test
  public void mergeCartsTest() {
    Product product1 = new Product();
    product1.setId(1L);
    product1.setTitle("Phone");
    product1.setPrice(100L);

    Product product2 = new Product();
    product2.setId(2L);
    product2.setTitle("Notebook");
    product2.setPrice(200L);

    Category category = new Category();
    category.setTitle("Electronics");
    product1.setCategory(category);
    product2.setCategory(category);

    Mockito.doReturn(product1).when(productService).findById(1L);
    Mockito.doReturn(product2).when(productService).findById(2L);

    cartService.add(CART, 1L);
    cartService.add(CART, 1L);

    cartService.add(CART_OTHER, 1L);
    cartService.add(CART_OTHER, 2L);
    cartService.add(CART_OTHER, 2L);

    Cart cart = cartService.getCartForCurrentUser(CART);
    Cart cart2 = cartService.getCartForCurrentUser(CART_OTHER);

    cartService.merge(CART, CART_OTHER);

    Mockito.verify(productService, Mockito.times(2)).findById(ArgumentMatchers.eq(1L));
    Mockito.verify(productService, Mockito.times(1)).findById(ArgumentMatchers.eq(2L));
    Assertions.assertEquals(2, cartService.getCartForCurrentUser(CART).getItems().size());
    Assertions.assertEquals(3, cartService.getCartForCurrentUser(CART).getItems().get(0).getAmount());
    Assertions.assertEquals(2, cartService.getCartForCurrentUser(CART).getItems().get(1).getAmount());
    Assertions.assertEquals(700L, cartService.getCartForCurrentUser(CART).getPrice());
  }
}