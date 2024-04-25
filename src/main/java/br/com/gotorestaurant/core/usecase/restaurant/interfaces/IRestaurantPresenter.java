package br.com.gotorestaurant.core.usecase.restaurant.interfaces;

import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.records.Reservation;

import java.util.List;

public interface IRestaurantPresenter {
    Long createRestaurant(Restaurant restaurantEntity);
    void updateRestaurant(Long id, Restaurant restaurantEntity);
    Restaurant findById(Long restaurantId);
    Restaurant findByDocument(String document);
    List<Restaurant> findAll();
    List<Restaurant> findByName(String name);
    List<Restaurant> findByCapacity(int capacity);
    List<Restaurant> findByReservationExists();
    boolean makeReservation(Reservation reservation, Long restaurantId);
}
