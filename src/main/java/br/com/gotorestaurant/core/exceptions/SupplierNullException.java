package br.com.gotorestaurant.core.exceptions;

public class SupplierNullException extends RuntimeException {
    public SupplierNullException() {
        super("As informações do fornecedor não foram preenchidas.");
    }
}
