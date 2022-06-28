package ru.geekbrains.webstore.api.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {

  private Long id;
  private String title;
  private BigDecimal price;
  private String categoryTitle;

  private List<CommentDto> comments;

  public ProductDto() {
  }

  public ProductDto(Long id, String title, BigDecimal price, String categoryTitle, List<CommentDto> comments) {
    this.id = id;
    this.title = title;
    this.price = price;
    this.categoryTitle = categoryTitle;
    this.comments = comments;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getCategoryTitle() {
    return categoryTitle;
  }

  public void setCategoryTitle(String categoryTitle) {
    this.categoryTitle = categoryTitle;
  }

  public List<CommentDto> getComments() {
    return comments;
  }

  public void setComments(List<CommentDto> comments) {
    this.comments = comments;
  }
}
