package br.com.gotorestaurant.core.usecase.restaurant.implementation.read;

import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.exceptions.DocumentNullException;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindRestaurantUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindRestaurantUseCase implements IFindRestaurantUseCase {

    private final IRestaurantPresenter restaurantPresenter;

    public FindRestaurantUseCase(IRestaurantPresenter restaurantPresenter) {
        this.restaurantPresenter = restaurantPresenter;
    }

    public List<Restaurant> listAll() {
        return restaurantPresenter.findAll();
    }

    public Restaurant findByDocument(Restaurant restaurant) {
        if (restaurant == null) throw new DocumentNullException("Restaurant");
        return restaurantPresenter.findByDocument(restaurant.document());
    }
}
