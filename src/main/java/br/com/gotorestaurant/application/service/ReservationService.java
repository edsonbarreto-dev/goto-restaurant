package br.com.gotorestaurant.application.service;

import br.com.gotorestaurant.application.repository.ICustomerRepository;
import br.com.gotorestaurant.application.repository.IReservationRepository;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.records.Reservation;
import br.com.gotorestaurant.core.usecase.restaurant.implementation.read.FindRestaurantUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IReservationService;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.IMakeReservationUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindReservationUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindRestaurantUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private IMakeReservationUseCase makeReservationUseCase;

    @Autowired
    private IFindReservationUseCase findReservationUseCase;

    @Autowired
    private IFindRestaurantUseCase findRestaurantUseCase;

    @Autowired
    private IReservationRepository reservationRepository;

    public ReservationService(IReservationRepository reservationRepository, IMakeReservationUseCase makeReservationUseCase,
                              IFindReservationUseCase findReservationUseCase,
                              IFindRestaurantUseCase findRestaurantUseCase) {
        this.findReservationUseCase = findReservationUseCase;
        this.makeReservationUseCase = makeReservationUseCase;
        this.reservationRepository = reservationRepository;
        this.findRestaurantUseCase = findRestaurantUseCase;
    }

    @Override
    public void makeReservation(Reservation reservation, String restaurantDocument) {
        Restaurant restaurant = this.findRestaurantUseCase.findByDocument(restaurantDocument);
        this.makeReservationUseCase.makeReservation(reservation, restaurant);
    }

    @Override
    public List<Reservation> reservations() {
        return this.findReservationUseCase.findAll();
    }

}
