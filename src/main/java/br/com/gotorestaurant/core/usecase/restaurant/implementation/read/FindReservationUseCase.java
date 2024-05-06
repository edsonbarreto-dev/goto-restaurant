package br.com.gotorestaurant.core.usecase.restaurant.implementation.read;

import br.com.gotorestaurant.core.records.Reservation;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IReservationPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindReservationUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FindReservationUseCase implements IFindReservationUseCase {

    private final IReservationPresenter reservationPresenter;

    public FindReservationUseCase(IReservationPresenter reservationPresenter) {
        this.reservationPresenter = reservationPresenter;
    }

    @Override
    public Reservation findById(Long id) {
        return this.reservationPresenter.findById(id);
    }

    @Override
    public Reservation findByCustomerDocumentAndRestaurantDocumentAndDate(
        String document, String restaurantDocument, LocalDate date
    ) {
        return this.reservationPresenter.findByDocumentAndRestaurantDocumentAndDate(document, restaurantDocument, date);
    }

    @Override
    public boolean findByDateAndTable(LocalDate date, int reservedTableNumber) {
        return this.reservationPresenter.isTableReservedInDate(date, reservedTableNumber);
    }
}
