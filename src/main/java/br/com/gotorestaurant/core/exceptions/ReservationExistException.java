package br.com.gotorestaurant.core.exceptions;

public class ReservationExistException extends RuntimeException {
    public ReservationExistException() {
        super("Já existe um reserva com este cliente.");
    }
}
