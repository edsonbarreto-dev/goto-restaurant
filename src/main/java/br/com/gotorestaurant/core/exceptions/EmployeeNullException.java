package br.com.gotorestaurant.core.exceptions;

public class EmployeeNullException extends RuntimeException {
    public EmployeeNullException() {
        super("As informações do funcionário não foram preenchidas.");
    }
}
