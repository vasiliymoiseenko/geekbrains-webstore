package ru.geekbrains.webstore.api.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

  private Long id;
  private String username;
  private String phone;
  private String address;
  private BigDecimal price;
  private String status;
  private List<OrderItemDto> items;

  public OrderDto() {
  }

  public OrderDto(Long id, String username, String phone, String address, BigDecimal price, String status, List<OrderItemDto> items) {
    this.id = id;
    this.username = username;
    this.phone = phone;
    this.address = address;
    this.price = price;
    this.status = status;
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

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<OrderItemDto> getItems() {
    return items;
  }

  public void setItems(List<OrderItemDto> items) {
    this.items = items;
  }
}