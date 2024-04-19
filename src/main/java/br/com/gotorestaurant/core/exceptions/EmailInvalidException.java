package br.com.gotorestaurant.core.exceptions;

public class EmailInvalidException extends RuntimeException {
    public EmailInvalidException(String subject) {
        super("""
            O email de %s informado é inválido.
        """.formatted(subject));
    }
}
