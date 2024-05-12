package br.com.gotorestaurant.core.exceptions;

public class ReservationNotFoundForCustomerDocument extends RuntimeException {
    public ReservationNotFoundForCustomerDocument() {
        super("Não localizei reservas para o cliente informado.");
    }
}
