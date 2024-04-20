package br.com.gotorestaurant.core.usecase.restaurant.interfaces;

import br.com.gotorestaurant.core.entity.Restaurant;

import java.util.List;
import java.util.UUID;



public interface IRestaurantPresenter {
    UUID createRestaurant(Restaurant restaurantEntity);
    void updateRestaurant(UUID uuid, Restaurant restaurantEntity);
    Restaurant findByDocument(String document);
    List<Restaurant> findAll();
    List<Restaurant> findByName(String name);
    List<Restaurant> findByCapacity(int capacity);
    List<Restaurant> findByReservationExists();
}
