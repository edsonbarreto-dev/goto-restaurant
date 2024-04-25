package br.com.gotorestaurant.core.usecase.restaurant.interfaces.read;

import br.com.gotorestaurant.core.entity.Restaurant;

public interface IFindRestaurantUseCase {

    Restaurant findByDocument(String document);
}
