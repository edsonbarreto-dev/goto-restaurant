package br.com.gotorestaurant.core.exceptions;

public class StateNullException extends RuntimeException {
    public StateNullException() {
        super("O nome do estado precisa ser informado.");
    }
}
