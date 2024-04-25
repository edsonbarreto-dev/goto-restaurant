package br.com.gotorestaurant.core.usecase.restaurant.interfaces.read;

import br.com.gotorestaurant.core.entity.Restaurant;

public interface IFindRestaurantUseCase {
    Restaurant findBy(Long id);
    Restaurant findByDocument(String document);
}
