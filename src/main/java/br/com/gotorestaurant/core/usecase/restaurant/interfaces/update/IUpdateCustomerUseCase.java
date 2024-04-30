package br.com.gotorestaurant.core.usecase.restaurant.interfaces.update;

import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.entity.SocialMedia;

public interface IUpdateCustomerUseCase {
    Customer update(SocialMedia socialMedia, String document);
}
