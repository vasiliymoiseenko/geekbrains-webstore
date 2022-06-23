package ru.geekbrains.webstore.cart.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.webstore.api.dto.ProductDto;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

  private final RestTemplate restTemplate;

  @Value("${integration.product-service.url}")
  private String productServiceUrl;

  public ProductDto getProductById(Long productId) {
    return restTemplate.getForObject(productServiceUrl + "/api/v1/products/" + productId, ProductDto.class);
  }
}
