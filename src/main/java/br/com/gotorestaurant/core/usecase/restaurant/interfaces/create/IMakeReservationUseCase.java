package br.com.gotorestaurant.core.usecase.restaurant.interfaces.create;

import br.com.gotorestaurant.core.records.Reservation;

public interface IMakeReservationUseCase {
    void makeReservation(Reservation reservation, String document);
}
