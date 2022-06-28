package ru.geekbrains.webstore.api.dto;

import java.time.LocalDateTime;

public class CommentDto {

  private Long id;
  private String username;
  private Long orderItemId;
  private String text;
  private LocalDateTime updatedAt;

  public CommentDto() {
  }

  public CommentDto(Long id, String username, Long orderItemId, String text, LocalDateTime updatedAt) {
    this.id = id;
    this.username = username;
    this.orderItemId = orderItemId;
    this.text = text;
    this.updatedAt = updatedAt;
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

  public Long getOrderItemId() {
    return orderItemId;
  }

  public void setOrderItemId(Long orderItemId) {
    this.orderItemId = orderItemId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
