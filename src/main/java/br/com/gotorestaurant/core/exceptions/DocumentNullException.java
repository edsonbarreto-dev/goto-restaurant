package br.com.gotorestaurant.core.exceptions;

public class DocumentNullException extends RuntimeException {
    public DocumentNullException(String subject) {
        super("""
            O número de documento do %s precisa ser preenchido.
        """.formatted(subject));
    }
}
