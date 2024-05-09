package br.com.gotorestaurant.core.usecase.restaurant.interfaces;

import br.com.gotorestaurant.core.records.Reservation;

import java.util.List;

public interface IReservationService {
    void makeReservation(Reservation reservation, String restaurantDocument);
    List<Reservation> reservations();
}
