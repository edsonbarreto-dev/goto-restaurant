package br.com.gotorestaurant.core.exceptions;

public class PartnerNullException extends RuntimeException {
    public PartnerNullException() {
        super("As informações do parceiro não foram preenchidas.");
    }
}
