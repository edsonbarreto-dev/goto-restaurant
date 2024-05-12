package br.com.gotorestaurant.core.exceptions;

public class BuildingCapacityException extends RuntimeException {
    public BuildingCapacityException() {
        super("A capacidade máxima do estabelecimento não foi definida.");
    }
}
