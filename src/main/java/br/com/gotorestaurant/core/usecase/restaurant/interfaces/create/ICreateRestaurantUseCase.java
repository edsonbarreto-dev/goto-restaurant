package br.com.gotorestaurant.core.usecase.restaurant.interfaces.create;

import br.com.gotorestaurant.core.entity.Restaurant;

public interface ICreateRestaurantUseCase {

    Long createRestaurant(Restaurant restaurant);
}
