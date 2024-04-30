package br.com.gotorestaurant.application.presenter.restaurant;

import br.com.gotorestaurant.application.repository.ICustomerRepository;
import br.com.gotorestaurant.application.repository.IReservationRepository;
import br.com.gotorestaurant.application.repository.IRestaurantRepository;
import br.com.gotorestaurant.application.repository.entity.CustomerEntity;
import br.com.gotorestaurant.application.repository.entity.ReservationEntity;
import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import br.com.gotorestaurant.application.shared.CustomerMapper;
import br.com.gotorestaurant.application.shared.ReservationMapper;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.application.shared.RestaurantMapper;
import br.com.gotorestaurant.core.exceptions.CustomerNotFoundException;
import br.com.gotorestaurant.core.exceptions.RestaurantNotFoundException;
import br.com.gotorestaurant.core.exceptions.RestaurantNotFoundForReservationException;
import br.com.gotorestaurant.core.records.Reservation;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantPresenter implements IRestaurantPresenter {

    @Autowired
    private IRestaurantRepository repository;

    @Autowired
    private IReservationRepository repositoryReservation;

    @Autowired
    private ICustomerRepository customerReservation;

    @Transactional
    @Override
    public Long createRestaurant(Restaurant restaurantEntity) {
        RestaurantEntity add = RestaurantMapper.toRestaurantEntity(restaurantEntity);
        RestaurantEntity entity = this.repository.save(add);
        return entity.getId();
    }

    @Override
    public void updateRestaurant(Long id, Restaurant restaurant) {
        RestaurantEntity restaurantEntity = this.repository.findByDocument(restaurant.document())
                .orElseThrow(RestaurantNotFoundException::new);
        this.repository.save(restaurantEntity);
    }

    @Override
    public Restaurant findById(Long restaurantId) {
        RestaurantEntity restaurantEntity = this.repository.findById(restaurantId)
                .orElseThrow(RestaurantNotFoundException::new);
        return RestaurantMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public Restaurant findByDocument(String document) {
        RestaurantEntity restaurantEntity = this.repository.findByDocument(document)
                .orElseThrow(RestaurantNotFoundException::new);
        return RestaurantMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public List<Restaurant> findAll() {
        List<Restaurant> restaurants = new ArrayList<>();
        Iterable<RestaurantEntity> all = this.repository.findAll();
        all.forEach(r -> restaurants.add(RestaurantMapper.toRestaurant(r)));
        return restaurants;
    }

    @Override
    public List<Restaurant> findByName(String name) {
        return new ArrayList<>();
    }

    @Override
    public List<Restaurant> findByCapacity(int capacity) {
        return new ArrayList<>();
    }

    @Override
    public List<Restaurant> findByReservationExists() {
        return new ArrayList<>();
    }

    @Override
    public void makeReservation(Reservation reservation, String documentRestaurant) {
        RestaurantEntity restaurantEntity = this.repository.findByDocument(documentRestaurant)
                .orElseThrow(RestaurantNotFoundForReservationException::new);

        CustomerEntity customerSaved;
        try {
            customerSaved = this.customerReservation.findByDocument(reservation.customer().getDocument())
                    .orElseThrow(CustomerNotFoundException::new);
        } catch (CustomerNotFoundException e) {
            customerSaved = this.customerReservation.save(CustomerMapper.toCustomerEntity(reservation.customer()));
        }


        ReservationEntity reservationEntity = ReservationMapper.toReservationEntity(reservation);
        reservationEntity.setCustomerEntity(customerSaved);
        reservationEntity.setRestaurantEntity(restaurantEntity);

        ReservationEntity reservationSaved = this.repositoryReservation.save(reservationEntity);

        List<ReservationEntity> reservations = restaurantEntity.getReservationEntity();
        reservations.add(reservationSaved);

        restaurantEntity.setReservationEntity(reservations);

        this.repository.makeReservation(restaurantEntity);
    }
}
