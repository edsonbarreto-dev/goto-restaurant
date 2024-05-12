package br.com.gotorestaurant.core.usecase.restaurant.interfaces;

import br.com.gotorestaurant.core.entity.Customer;

public interface ICustomerPresenter {
    void updateCustomer(Customer customer);
    Long createCustomer(Customer customer);
    Customer findById(Long id);
    Customer findByDocument(String document);
    Customer findByName(String name);
    Customer findByEmail(String email);
}
