package ru.geekbrains.webstore.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.geekbrains.webstore.exception.DataValidationException;
import ru.geekbrains.webstore.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.exception.response.ErrorResponse;

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
