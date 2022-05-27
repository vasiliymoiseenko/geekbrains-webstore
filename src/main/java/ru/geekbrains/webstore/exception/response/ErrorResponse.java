package ru.geekbrains.webstore.exception.response;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class ErrorResponse {

  private List<String> messages;
  private Date date;


  public ErrorResponse(List<String> messages) {
    this.messages = messages;
    this.date = new Date();
  }

  public ErrorResponse(String message) {
    this(List.of(message));
  }

  public ErrorResponse(String... messages) {
    this(List.of(messages));
  }
}
