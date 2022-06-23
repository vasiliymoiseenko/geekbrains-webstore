package ru.geekbrains.webstore.api.dto;

import java.util.List;

public class CartDto {

  private List<OrderItemDto> items;
  private Long totalPrice;

  public List<OrderItemDto> getItems() {
    return items;
  }

  public void setItems(List<OrderItemDto> items) {
    this.items = items;
  }

  public Long getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Long totalPrice) {
    this.totalPrice = totalPrice;
  }

  public CartDto() {
  }

  public CartDto(List<OrderItemDto> items, Long totalPrice) {
    this.items = items;
    this.totalPrice = totalPrice;
  }
}
