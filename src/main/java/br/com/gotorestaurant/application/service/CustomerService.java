package br.com.gotorestaurant.application.service;
import br.com.gotorestaurant.application.repository.ICustomerRepository;
import br.com.gotorestaurant.application.repository.IRestaurantRepository;
import br.com.gotorestaurant.core.entity.SocialMedia;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.ICreateCustomerUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindCustomerUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.update.IUpdateCustomerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICreateCustomerUseCase createCustomerUseCase;

    @Autowired
    private IFindCustomerUseCase findCustomerUseCase;

    @Autowired
    private IUpdateCustomerUseCase updateCustomerUseCase;

    @Autowired
    private ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository, ICreateCustomerUseCase createCustomerUseCase,
                           IFindCustomerUseCase findCustomerUseCase) {
        this.findCustomerUseCase = findCustomerUseCase;
        this.createCustomerUseCase = createCustomerUseCase;
        this.customerRepository = customerRepository;
    }


    @Override
    public Long create(Customer customer) {
        return this.createCustomerUseCase.createCustomer(customer);
    }

    @Override
    public Customer findBy(Long id) {
        return this.findCustomerUseCase.findById(id);
    }

    @Override
    public Customer findByDocument(String document) {
        return this.findCustomerUseCase.findByDocument(document);
    }

    @Override
    public Customer update(SocialMedia socialMedia, String document) {
        return this.updateCustomerUseCase.update(socialMedia, document);
    }
}
