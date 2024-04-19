package br.com.gotorestaurant.core.exceptions;

public class RestaurantHasExistsException extends RuntimeException {
    public RestaurantHasExistsException() {
        super("O restaurante cadastrado!");
    }
}
