package ru.geekbrains.webstore.dto;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class ProductDto {

  private Long id;

  @NotNull(message = "Product must have a title")
  @Length(min = 3, max = 255, message = "Title length must be 3-255 characters")
  private String title;

  @NotNull(message = "Product must have a price")
  @Min(value = 1, message = "Price must be greater than 0")
  private Long price;

  private List<CommentDto> comments;
}
