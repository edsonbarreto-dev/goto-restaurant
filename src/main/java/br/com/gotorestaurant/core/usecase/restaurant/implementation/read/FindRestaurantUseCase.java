package br.com.gotorestaurant.core.usecase.restaurant.implementation.read;

import br.com.gotorestaurant.core.entity.RestaurantEntity;
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

    public List<RestaurantEntity> listAll() {
        return restaurantPresenter.findAll();
    }

    public RestaurantEntity findByDocument(String document) {
        return restaurantPresenter.findByDocument(document);
    }
}
