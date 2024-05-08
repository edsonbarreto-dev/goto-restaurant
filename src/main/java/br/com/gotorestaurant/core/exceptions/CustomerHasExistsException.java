package br.com.gotorestaurant.core.exceptions;

public class CustomerHasExistsException extends RuntimeException {
    public CustomerHasExistsException(String document) {
        super("""
        O cliente com o documento número %s já está cadastrado.
        """.formatted(document));
    }
}
