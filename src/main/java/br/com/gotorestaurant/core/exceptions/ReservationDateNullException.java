package br.com.gotorestaurant.core.exceptions;

public class ReservationDateNullException extends RuntimeException {
    public ReservationDateNullException() {
        super("A data para reserva dever ser informada.");
    }
}
