package br.com.gotorestaurant.core.exceptions;

public class RestaurantNotFoundForReservationException extends RuntimeException {
    public RestaurantNotFoundForReservationException() {
        super("Não é possível realizar a reserva sem informar um restaurante válido!");
    }
}
