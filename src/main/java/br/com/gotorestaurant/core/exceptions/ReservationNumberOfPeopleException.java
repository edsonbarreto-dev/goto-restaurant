package br.com.gotorestaurant.core.exceptions;

public class ReservationNumberOfPeopleException extends RuntimeException {
    public ReservationNumberOfPeopleException(int total) {
        super("""
            O número mínimo de pessoas para reserva é de %s.
        """.formatted(total)
        );
    }
}
