package br.com.gotorestaurant.core.exceptions;

public class RestaurantNullException extends RuntimeException {
    public RestaurantNullException() {
        super("Os dados do restaurantes precisam ser informados.");
    }
}
