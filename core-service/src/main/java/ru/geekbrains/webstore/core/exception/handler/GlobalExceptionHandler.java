package ru.geekbrains.webstore.core.exception.handler;

import ru.geekbrains.webstore.core.exception.DataValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.geekbrains.webstore.api.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.api.exception.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e) {
    return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<?> catchDataValidationException(DataValidationException e) {
    return new ResponseEntity<>(new ErrorResponse(e.getMessages()), HttpStatus.BAD_REQUEST);
  }
}
