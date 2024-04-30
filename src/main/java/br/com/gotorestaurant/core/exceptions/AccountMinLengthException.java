package br.com.gotorestaurant.core.exceptions;

public class AccountMinLengthException extends RuntimeException {
    public AccountMinLengthException() {
        super("O nome da conta da rede social precisa ser informado.");
    }
}
