package br.com.gotorestaurant.core.exceptions;

public class ReservationNotPossibleForDateAndTableException extends RuntimeException {
    public ReservationNotPossibleForDateAndTableException() {
        super("A mesa indicada já está reservada na data informada.");
    }
}
