package br.com.gotorestaurant.application.service;

import br.com.gotorestaurant.application.record.RestaurantVO;
import br.com.gotorestaurant.application.shared.RestaurantMapper;
import br.com.gotorestaurant.core.entity.RestaurantEntity;
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
        RestaurantEntity restaurantEntity = RestaurantMapper.toRestaurantEntity(restaurant);
        return this.createRestaurantUseCase.createRestaurant(restaurantEntity);
    }

    @Override
    public List<RestaurantEntity> listAll() {
        return this.listRestaurantUseCase.listAll();
    }

    @Override
    public RestaurantEntity findByDocument(String document) {
        return this.findRestaurantUseCase.findByDocument(document);
    }

    @Override
    public RestaurantEntity findCustomerByDocument(String document) {
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
    public RestaurantEntity addCustomer(Customer customer, RestaurantEntity restaurantEntity) {
        return null;
    }

    @Override
    public RestaurantEntity addEmployee(Employee employee, RestaurantEntity restaurantEntity) {
        return null;
    }

    @Override
    public RestaurantEntity addPartner(Partner partner, RestaurantEntity restaurantEntity) {
        return null;
    }

    @Override
    public RestaurantEntity addPhone(Phone phone, RestaurantEntity restaurantEntity) {
        return null;
    }

    @Override
    public RestaurantEntity addSocialMedia(SocialMedia socialMedia, RestaurantEntity restaurantEntity) {
        return null;
    }

    @Override
    public RestaurantEntity addSupplier(Supplier supplier, RestaurantEntity restaurantEntity) {
        return null;
    }

    @Override
    public RestaurantEntity makeReservation(Reservation reservation, RestaurantEntity restaurantEntity) {
        return null;
    }
}
