package br.com.gotorestaurant.core.exceptions;

public class FullUrlPlatformException extends RuntimeException {
    public FullUrlPlatformException() {
        super("A URL para a rede social parece ser inválida.");
    }
}
