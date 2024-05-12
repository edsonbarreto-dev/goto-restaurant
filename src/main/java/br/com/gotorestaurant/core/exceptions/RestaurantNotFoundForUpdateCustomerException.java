package br.com.gotorestaurant.core.exceptions;

public class RestaurantNotFoundForUpdateCustomerException extends RuntimeException {
    public RestaurantNotFoundForUpdateCustomerException() {
        super("O número de documento do restaurante informado não foi localizado. Impossível atualizar o cliente.");
    }
}
