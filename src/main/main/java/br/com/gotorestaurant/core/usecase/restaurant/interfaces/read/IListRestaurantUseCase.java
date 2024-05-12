package br.com.gotorestaurant.core.usecase.restaurant.interfaces.read;

import br.com.gotorestaurant.core.entity.Restaurant;

import java.util.List;

public interface IListRestaurantUseCase {
    List<Restaurant> listAll();
    Restaurant findByDocument(String document);
}
