package br.com.gotorestaurant.core.usecase.restaurant.implementation.read;

import br.com.gotorestaurant.core.records.Reservation;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.IMakeReservationUseCase;
import org.springframework.stereotype.Service;

@Service
public class MakeReservationRestaurantUseCase implements IMakeReservationUseCase {

    private final IRestaurantPresenter presenter;

    public MakeReservationRestaurantUseCase(IRestaurantPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void makeReservation(Reservation reservation, String document) {
        presenter.makeReservation(reservation, document);
    }
}
