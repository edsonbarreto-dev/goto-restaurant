package br.com.gotorestaurant.core.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RestaurantNullException.class)
  public ResponseEntity<RestErrorMessage> eventNullpointerException(RestaurantNullException ex) {
    RestErrorMessage restErrorMessage = new RestErrorMessage();
    restErrorMessage.setStatus(HttpStatus.BAD_REQUEST);
    restErrorMessage.setMessage(ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
  }

  @ExceptionHandler(BrandNullException.class)
  public ResponseEntity<RestErrorMessage> eventBrandNullHandler(BrandNullException ex) {
    RestErrorMessage restErrorMessage = new RestErrorMessage();
    restErrorMessage.setStatus(HttpStatus.NOT_FOUND);
    restErrorMessage.setMessage(ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
  }

  @ExceptionHandler(RestaurantHasExistsException.class)
  public ResponseEntity<RestErrorMessage> eventBrandNullHandler(RestaurantHasExistsException ex) {
    RestErrorMessage restErrorMessage = new RestErrorMessage();
    restErrorMessage.setStatus(HttpStatus.BAD_REQUEST);
    restErrorMessage.setMessage(ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
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
