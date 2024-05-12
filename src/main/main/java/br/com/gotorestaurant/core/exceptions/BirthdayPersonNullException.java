package br.com.gotorestaurant.core.exceptions;

public class BirthdayPersonNullException extends RuntimeException {
    public BirthdayPersonNullException() {
        super("O nome do aniversariante deve ser informado.");
    }
}
