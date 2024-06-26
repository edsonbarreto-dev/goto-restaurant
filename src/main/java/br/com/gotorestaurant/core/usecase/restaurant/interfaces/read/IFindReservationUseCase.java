package br.com.gotorestaurant.core.usecase.restaurant.interfaces.read;

import br.com.gotorestaurant.core.records.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface IFindReservationUseCase {
    List<Reservation> findAll();
    Reservation findById(Long id);
    Reservation findByCustomerDocumentAndRestaurantDocumentAndDate(String documentCustomer, String restaurantDocument, LocalDate date);
    boolean findByDateAndTable(LocalDate date, int reservedTableNumber);
}
