package br.com.gotorestaurant.core.usecase.restaurant.interfaces.read;

import br.com.gotorestaurant.core.entity.RestaurantEntity;

public interface IFindRestaurantUseCase {

    RestaurantEntity findByDocument(String document);
}
