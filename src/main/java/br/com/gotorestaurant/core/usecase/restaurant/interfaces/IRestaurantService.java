package br.com.gotorestaurant.core.usecase.restaurant.interfaces;

import br.com.gotorestaurant.application.record.CreateResponse;
import br.com.gotorestaurant.application.record.ListResponse;
import br.com.gotorestaurant.application.record.RestaurantVO;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.records.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IRestaurantService<T> {
    ResponseEntity<CreateResponse<Restaurant>> create(RestaurantVO restaurant);
    ResponseEntity<ListResponse<List<Restaurant>>> listAll();
    Restaurant findByDocument(Restaurant document);
    Restaurant findCustomerByDocument(String document);
    Reservation findReservationByCustomer(Customer customer);
    Phone findPhoneByCustomer(Customer customer);
    Phone findPhoneRestaurantByDocument(String document);
    Restaurant addCustomer(Customer customer, Restaurant restaurantEntity);
    Restaurant addEmployee(Employee employee, Restaurant restaurantEntity);
    Restaurant addPartner(Partner partner, Restaurant restaurantEntity);
    Restaurant addPhone(Phone phone, Restaurant restaurantEntity);
    Restaurant addSocialMedia(SocialMedia socialMedia, Restaurant restaurantEntity);
    Restaurant addSupplier(Supplier supplier, Restaurant restaurantEntity);
    Restaurant makeReservation(Reservation reservation, Restaurant restaurantEntity);
}
