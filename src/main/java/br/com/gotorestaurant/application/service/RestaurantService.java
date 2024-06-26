package br.com.gotorestaurant.application.service;

import br.com.gotorestaurant.application.shared.CustomerMapper;
import br.com.gotorestaurant.application.repository.IRestaurantRepository;
import br.com.gotorestaurant.core.usecase.restaurant.implementation.create.CreateRestaurantUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.implementation.read.FindRestaurantUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.IMakeReservationUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.update.IUpdateCustomerRestaurantUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.records.*;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantService;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.ICreateRestaurantUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindRestaurantUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IListRestaurantUseCase;


@Service
public class RestaurantService implements IRestaurantService {

    @Autowired
    private IListRestaurantUseCase listRestaurantUseCase;

    @Autowired
    private IFindRestaurantUseCase findRestaurantUseCase;

    @Autowired
    private IMakeReservationUseCase makeReservationUseCase;

    @Autowired
    private ICreateRestaurantUseCase createRestaurantUseCase;

    @Autowired
    private IUpdateCustomerRestaurantUseCase updateCustomerRestaurantUseCase;
  
    @Autowired
    private final IRestaurantRepository restaurantRepository;

    public RestaurantService(IRestaurantRepository restaurantRepository, CreateRestaurantUseCase createRestaurantUseCase,
                             FindRestaurantUseCase findRestaurantUseCase, IListRestaurantUseCase listRestaurantUseCase) {
        this.restaurantRepository = restaurantRepository;
        this.createRestaurantUseCase = createRestaurantUseCase;
        this.findRestaurantUseCase = findRestaurantUseCase;
        this.listRestaurantUseCase = listRestaurantUseCase;
    }

    @Override
    public Long create(Restaurant restaurant) {
        return this.createRestaurantUseCase.createRestaurant(restaurant);
    }

    @Override
    public List<Restaurant> listAll() {
        return this.listRestaurantUseCase.listAll();
    }

    @Override
    public Restaurant findBy(Long id) {
        return this.findRestaurantUseCase.findBy(id);
    }

    @Override
    public Restaurant findByDocument(String document) {
        return this.findRestaurantUseCase.findByDocument(document);
    }

    @Override
    public Restaurant findCustomerByDocument(String document) {
        return null;
    }

    @Override
    public Reservation findReservationByCustomer(Customer customer) {
        return null;
    }

    @Override
    public Phone findPhoneByCustomer(Customer customer) {
        return null;
    }

    @Override
    public Phone findPhoneRestaurantByDocument(String document) {
        return null;
    }

    @Override
    public Restaurant addCustomer(Customer customer, Restaurant restaurantEntity) {
        return null;
    }

    @Override
    public Restaurant addEmployee(Employee employee, Restaurant restaurantEntity) {
        return null;
    }

    @Override
    public Restaurant addPartner(Partner partner, Restaurant restaurantEntity) {
        return null;
    }

    @Override
    public Restaurant addPhone(Phone phone, Restaurant restaurantEntity) {
        return null;
    }

    @Override
    public Restaurant addSocialMedia(SocialMedia socialMedia, Restaurant restaurantEntity) {
        return null;
    }

    @Override
    public Restaurant addSupplier(Supplier supplier, Restaurant restaurantEntity) {
        return null;
    }

    @Override
    public void updateCustomer(Customer customer, String document) {
        this.updateCustomerRestaurantUseCase.update(CustomerMapper.toCustomerCore(customer), document);
    }
}
