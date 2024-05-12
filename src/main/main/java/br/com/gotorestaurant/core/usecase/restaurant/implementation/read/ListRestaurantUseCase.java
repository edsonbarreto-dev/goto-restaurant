package br.com.gotorestaurant.core.usecase.restaurant.implementation.read;

import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IListRestaurantUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListRestaurantUseCase implements IListRestaurantUseCase {

    private final IRestaurantPresenter presenter;

    public ListRestaurantUseCase(IRestaurantPresenter presenter) {
        this.presenter = presenter;
    }

    public List<Restaurant> listAll() {
        return presenter.findAll();
    }

    public Restaurant findByDocument(String document) {
        return presenter.findByDocument(document);
    }
}
