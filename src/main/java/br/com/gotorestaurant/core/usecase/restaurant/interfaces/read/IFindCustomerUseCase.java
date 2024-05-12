package br.com.gotorestaurant.core.usecase.restaurant.interfaces.read;

import br.com.gotorestaurant.core.entity.Customer;

public interface IFindCustomerUseCase {
    Customer findById(Long id);
    Customer findByDocument(String document);
}
