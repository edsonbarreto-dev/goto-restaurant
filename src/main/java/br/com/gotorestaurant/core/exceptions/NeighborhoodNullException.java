package br.com.gotorestaurant.core.exceptions;

public class NeighborhoodNullException extends RuntimeException {
    public NeighborhoodNullException() {
        super("O nome do bairro precisa ser informado.");
    }
}
