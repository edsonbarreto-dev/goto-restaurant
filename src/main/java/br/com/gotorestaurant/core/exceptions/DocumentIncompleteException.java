package br.com.gotorestaurant.core.exceptions;

public class DocumentIncompleteException extends RuntimeException {
    public DocumentIncompleteException(String subject) {
        super("""
            O número do documento do %s precisa ser preenchido corretamente.
        """.formatted(subject));
    }
}
