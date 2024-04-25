package br.com.gotorestaurant.core.usecase.restaurant.implementation.read;

import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.exceptions.DocumentNullException;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindRestaurantUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindRestaurantUseCase implements IFindRestaurantUseCase {

    private final IRestaurantPresenter presenter;

    public FindRestaurantUseCase(IRestaurantPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Restaurant findBy(Long id) {
        return presenter.findById(id);
    }

    public Restaurant findByDocument(String document) {
        if (document.isEmpty()) throw new DocumentNullException("Restaurant");
        return presenter.findByDocument(document);
    }
}
