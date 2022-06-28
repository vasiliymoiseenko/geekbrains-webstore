package ru.geekbrains.webstore.cart.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.webstore.api.dto.ProductDto;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

  private final WebClient productServiceWebClient;

  public ProductDto getProductById(Long productId) {
    ProductDto product = productServiceWebClient.get()
        .uri("/api/v1/products/" + productId)
        .retrieve()
        .bodyToMono(ProductDto.class)
        .block();
    return product;
  }
}
