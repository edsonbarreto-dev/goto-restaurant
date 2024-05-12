package br.com.gotorestaurant.core.usecase.restaurant.implementation.create;

import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.exceptions.CustomerHasExistsException;
import br.com.gotorestaurant.core.exceptions.CustomerNotFoundException;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.ICustomerPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.ICreateCustomerUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindCustomerUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerUseCase implements ICreateCustomerUseCase {

    private final IFindCustomerUseCase findUseCase;
    private final ICustomerPresenter customerPresenter;

    public CreateCustomerUseCase(
            IFindCustomerUseCase findCustomerUseCase,
            ICustomerPresenter customerPresenter
    ) {
        this.findUseCase = findCustomerUseCase;
        this.customerPresenter = customerPresenter;
    }

    @Override
    public Long createCustomer(Customer customer) throws RuntimeException {
        try {
            this.findUseCase.findByDocument(customer.getDocument());
            throw new CustomerHasExistsException(customer.getDocument());
        } catch (CustomerNotFoundException e) {
            return this.customerPresenter.createCustomer(customer);
        }
    }
}
