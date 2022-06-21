package ru.geekbrains.webstore.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {

  private Long id;
  private String username;
  private Long orderItemId;
  private String text;
  private LocalDateTime updatedAt;
}
