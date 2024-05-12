package br.com.gotorestaurant.core.exceptions;

public class CapacityPlaceException extends RuntimeException {
    public CapacityPlaceException() {
        super("O número de pessoas para o local é menor que o exigido.");
    }
}
