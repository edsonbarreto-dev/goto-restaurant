package br.com.gotorestaurant.core.usecase.restaurant.interfaces.create;

import br.com.gotorestaurant.core.entity.Restaurant;

import java.util.UUID;

public interface ICreateRestaurantUseCase {

    UUID createRestaurant(Restaurant restaurantEntity);
}
