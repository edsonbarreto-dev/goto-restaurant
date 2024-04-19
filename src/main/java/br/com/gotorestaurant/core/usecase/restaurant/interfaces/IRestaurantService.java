package br.com.gotorestaurant.core.usecase.restaurant.interfaces;

import br.com.gotorestaurant.application.record.RestaurantVO;
import br.com.gotorestaurant.core.entity.RestaurantEntity;
import br.com.gotorestaurant.core.records.*;

import java.util.List;
import java.util.UUID;

public interface IRestaurantService {
    UUID create(RestaurantVO restaurant);
    List<RestaurantEntity> listAll();
    RestaurantEntity findByDocument(String document);
    RestaurantEntity findCustomerByDocument(String document);
    Reservation findReservationByCustomer(Customer customer);
    Phone findPhoneByCustomer(Customer customer);
    Phone findPhoneRestaurantByDocument(String document);
    RestaurantEntity addCustomer(Customer customer, RestaurantEntity restaurantEntity);
    RestaurantEntity addEmployee(Employee employee, RestaurantEntity restaurantEntity);
    RestaurantEntity addPartner(Partner partner, RestaurantEntity restaurantEntity);
    RestaurantEntity addPhone(Phone phone, RestaurantEntity restaurantEntity);
    RestaurantEntity addSocialMedia(SocialMedia socialMedia, RestaurantEntity restaurantEntity);
    RestaurantEntity addSupplier(Supplier supplier, RestaurantEntity restaurantEntity);
    RestaurantEntity makeReservation(Reservation reservation, RestaurantEntity restaurantEntity);
}
