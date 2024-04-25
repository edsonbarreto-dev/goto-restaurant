package br.com.gotorestaurant.core.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException() {
        super("Não localizei o restaurante com o documento informado.");
    }
}
