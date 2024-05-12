package br.com.gotorestaurant.core.exceptions;

public class NameNullException extends RuntimeException {
    public NameNullException(String subject) {
        super("""
            O nome da %s precisa ser preenchido.
        """.formatted(subject));
    }
}
