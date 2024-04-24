package br.com.gotorestaurant.application.service;

import br.com.gotorestaurant.application.record.CreateResponse;
import br.com.gotorestaurant.application.record.ListResponse;
import br.com.gotorestaurant.application.record.RestaurantVO;
import br.com.gotorestaurant.application.shared.RestaurantMapper;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.records.*;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantService;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.ICreateRestaurantUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindRestaurantUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IListRestaurantUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RestaurantService implements IRestaurantService {

    @Autowired
    private IListRestaurantUseCase listRestaurantUseCase;

    @Autowired
    private IFindRestaurantUseCase findRestaurantUseCase;

    @Autowired
    private ICreateRestaurantUseCase createRestaurantUseCase;

    @Override
    public ResponseEntity<CreateResponse<Long>> create(RestaurantVO restaurantVO) {
        Restaurant restaurant = RestaurantMapper.toRestaurant(restaurantVO);
        Long id = this.createRestaurantUseCase.createRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CreateResponse<>(id, "Success Create Restaurant")
        );
    }

    @Override
    public ResponseEntity<ListResponse<List<RestaurantVO>>> listAll() {
        List<Restaurant> restaurants = this.listRestaurantUseCase.listAll();
        List<RestaurantVO> litRestaurantVO = RestaurantMapper.toLitRestaurantVO(restaurants);
        return ResponseEntity.ok(new ListResponse<>(litRestaurantVO, "Restaurant list successfully"));
    }

    @Override
    public Restaurant findByDocument(Restaurant restaurant) {
        return this.findRestaurantUseCase.findByDocument(restaurant);
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
