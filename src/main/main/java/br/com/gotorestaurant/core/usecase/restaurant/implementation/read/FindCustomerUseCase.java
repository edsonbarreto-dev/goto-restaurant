package br.com.gotorestaurant.core.usecase.restaurant.implementation.read;

import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.exceptions.DocumentNullException;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.ICustomerPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindCustomerUseCase;
import org.springframework.stereotype.Service;

@Service
public class FindCustomerUseCase implements IFindCustomerUseCase {

    private final ICustomerPresenter presenter;

    public FindCustomerUseCase(ICustomerPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Customer findById(Long id) {
        return presenter.findById(id);
    }

    public Customer findByDocument(String document) {
        if (document.isEmpty()) throw new DocumentNullException("Customer");
        return presenter.findByDocument(document);
    }
}
