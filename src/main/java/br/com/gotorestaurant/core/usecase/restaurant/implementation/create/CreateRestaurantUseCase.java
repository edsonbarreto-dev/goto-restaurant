package br.com.gotorestaurant.core.usecase.restaurant.implementation.create;

import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.exceptions.RestaurantHasExistsException;
import br.com.gotorestaurant.core.exceptions.RestaurantNullException;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.ICreateRestaurantUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindRestaurantUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateRestaurantUseCase implements ICreateRestaurantUseCase {

    private final IFindRestaurantUseCase findRestaurantUseCase;

    private final IRestaurantPresenter restaurantPresenter;

    public CreateRestaurantUseCase(
        IFindRestaurantUseCase findRestaurantUseCase, IRestaurantPresenter restaurantPresenter
    ) {
        this.findRestaurantUseCase = findRestaurantUseCase;
        this.restaurantPresenter = restaurantPresenter;
    }

    @Override
    public UUID createRestaurant(Restaurant restaurantEntity) throws RuntimeException {
        if (restaurantEntity == null) throw new RestaurantNullException();
        Restaurant restaurantEntityHasExists = this.findRestaurantUseCase.findByDocument(restaurantEntity.document());
        if (restaurantEntityHasExists != null) throw new RestaurantHasExistsException();
        return this.restaurantPresenter.createRestaurant(restaurantEntity);
    }
}
