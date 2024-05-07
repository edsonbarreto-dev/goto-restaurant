package br.com.gotorestaurant.core.usecase.restaurant.interfaces;

import br.com.gotorestaurant.core.records.Reservation;

public interface IReservationService {
    void makeReservation(Reservation reservation, String restaurantDocument);
}
