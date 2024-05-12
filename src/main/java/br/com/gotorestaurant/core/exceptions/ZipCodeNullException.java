package br.com.gotorestaurant.core.exceptions;

public class ZipCodeNullException extends RuntimeException {
    public ZipCodeNullException() {
        super("O código postal(CEP) precisa ser informado.");
    }
}
