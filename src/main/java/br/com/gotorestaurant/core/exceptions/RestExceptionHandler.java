package br.com.gotorestaurant.core.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RestaurantNullException.class)
  public ResponseEntity<RestErrorMessage> eventNullpointerException(RestaurantNullException ex) {
    new RestErrorMessage().setStatus(ex.).setMessage(ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> eventNotFoundHandler(EntityNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  private record BeanValidationErros(String field, String message) {
    public BeanValidationErros(FieldError error) {
      this(error.getField(), error.getDefaultMessage());
    }
  }
}
