package br.com.gotorestaurant.core.usecase.restaurant.interfaces;

import br.com.gotorestaurant.core.entity.RestaurantEntity;

import java.util.List;
import java.util.UUID;



public interface IRestaurantPresenter {
    UUID createRestaurant(RestaurantEntity restaurantEntity);
    void updateRestaurant(UUID uuid, RestaurantEntity restaurantEntity);
    RestaurantEntity findByDocument(String document);
    List<RestaurantEntity> findAll();
    List<RestaurantEntity> findByName(String name);
    List<RestaurantEntity> findByCapacity(int capacity);
    List<RestaurantEntity> findByReservationExists();
}
