package br.com.gotorestaurant.core.exceptions;

public class EmailInvalidException extends RuntimeException {
    public EmailInvalidException(String subject) {
        super("""
        O email do %s é inválido.
        """.formatted(subject));
    }
}
