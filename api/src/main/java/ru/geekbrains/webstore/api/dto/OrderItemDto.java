package ru.geekbrains.webstore.api.dto;

public class OrderItemDto {

  private Long id;
  private Long productId;
  private String productTitle;
  private Integer amount;
  private Long pricePerProduct;
  private Long price;
  private CommentDto commentDto;

  public OrderItemDto() {
  }

  public OrderItemDto(Long id, Long productId, String productTitle, Integer amount, Long pricePerProduct, Long price, CommentDto commentDto) {
    this.id = id;
    this.productId = productId;
    this.productTitle = productTitle;
    this.amount = amount;
    this.pricePerProduct = pricePerProduct;
    this.price = price;
    this.commentDto = commentDto;
  }

  public void changeQuantity(int delta) {
    amount += delta;
    if (amount <= 0) {
      amount = 0;
    }
    price = pricePerProduct * amount;
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

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public Long getPricePerProduct() {
    return pricePerProduct;
  }

  public void setPricePerProduct(Long pricePerProduct) {
    this.pricePerProduct = pricePerProduct;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public CommentDto getCommentDto() {
    return commentDto;
  }

  public void setCommentDto(CommentDto commentDto) {
    this.commentDto = commentDto;
  }
}
