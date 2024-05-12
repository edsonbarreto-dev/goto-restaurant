package br.com.gotorestaurant.core.usecase.restaurant.implementation.update;

import br.com.gotorestaurant.application.repository.entity.PhoneEntity;
import br.com.gotorestaurant.application.shared.CustomerMapper;
import br.com.gotorestaurant.application.shared.PhoneMapper;
import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.exceptions.CustomerNotFoundException;
import br.com.gotorestaurant.core.exceptions.RestaurantNotFoundForUpdateCustomerException;
import br.com.gotorestaurant.core.records.Phone;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.ICustomerPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.update.IUpdateCustomerRestaurantUseCase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateCustomerRestaurantUseCase implements IUpdateCustomerRestaurantUseCase {

    private final IRestaurantPresenter restaurantPresenter;
    private final ICustomerPresenter customerPresenter;
    private boolean updated = false;

    public UpdateCustomerRestaurantUseCase(
            IRestaurantPresenter restaurantPresenter,
            ICustomerPresenter customerPresenter
    ) {
        this.restaurantPresenter = restaurantPresenter;
        this.customerPresenter = customerPresenter;
    }


    @Override
    public void update(Customer customer, String document) {
        this.restaurantPresenter.updateCustomer(customer, document);
    }
}
