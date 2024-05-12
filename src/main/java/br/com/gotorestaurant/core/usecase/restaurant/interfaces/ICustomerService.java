package br.com.gotorestaurant.core.usecase.restaurant.interfaces;

import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.entity.SocialMedia;

public interface ICustomerService {
    Long create(Customer customer);
    Customer findBy(Long id);
    Customer findByDocument(String document);
    Customer update(SocialMedia socialMedia, String document);
}
