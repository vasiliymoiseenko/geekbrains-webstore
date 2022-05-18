package ru.geekbrains.webstore.exceptions;

import java.util.Date;
import lombok.Data;

@Data
public class ErrorResponse {

  private String message;
  private Date date;

  public ErrorResponse(String message) {
    this.message = message;
    this.date = new Date();
  }
}
