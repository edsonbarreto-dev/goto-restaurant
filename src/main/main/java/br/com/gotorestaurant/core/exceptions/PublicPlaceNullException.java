package br.com.gotorestaurant.core.exceptions;

public class PublicPlaceNullException extends RuntimeException {
    public PublicPlaceNullException() {
        super("A informações específicas do endereço precisam ser informadas corretamente.");
    }
}
