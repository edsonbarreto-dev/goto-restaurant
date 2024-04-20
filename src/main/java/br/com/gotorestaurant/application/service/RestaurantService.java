package br.com.gotorestaurant.application.service;

import br.com.gotorestaurant.application.record.RestaurantVO;
import br.com.gotorestaurant.application.shared.RestaurantMapper;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.records.*;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantService;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.ICreateRestaurantUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindRestaurantUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IListRestaurantUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService implements IRestaurantService {

    @Autowired
    private IListRestaurantUseCase listRestaurantUseCase;

    @Autowired
    private IFindRestaurantUseCase findRestaurantUseCase;

    @Autowired
    private ICreateRestaurantUseCase createRestaurantUseCase;

    @Override
    public UUID create(RestaurantVO restaurant) {
        Restaurant restaurantEntity = RestaurantMapper.toRestaurantEntity(restaurant);
        return this.createRestaurantUseCase.createRestaurant(restaurantEntity);
    }

    @Override
    public List<Restaurant> listAll() {
        return this.listRestaurantUseCase.listAll();
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
    public Restaurant makeReservation(Reservation reservation, Restaurant restaurantEntity) {
        return null;
    }
}
