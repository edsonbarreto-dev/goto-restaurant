package br.com.gotorestaurant.core.usecase.restaurant.implementation.create;

import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.exceptions.RestaurantHasExistsException;
import br.com.gotorestaurant.core.exceptions.RestaurantNotFoundException;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.ICreateRestaurantUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindCustomerUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindRestaurantUseCase;
import org.springframework.stereotype.Service;



@Service
public class CreateRestaurantUseCase implements ICreateRestaurantUseCase {

    private final IFindCustomerUseCase findCustomerUseCase;

    private final IFindRestaurantUseCase findRestaurantUseCase;

    private final IRestaurantPresenter restaurantPresenter;
    private final CreateCustomerUseCase createCustomerUseCase;

    public CreateRestaurantUseCase(
            IFindCustomerUseCase findCustomerUseCase,
            IFindRestaurantUseCase findRestaurantUseCase,
            IRestaurantPresenter restaurantPresenter,
            CreateCustomerUseCase createCustomerUseCase) {
        this.findCustomerUseCase = findCustomerUseCase;
        this.findRestaurantUseCase = findRestaurantUseCase;
        this.restaurantPresenter = restaurantPresenter;
        this.createCustomerUseCase = createCustomerUseCase;
    }

    @Override
    public Long createRestaurant(Restaurant restaurant) throws RuntimeException {
        if (!restaurant.customers().isEmpty()) {
            restaurant.customers().forEach(customer -> {
                Customer customerFind = findCustomerUseCase.findByDocument(customer.getDocument());
                if (customerFind == null) {
                    createCustomerUseCase.createCustomer(customer);
                }
            });
        }
        try {
            this.findRestaurantUseCase.findByDocument(restaurant.document());
            throw new RestaurantHasExistsException();
        } catch (RestaurantNotFoundException e) {
            return this.restaurantPresenter.createRestaurant(restaurant);
        }
    }
}
