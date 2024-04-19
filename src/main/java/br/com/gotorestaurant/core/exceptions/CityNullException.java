package br.com.gotorestaurant.core.exceptions;

public class CityNullException extends RuntimeException {
    public CityNullException() {
        super("O nome da cidade precisa ser informada.");
    }
}
