package br.com.gotorestaurant.core.usecase.restaurant.interfaces.create;

import br.com.gotorestaurant.core.entity.Customer;

public interface ICreateCustomerUseCase {

    Long createCustomer(Customer customer);
}
