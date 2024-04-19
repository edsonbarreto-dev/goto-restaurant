package br.com.gotorestaurant.core.exceptions;

public class MinItemListPhoneNumberException extends RuntimeException {
    public MinItemListPhoneNumberException(String subject, int minSize) {
        super("""
            A lista de telefones de %s deve conter, no mínimo, %s.
        """.formatted(subject, minSize));
    }
}
