package br.com.gotorestaurant.core.usecase.restaurant.interfaces;

import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.records.*;

import java.util.List;


public interface IRestaurantService {
    Long create(Restaurant restaurant);
    List<Restaurant> listAll();
    Restaurant findBy(Long id);
    Restaurant findByDocument(String document);
    Restaurant findCustomerByDocument(String document);
    Reservation findReservationByCustomer(Customer customer);
    void makeReservation(Reservation reservation, String document);
    Phone findPhoneByCustomer(Customer customer);
    Phone findPhoneRestaurantByDocument(String document);
    Restaurant addCustomer(Customer customer, Restaurant restaurantEntity);
    Restaurant addEmployee(Employee employee, Restaurant restaurantEntity);
    Restaurant addPartner(Partner partner, Restaurant restaurantEntity);
    Restaurant addPhone(Phone phone, Restaurant restaurantEntity);
    Restaurant addSocialMedia(SocialMedia socialMedia, Restaurant restaurantEntity);
    Restaurant addSupplier(Supplier supplier, Restaurant restaurantEntity);
    void updateCustomer(Customer customer, String document);
}
