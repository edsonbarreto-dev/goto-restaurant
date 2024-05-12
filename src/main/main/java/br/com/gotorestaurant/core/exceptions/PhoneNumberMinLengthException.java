package br.com.gotorestaurant.core.exceptions;

public class PhoneNumberMinLengthException extends RuntimeException {
    public PhoneNumberMinLengthException() {
        super("O número do telefone precisa ter, no mínimo, nove dígitos.");
    }
}
