package br.com.gotorestaurant.core.usecase.restaurant.interfaces;

import br.com.gotorestaurant.core.records.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface IReservationPresenter {
    Long makeReservation(Reservation reservation);
    void updateReservation(Reservation reservation);
    Reservation findById(Long id);
    Reservation findByCustomerDocument(String document);
    boolean isTableReservedInDate(LocalDate date, int reservedTableNumber);
    Reservation findByDocumentAndRestaurantDocumentAndDate(String document, String restaurantDocument, LocalDate date);
    List<Reservation> findAll();
}
