package ru.geekbrains.webstore.api.exception.response;

import java.util.Date;
import java.util.List;

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

  public List<String> getMessages() {
    return messages;
  }

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
