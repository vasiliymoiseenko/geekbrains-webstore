package ru.geekbrains.webstore.api.dto;

import java.util.List;

public class OrderDto {

  private Long id;
  private String username;
  private String phone;
  private String address;
  private Long Price;
  private List<OrderItemDto> items;

  public OrderDto() {
  }

  public OrderDto(Long id, String username, String phone, String address, Long price, List<OrderItemDto> items) {
    this.id = id;
    this.username = username;
    this.phone = phone;
    this.address = address;
    Price = price;
    this.items = items;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Long getPrice() {
    return Price;
  }

  public void setPrice(Long price) {
    Price = price;
  }

  public List<OrderItemDto> getItems() {
    return items;
  }

  public void setItems(List<OrderItemDto> items) {
    this.items = items;
  }
}
