package br.com.gotorestaurant.core.exceptions;

public class AddressNullException extends RuntimeException {
    public AddressNullException(String subject) {
        super("""
            As informações sobre o endereço do %s não foram preenchidas.
        """.formatted(subject));
    }
}
