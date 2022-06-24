package ru.geekbrains.webstore.cart.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.Getter;
import ru.geekbrains.webstore.api.dto.OrderItemDto;
import ru.geekbrains.webstore.api.dto.ProductDto;

@Getter
public class Cart {

  private final List<OrderItemDto> items = new ArrayList<>();
  private Long totalPrice = 0L;

  public void add(ProductDto product) {
    for (OrderItemDto item : items) {
      if (item.getProductId().equals(product.getId())) {
        item.changeQuantity(1);
        recalculate();
        return;
      }
    }
    items.add(new OrderItemDto(null, product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice(), null));
    recalculate();
  }

  public void sub(Long productId) {
    Iterator<OrderItemDto> iter = items.iterator();
    while (iter.hasNext()) {
      OrderItemDto item = iter.next();
      if (item.getProductId().equals(productId)) {
        item.changeQuantity(-1);
        recalculate();
        if (item.getAmount() <= 0) {
          iter.remove();
        }
        return;
      }
    }
  }

  public void remove(Long productId) {
    items.removeIf(i -> i.getProductId().equals(productId));
    recalculate();
  }

  public void clear() {
    items.clear();
    totalPrice = 0L;
  }

  public void merge(Cart another) {
    for (OrderItemDto anotherItem : another.items) {
      boolean merged = false;
      for (OrderItemDto item : items) {
        if (item.getProductId().equals(anotherItem.getProductId())) {
          item.changeQuantity(anotherItem.getAmount());
          merged = true;
          break;
        }
      }
      if (!merged) {
        items.add(anotherItem);
      }
    }
    recalculate();
    another.clear();
  }

  private void recalculate() {
    totalPrice = 0L;
    items.forEach(i -> totalPrice += i.getPrice());
  }
}
