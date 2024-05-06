package br.com.gotorestaurant.core.usecase.restaurant.implementation.create;

import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.exceptions.ReservationExistException;
import br.com.gotorestaurant.core.exceptions.ReservationNotFoundForCustomerDocument;
import br.com.gotorestaurant.core.exceptions.ReservationNotPossibleForDateAndTableException;
import br.com.gotorestaurant.core.records.Reservation;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.ICustomerPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.IMakeReservationUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindReservationUseCase;
import org.springframework.stereotype.Service;

@Service
public class MakeReservationRestaurantUseCase implements IMakeReservationUseCase {

    private final IRestaurantPresenter presenterRestaurant;
    private final ICustomerPresenter presenterCustomer;
    private final IFindReservationUseCase findReservationUseCase;

    public MakeReservationRestaurantUseCase(
            IRestaurantPresenter restaurant, ICustomerPresenter customer, IFindReservationUseCase findReservationUseCase
    ) {
        this.presenterRestaurant = restaurant;
        this.presenterCustomer = customer;
        this.findReservationUseCase = findReservationUseCase;
    }

    @Override
    public void makeReservation(Reservation reservation, String documentRestaurant) {

        if (this.findReservationUseCase.findByDateAndTable(reservation.date(), reservation.reservedTableNumber())) {
            throw new ReservationNotPossibleForDateAndTableException();
        }

        Restaurant restaurant = presenterRestaurant.findByDocument(documentRestaurant);

        /*
         * Verifica se existe reserva com os parametros informados
         * Se existir, lança exception e interrompe o processo
         * **/
        try {
            this.findReservationUseCase.findByCustomerDocumentAndRestaurantDocumentAndDate(
                reservation.customer().getDocument(), restaurant.document(), reservation.date()
            );

            throw new ReservationExistException();
        } catch (Exception e) {
            presenterRestaurant.makeReservation(reservation, documentRestaurant);
        }
    }
}
