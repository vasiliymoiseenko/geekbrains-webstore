package ru.geekbrains.webstore.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class CategoryDto {

  private Long id;

  @NotNull(message = "Product must have a title")
  @Length(min = 3, max = 255, message = "Title length must be 3-255 characters")
  private String title;
  
  private List<ProductDto> products;
}
