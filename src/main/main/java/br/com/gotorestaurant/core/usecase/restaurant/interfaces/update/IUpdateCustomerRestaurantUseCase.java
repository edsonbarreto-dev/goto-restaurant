package br.com.gotorestaurant.core.usecase.restaurant.interfaces.update;


import br.com.gotorestaurant.core.entity.Customer;

public interface IUpdateCustomerRestaurantUseCase {
    void update(Customer customer, String document);
}
