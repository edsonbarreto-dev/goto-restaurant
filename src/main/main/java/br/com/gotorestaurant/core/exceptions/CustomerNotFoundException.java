package br.com.gotorestaurant.core.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super("O cliente informado não foi encontrado.");
    }
}
