package ru.geekbrains.webstore.api.dto;

import java.math.BigDecimal;

public class OrderItemDto {

  private Long id;
  private Long productId;
  private String productTitle;
  private Integer quantity;
  private BigDecimal pricePerProduct;
  private BigDecimal price;
  private CommentDto commentDto;

  public OrderItemDto() {
  }

  public OrderItemDto(Long id, Long productId, String productTitle, Integer quantity, BigDecimal pricePerProduct, BigDecimal price,
      CommentDto commentDto) {
    this.id = id;
    this.productId = productId;
    this.productTitle = productTitle;
    this.quantity = quantity;
    this.pricePerProduct = pricePerProduct;
    this.price = price;
    this.commentDto = commentDto;
  }

  public void changeQuantity(int delta) {
    quantity += delta;
    if (quantity <= 0) {
      quantity = 0;
    }
    price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductTitle() {
    return productTitle;
  }

  public void setProductTitle(String productTitle) {
    this.productTitle = productTitle;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getPricePerProduct() {
    return pricePerProduct;
  }

  public void setPricePerProduct(BigDecimal pricePerProduct) {
    this.pricePerProduct = pricePerProduct;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public CommentDto getCommentDto() {
    return commentDto;
  }

  public void setCommentDto(CommentDto commentDto) {
    this.commentDto = commentDto;
  }
}
