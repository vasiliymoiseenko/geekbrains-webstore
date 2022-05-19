package ru.geekbrains.webstore.exceptions;

import java.util.List;
import lombok.Getter;

public class DataValidationException extends RuntimeException {

  @Getter
  private List<String> messages;

  public DataValidationException(List<String> messages) {
    this.messages = messages;
  }

}
