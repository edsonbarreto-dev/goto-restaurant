package br.com.gotorestaurant.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity noContent() {
    return ResponseEntity.status(400).build();
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity notFound(MethodArgumentNotValidException ex) {
    var erros = ex.getFieldErrors();
    return ResponseEntity.badRequest().body(erros.stream().map(BeanValidationErros::new).toList());
  }
  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
  public ResponseEntity notFound(SQLIntegrityConstraintViolationException ex) {
    String message = ex.getMessage();
    return ResponseEntity.badRequest().body(new BeanValidationErros("query", message));
  }
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity notFound(ValidationException ex) {
    String message = ex.getMessage();
    return ResponseEntity.badRequest().body(new BeanValidationErros("query", message));
  }

  private record BeanValidationErros(String field, String message) {
    public BeanValidationErros(FieldError error) {
      this(error.getField(), error.getDefaultMessage());
    }
  }
}
