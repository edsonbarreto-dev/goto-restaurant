package br.com.gotorestaurant.core.usecase.restaurant.interfaces.read;

import br.com.gotorestaurant.core.entity.RestaurantEntity;

import java.util.List;

public interface IListRestaurantUseCase {

    List<RestaurantEntity> listAll();
    RestaurantEntity findByDocument(String document);
}
