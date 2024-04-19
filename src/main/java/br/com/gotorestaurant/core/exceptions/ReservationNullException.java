package br.com.gotorestaurant.core.exceptions;

public class ReservationNullException extends RuntimeException {
    public ReservationNullException() {
        super("Os dados para registrar a reserva não foram informados.");
    }
}
