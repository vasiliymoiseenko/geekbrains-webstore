package ru.geekbrains.webstore.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.webstore.entity.Product;

@Data
@NoArgsConstructor
public class ProductDto {

  @Getter
  private Long id;

  @NotNull(message = "Product must have a title")
  @Length(min = 3, max = 255, message = "Title length must be 3-255 characters")
  private String title;

  @NotNull(message = "Product must have a price")
  @Min(value = 1, message = "Price must be greater than 0")
  private Long price;
}
