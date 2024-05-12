package br.com.gotorestaurant.core.exceptions;

public class PathImageBrandNullException extends RuntimeException {
    public PathImageBrandNullException(String subject) {
        super("""
            O caminho da imagem(logo) do tipo %s, precisar ser preenchido.
        """.formatted(subject));
    }
}
