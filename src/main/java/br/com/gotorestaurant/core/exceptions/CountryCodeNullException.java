package br.com.gotorestaurant.core.exceptions;

public class CountryCodeNullException extends RuntimeException {
    public CountryCodeNullException() {
        super("O código do país, para formação do telefone, precisa ser informado.");
    }
}
