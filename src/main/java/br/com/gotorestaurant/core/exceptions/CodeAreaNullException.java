package br.com.gotorestaurant.core.exceptions;

public class CodeAreaNullException extends RuntimeException {
    public CodeAreaNullException() {
        super("O código da região, para formação do telefone, precisa ser informado.");
    }
}
