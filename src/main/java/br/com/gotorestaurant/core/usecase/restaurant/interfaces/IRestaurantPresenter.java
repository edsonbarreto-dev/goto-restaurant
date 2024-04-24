package br.com.gotorestaurant.core.usecase.restaurant.interfaces;

import br.com.gotorestaurant.core.entity.Restaurant;

import java.util.List;




public interface IRestaurantPresenter {
    Long createRestaurant(Restaurant restaurantEntity);
    void updateRestaurant(Long id, Restaurant restaurantEntity);
    Restaurant findByDocument(String document);
    List<Restaurant> findAll();
    List<Restaurant> findByName(String name);
    List<Restaurant> findByCapacity(int capacity);
    List<Restaurant> findByReservationExists();
}
