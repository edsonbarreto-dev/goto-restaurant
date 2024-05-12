package br.com.gotorestaurant.core.exceptions;

public class BrandNullException extends RuntimeException {
    public BrandNullException() {
        super("As configurações de Marca são inválidas.");
    }
}
