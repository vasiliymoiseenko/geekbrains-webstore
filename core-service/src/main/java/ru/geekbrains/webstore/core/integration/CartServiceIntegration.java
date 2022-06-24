package ru.geekbrains.webstore.core.integration;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.webstore.api.dto.CartDto;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

  private final WebClient cartServiceWebClient;

  public void clearUserCart(String username) {
    cartServiceWebClient.get()
        .uri("/api/v1/cart/0/clear")
        .header("username", username)
        .retrieve()
        .toBodilessEntity()
        .block();
  }

  public CartDto getUserCartDto(String username) {
    CartDto cart = cartServiceWebClient.get()
        .uri("/api/v1/cart/0")
        .header("username", username)
        .retrieve()
        .bodyToMono(CartDto.class)
        .block();
    return cart;
  }
}
