package br.com.gotorestaurant.core.usecase.restaurant.interfaces;

import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.records.Reservation;

import java.util.List;

public interface IRestaurantPresenter {
    void updateRestaurant(Restaurant restaurant);
    void updateCustomer(Customer customer, String restaurantDocument);
    void makeReservation(Reservation reservation, String document);
    Long createRestaurant(Restaurant restaurantEntity);
    Restaurant findById(Long restaurantId);
    Restaurant findByDocument(String document);
    List<Restaurant> findAll();
    List<Restaurant> findByName(String name);
    List<Restaurant> findByCapacity(int capacity);
    List<Restaurant> findByReservationExists();
}
