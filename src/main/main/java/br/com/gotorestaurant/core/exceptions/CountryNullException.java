package br.com.gotorestaurant.core.exceptions;

public class CountryNullException extends RuntimeException {
    public CountryNullException() {
        super("O nome do país precisa ser informado.");
    }
}
