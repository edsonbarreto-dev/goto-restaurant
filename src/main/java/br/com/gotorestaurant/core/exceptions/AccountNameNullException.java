package br.com.gotorestaurant.core.exceptions;

public class AccountNameNullException extends RuntimeException {
    public AccountNameNullException() {
        super("O nome da conta da rede social precisa ser informado.");
    }
}
