package ru.geekbrains.webstore.exception;

import java.util.List;
import lombok.Getter;

public class DataValidationException extends RuntimeException {

  @Getter
  private final List<String> messages;

  public DataValidationException(List<String> messages) {
    this.messages = messages;
  }

}
