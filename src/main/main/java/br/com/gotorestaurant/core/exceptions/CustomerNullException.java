package br.com.gotorestaurant.core.exceptions;

public class CustomerNullException extends RuntimeException {
    public CustomerNullException() {
        super("As informações do cliente não foram preenchidas.");
    }
}
