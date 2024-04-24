package br.com.gotorestaurant.core.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RestErrorMessage {
    private HttpStatus status;
    private String message;
}
